package CoBo.Seoul.CoBo.Service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun alarm(on: Boolean, authentication: Authentication): ResponseEntity<HttpStatus>
}