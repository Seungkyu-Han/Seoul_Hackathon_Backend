package CoBo.Seoul.CoBo.Config

import CoBo.Seoul.CoBo.Socket.ReverseRunSocket
import CoBo.Seoul.CoBo.Socket.SpeedSocket
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    val speedSocket: SpeedSocket,
    val reverseRunSocket: ReverseRunSocket
):WebSocketConfigurer{

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(speedSocket, "/speed").setAllowedOriginPatterns("*")
        registry.addHandler(reverseRunSocket, "/reverse-run").setAllowedOriginPatterns("*")
    }
}