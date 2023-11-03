package CoBo.Seoul.CoBo.Service

import CoBo.Seoul.CoBo.Data.RegionEnum
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface ArduinoService {

    fun reverseRun(region:RegionEnum, direction:Int):ResponseEntity<HttpStatus>
    fun speed(speed:Float, region:RegionEnum, direction:Int): ResponseEntity<HttpStatus>
}