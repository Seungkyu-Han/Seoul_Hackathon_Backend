package CoBo.Seoul.CoBo.Service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface SpeedService {
    fun weekSpeedRate(): ResponseEntity<MutableMap<String, Double>>

    fun timeSpeedRate(): ResponseEntity<MutableMap<Int, Double>>
}