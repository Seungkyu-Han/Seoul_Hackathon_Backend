package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Data.Dto.Res.LoginRes
import CoBo.Seoul.CoBo.Service.AuthService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController(var authService: AuthService){

    @GetMapping("/check")
    @ApiOperation(
        value = "로그인 확인 API",
        notes = "토큰을 이용해 로그인을 확인합니다",
        response = HttpStatus::class
    )
    fun check(@Parameter(hidden = true) authentication:Authentication){
        TODO()
    }

    @PatchMapping("/login")
    @ApiOperation(
        value = "로그인 API",
        notes = "RefreshToken을 이용하여 토큰들을 반환",
        response = HttpStatus::class
    )
    fun login(@Parameter(hidden = true) authentication:Authentication){
        TODO()
    }

    @GetMapping("/login")
    @ApiOperation(
        value = "로그인 API",
        notes = "카카오 로그인"
    )
    fun login(@RequestParam code: String):ResponseEntity<LoginRes>{
        return authService.login(code)
    }
}