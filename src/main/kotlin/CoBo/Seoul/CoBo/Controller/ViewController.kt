package CoBo.Seoul.CoBo.Controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {


    @GetMapping("/")
    fun main():String{
        return "/Reverse"
    }

    @GetMapping("/Reverse")
    fun reverse():String{
        return "/Reverse"
    }

    @GetMapping("/Speed")
    fun speed():String{
        return "/Speed"
    }

    @GetMapping("/aside")
    fun aside():String{
        return "/aside"
    }

    @GetMapping("/login")
    fun login():String{
        return "/login"
    }
}