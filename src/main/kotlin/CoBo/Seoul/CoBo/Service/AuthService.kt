package CoBo.Seoul.CoBo.Service

import CoBo.Seoul.CoBo.Data.Dto.Res.LoginRes
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface AuthService {
    fun login(code: String): ResponseEntity<LoginRes>
}