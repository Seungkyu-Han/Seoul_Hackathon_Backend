package CoBo.Seoul.CoBo.Controller

import CoBo.Seoul.CoBo.Service.ReverseRunService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reverse-run")
class ReverseRunController (
    val reverseRunService: ReverseRunService){

}