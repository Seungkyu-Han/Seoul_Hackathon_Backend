package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Repository.UserRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.mail.MessagingException

@RestController
@RequestMapping("/api/smtp")
class SmtpController(
    val javaMailSender: JavaMailSender,
    val userRepository: UserRepository
){

    @GetMapping("/test")
    fun test(){
        val userList = userRepository.findAll()

        for(user in userList){
            val mimeMessage = javaMailSender.createMimeMessage()
            return try {
                val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")
                helper.setTo(user.email)
                helper.setSubject("테스트입니다.")
                helper.setText("hello", true)
                javaMailSender.send(mimeMessage)
            } catch (e: MessagingException) {
                throw RuntimeException(e)
            }
        }
    }

}