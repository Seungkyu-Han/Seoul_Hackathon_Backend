package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Data.WeekEnum
import CoBo.Seoul.CoBo.Repository.ReverseRunRepository
import CoBo.Seoul.CoBo.Service.ReverseRunService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class ReverseRunServiceImpl(var reverseRunRepository: ReverseRunRepository):ReverseRunService {

    override fun weekReverseRunCount(): ResponseEntity<MutableMap<String, Int>> {
        val week = LocalDateTime.now().minus(1, ChronoUnit.WEEKS)
        val weekReverseRunMap = mutableMapOf<String, Int>()
        for(weekDay in WeekEnum.values())
            weekReverseRunMap[weekDay.name] = reverseRunRepository.countReverseRunWeekTag(week, weekDay) ?: 0
        return ResponseEntity(weekReverseRunMap, HttpStatus.OK)
    }

    override fun timeReverseRunCount(): ResponseEntity<Map<Int, Int>> {
        val week = LocalDateTime.now().minus(1, ChronoUnit.WEEKS)
        val timeReverseRunMap = mutableMapOf<Int, Int>()

        for (time in 0..23) {
            timeReverseRunMap[time] = reverseRunRepository.countReverseRunTimeTag(week, time.toShort()) ?: 0
        }

        // 맵을 값으로 내림차순으로 정렬
        val sortedMap = timeReverseRunMap.entries
            .sortedByDescending { it.value }
            .take(3) // 상위 3개 값만 선택

        // 정렬된 상위 3개 값을 새로운 맵으로 만듦
        val top3TimeReverseRunMap = sortedMap.associate { it.key to it.value }

        return ResponseEntity(top3TimeReverseRunMap, HttpStatus.OK)
    }

}