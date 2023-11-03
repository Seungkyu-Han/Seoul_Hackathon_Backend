package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Data.Entity.ReverseRun
import CoBo.Seoul.CoBo.Data.Entity.Speed
import CoBo.Seoul.CoBo.Data.RegionEnum
import CoBo.Seoul.CoBo.Data.WeekEnum
import CoBo.Seoul.CoBo.Repository.ReverseRunRepository
import CoBo.Seoul.CoBo.Repository.SpeedRepository
import CoBo.Seoul.CoBo.Service.ArduinoService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDateTime

@Service
class ArduinoServiceImpl(
    val reverseRunRepository: ReverseRunRepository,
    val speedRepository: SpeedRepository,
    val applicationEventPublisher: ApplicationEventPublisher
):ArduinoService{

    val overSpeed = 35.0

    override fun reverseRun(region: RegionEnum, direction: Int): ResponseEntity<HttpStatus> {

        val currentDateTime = LocalDateTime.now()

        val reverseRun = ReverseRun(
            id = null,
            created_at = currentDateTime,
            region=region,
            direction=direction,
            day_of_the_week_tag = getDayOfTheWeek(),
            time_tag = currentDateTime.toLocalTime().hour.toShort()
        )

        reverseRunRepository.save(reverseRun)

        applicationEventPublisher.publishEvent(reverseRun)

        return ResponseEntity(HttpStatus.OK)
    }

    override fun speed(speed: Float, region: RegionEnum, direction: Int): ResponseEntity<HttpStatus> {
        val currentDateTime = LocalDateTime.now()

        val speedEntity = Speed(
            id = null,
            speed = speed,
            created_at = LocalDateTime.now(),
            region = region,
            direction = direction,
            day_of_the_week_tag = getDayOfTheWeek(),
            time_tag = currentDateTime.toLocalTime().hour.toShort(),
            overSpeed = speed >= overSpeed
        )

        speedRepository.save(speedEntity)

        applicationEventPublisher.publishEvent(speedEntity)

        return ResponseEntity(HttpStatus.OK)

    }

    private fun getDayOfTheWeek():WeekEnum{
        return when(LocalDateTime.now().dayOfWeek){
            DayOfWeek.MONDAY -> WeekEnum.월
            DayOfWeek.TUESDAY -> WeekEnum.화
            DayOfWeek.WEDNESDAY -> WeekEnum.수
            DayOfWeek.THURSDAY -> WeekEnum.목
            DayOfWeek.FRIDAY -> WeekEnum.금
            DayOfWeek.SATURDAY -> WeekEnum.토
            DayOfWeek.SUNDAY -> WeekEnum.일
            else -> WeekEnum.에러
        }
    }


}