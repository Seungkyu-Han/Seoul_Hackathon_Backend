package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Data.WeekEnum
import CoBo.Seoul.CoBo.Repository.SpeedRepository
import CoBo.Seoul.CoBo.Service.SpeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class SpeedServiceImpl(var speedRepository: SpeedRepository): SpeedService {


    override fun weekSpeedRate(): ResponseEntity<MutableMap<String, Double>> {
        val week = LocalDateTime.now().minus(1, ChronoUnit.WEEKS)
        val weekSpeedMap = mutableMapOf<String, Double>()
        for(weekDay in WeekEnum.values()){
            val count = speedRepository.countSpeedWeekTag(week, weekDay)
            if(count == 0){
                weekSpeedMap[weekDay.name] = 0.0
            }else{
                weekSpeedMap[weekDay.name] =
                    speedRepository.countOverSpeedWeekTag(week, weekDay) /
                            count.toDouble()
            }
        }
        return ResponseEntity(weekSpeedMap, HttpStatus.OK)
    }

    override fun timeSpeedRate(): ResponseEntity<Map<Int, Double>> {
        val week = LocalDateTime.now().minus(1, ChronoUnit.WEEKS)
        val timeSpeedMap = mutableMapOf<Int, Double>()

        for (time in 0..23) {
            val count = speedRepository.countSpeedTimeTag(week, time.toShort())
            if (count == 0) {
                timeSpeedMap[time] = 0.0
            } else {
                val overSpeedTimeTag = speedRepository.countOverSpeedTimeTag(week, time.toShort())
                val rate = overSpeedTimeTag.toDouble() / count.toDouble()
                timeSpeedMap[time] = rate
            }
        }

        // 맵을 값으로 내림차순으로 정렬
        val sortedMap = timeSpeedMap.entries
            .sortedByDescending { it.value }
            .take(3) // 상위 3개 값만 선택

        // 정렬된 상위 3개 값을 새로운 맵으로 만듦
        val top3TimeSpeedMap = sortedMap.associate { it.key to it.value }

        return ResponseEntity(top3TimeSpeedMap, HttpStatus.OK)
    }
}