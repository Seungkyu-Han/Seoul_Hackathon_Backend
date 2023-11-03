package CoBo.Seoul.CoBo.Service

import CoBo.Seoul.CoBo.Data.Dto.Req.AuthPatchLoginReq
import CoBo.Seoul.CoBo.Data.Dto.Res.LoginRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
interface AuthService {
    fun login(code: String): ResponseEntity<LoginRes>

    fun login(authPatchLoginReq: AuthPatchLoginReq): ResponseEntity<LoginRes>
    fun check(authentication: Authentication): ResponseEntity<HttpStatus>
}