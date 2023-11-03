package CoBo.Seoul.CoBo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CoBoApplication

fun main(args: Array<String>) {
	runApplication<CoBoApplication>(*args)
}
