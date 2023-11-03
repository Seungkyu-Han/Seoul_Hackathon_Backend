package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Service.SpeedService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/speed")
class SpeedController (
        val speedService: SpeedService){

    @GetMapping("/week")
    @ApiOperation(
        value = "최근 1주간 과속의 횟수를 구해줍니다.",
        notes = "주간 과속의 비율을 구해줍니다.",
    )
    @ApiImplicitParams()
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun weekSpeedRate():ResponseEntity<HttpStatus>{
        return speedService.weekSpeedRate()
    }

}