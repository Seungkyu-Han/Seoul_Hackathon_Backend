package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Data.RegionEnum
import CoBo.Seoul.CoBo.Service.ArduinoService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/arduino")
class ArduinoController(
    val arduinoService: ArduinoService){

    @GetMapping("/reverse-run")
    @ApiOperation(
        value = "역주행 정보를 서버로 전송합니다.",
        notes = "역주행에 관한 정보를 파라미터에 적어서 전송하면 됩니다.",
        response = HttpStatus::class
    )
    @ApiImplicitParams(
        ApiImplicitParam(name = "region", value = "역주행이 발생한 지역의 정보입니다."),
        ApiImplicitParam(name = "direction", value = "역주행이 발생한 방향의 정보입니다.", type = "query")
    )
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun reverseRun(
        @RequestParam region:RegionEnum,
        @RequestParam direction:Int,
    ):ResponseEntity<HttpStatus>{
        return arduinoService.reverseRun(region, direction)
    }

    @GetMapping("/speed")
    @ApiOperation(
        value = "과속 정보를 서버로 전송합니다.",
        notes = "과속에 대한 정보를 파라미터에 적어서 전송하면 됩니다.",
        response = HttpStatus::class
    )
    @ApiImplicitParams(

    )
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun speed(
        @RequestParam speed:Float,
        @RequestParam region: RegionEnum,
        @RequestParam direction: Int,
        request: HttpServletRequest
    ):ResponseEntity<HttpStatus>{
        println(request.requestURL.toString())
        return arduinoService.speed(speed, region, direction)
    }
}