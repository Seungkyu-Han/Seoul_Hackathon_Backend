package CoBo.Seoul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoBoApplication

fun main(args: Array<String>) {
	runApplication<CoBoApplication>(*args)
}
