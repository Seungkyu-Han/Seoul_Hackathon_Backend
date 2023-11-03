package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Data.Entity.ReverseRun
import CoBo.Seoul.CoBo.Data.Entity.Speed
import CoBo.Seoul.CoBo.Data.RegionEnum
import CoBo.Seoul.CoBo.Repository.ReverseRunRepository
import CoBo.Seoul.CoBo.Repository.SpeedRepository
import CoBo.Seoul.CoBo.Service.ArduinoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDateTime

@Service
class ArduinoServiceImpl(
    val reverseRunRepository: ReverseRunRepository,
    val speedRepository: SpeedRepository
):ArduinoService{

    val overSpeed = 35.0

    override fun reverseRun(region: RegionEnum, direction: Int): ResponseEntity<HttpStatus> {

        val currentDateTime = LocalDateTime.now()

        reverseRunRepository.save(ReverseRun(
            id = null,
            created_at = currentDateTime,
            region=region,
            direction=direction,
            day_of_the_week_tag = getDayOfTheWeek(),
            time_tag = currentDateTime.toLocalTime().hour.toShort()
        ))

        return ResponseEntity(HttpStatus.OK)
    }

    override fun speed(speed: Float, region: RegionEnum, direction: Int): ResponseEntity<HttpStatus> {
        val currentDateTime = LocalDateTime.now()

        speedRepository.save(Speed(
            id = null,
            speed = speed,
            created_at = LocalDateTime.now(),
            region = region,
            direction = direction,
            day_of_the_week_tag = getDayOfTheWeek(),
            time_tag = currentDateTime.toLocalTime().hour.toShort(),
            overSpeed = speed >= overSpeed
        ))

        return ResponseEntity(HttpStatus.OK)

    }

    private fun getDayOfTheWeek():String{
        return when(LocalDateTime.now().dayOfWeek){
            DayOfWeek.MONDAY -> "월"
            DayOfWeek.TUESDAY -> "화"
            DayOfWeek.WEDNESDAY -> "수"
            DayOfWeek.THURSDAY -> "목"
            DayOfWeek.FRIDAY -> "금"
            DayOfWeek.SATURDAY -> "토"
            DayOfWeek.SUNDAY -> "일"
            else -> "에러"
        }
    }


}