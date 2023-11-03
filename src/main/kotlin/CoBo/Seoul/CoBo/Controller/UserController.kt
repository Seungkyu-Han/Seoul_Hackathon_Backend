package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Service.UserService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@RestController
@RequestMapping("/api/user")
class UserController(
    var userService: UserService
) {

    @GetMapping("/alarm")
    @ApiOperation(
        value = "메일 알람 ON/OFF",
        notes = "ON -> 메일 알람 켜기, OFF -> 메일 알람 끄기"
    )
    @ApiImplicitParams(
        ApiImplicitParam(name = "on", value="알람 상태")
    )
    @ApiResponse(code = 200, message = "나머지는 모두 실패입니다.")
    fun alarm(
        @RequestParam on:Boolean,
        @ApiIgnore authentication: Authentication
    ):ResponseEntity<HttpStatus>{
        return userService.alarm(on, authentication)
    }
}