package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Service.ReverseRunService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reverse-run")
class ReverseRunController (
    val reverseRunService: ReverseRunService){


    @GetMapping("/week")
    @ApiOperation(
        value = "최근 1주간 요일별로 역주행의 수를 구해줍니다.",
        notes = "주간 역주행의 비율을 구해줍니다.",
    )
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun weekSpeedRate(): ResponseEntity<MutableMap<String, Int>> {
        return reverseRunService.weekReverseRunCount()
    }

    @GetMapping("/time")
    @ApiOperation(
        value = "최근 1주간 시간별로 과속의 비율을 구해줍니다.",
        notes = "주간 역주행의 비율을 구해줍니다."
    )
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun timeSpeedRate(): ResponseEntity<Map<Int, Int>> {
        return reverseRunService.timeReverseRunCount()
    }

    @GetMapping("/region")
    @ApiOperation(
        value = "최근 1주간 가장 많은 역주행이 발생한 지역을 구해줍니다.",
        notes = "주간 위치별 역주행의 비율을 구해줍니다."
    )
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun regionSpeedRate():ResponseEntity<Map<String, Int>>{
        return reverseRunService.regionReverseRunCount()
    }

}