package CoBo.Seoul.CoBo.Scheduler

import CoBo.Seoul.CoBo.Repository.UserRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SendMail(val userRepository: UserRepository){

    @Scheduled(cron = "0 0 0 * * *")
    fun sendMail(){
        val userList = userRepository.findAll()

        for(user in userList){

        }
    }
}