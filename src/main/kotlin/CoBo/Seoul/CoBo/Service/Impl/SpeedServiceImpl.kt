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

    override fun timeSpeedRate(): ResponseEntity<MutableMap<Int, Double>> {
        val week = LocalDateTime.now().minus(1, ChronoUnit.WEEKS)
        val timeSpeedMap = mutableMapOf<Int, Double>()
        for(time in 0..23){
            val count = speedRepository.countSpeedTimeTag(week, time.toShort())
            if(count == 0){
                timeSpeedMap[time] = 0.0
            }else{
                timeSpeedMap[time] =
                    speedRepository.countOverSpeedTimeTag(week, time.toShort()) /
                            count.toDouble()
                println(time)
                println(count)
                println(speedRepository.countOverSpeedTimeTag(week, time.toShort()))
                println()
            }
        }
        println("EEEEEEE")
        return ResponseEntity(timeSpeedMap, HttpStatus.OK)
    }
}