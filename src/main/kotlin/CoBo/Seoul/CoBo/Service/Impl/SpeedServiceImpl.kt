package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Repository.SpeedRepository
import CoBo.Seoul.CoBo.Service.SpeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SpeedServiceImpl(speedRepository: SpeedRepository): SpeedService {


    override fun weekSpeedRate(): ResponseEntity<HttpStatus> {


        return ResponseEntity(HttpStatus.OK)
    }
}