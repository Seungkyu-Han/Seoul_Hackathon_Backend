package CoBo.Seoul.CoBo.Service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
interface ReverseRunService {
    fun weekReverseRunCount(): ResponseEntity<MutableMap<String, Int>>
    fun timeReverseRunCount(): ResponseEntity<Map<Int, Int>>
}