package CoBo.Seoul.CoBo.Scheduler

import CoBo.Seoul.CoBo.Repository.UserRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import javax.mail.MessagingException

@Component
class SendMail(
    val userRepository: UserRepository,
    val javaMailSender: JavaMailSender){

    @Scheduled(cron = "0 0 * * * *")
    fun sendMail(){
        println("메일 전송 시작")
        val userList = userRepository.findAll()

        for(user in userList){
            val mimeMessage = javaMailSender.createMimeMessage()
            return try{
                val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")
                helper.setTo(user.email)
                helper.setSubject("한강 자전거 보고서입니다.")
                helper.setText("", true)
                javaMailSender.send(mimeMessage)
            }catch(e:MessagingException){
                println("에러 발생")
                throw RuntimeException(e)
            }
        }
    }
}