package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Repository.UserRepository
import CoBo.Seoul.CoBo.Service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(var userRepository: UserRepository):UserService {
    override fun alarm(on: Boolean, authentication: Authentication): ResponseEntity<HttpStatus> {

        println(authentication.name.toLong())
        val optionalUserEntity = userRepository.findById(
            Integer.valueOf(authentication.name).toInt()
        ).get()

        optionalUserEntity.alarm = on
        userRepository.save(optionalUserEntity)

        return ResponseEntity(HttpStatus.OK)
    }
}