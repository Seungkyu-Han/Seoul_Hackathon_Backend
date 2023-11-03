package CoBo.Seoul.CoBo.Socket

import CoBo.Seoul.CoBo.Data.Dto.ReverseRunDto
import CoBo.Seoul.CoBo.Data.Dto.SpeedDto
import CoBo.Seoul.CoBo.Data.Entity.ReverseRun
import CoBo.Seoul.CoBo.Data.Entity.Speed
import CoBo.Seoul.CoBo.Repository.ReverseRunRepository
import CoBo.Seoul.CoBo.Repository.SpeedRepository
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException
import java.lang.RuntimeException

@Component
@RequiredArgsConstructor
class ReverseRunSocket(
    val reverseRunRepository: ReverseRunRepository,
    val objectMapper: ObjectMapper
):TextWebSocketHandler(){

    private val webSocketSessionList: MutableList<WebSocketSession> = ArrayList()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        webSocketSessionList.add(session)
        val reverseRunList = ArrayList<ReverseRunDto>()
        for(speed in reverseRunRepository.findAll())
            reverseRunList.add(
                ReverseRunDto(
                created_at = speed.created_at,
                region = speed.region,
                direction = speed.direction
            )
            )
        session.sendMessage(TextMessage(objectMapper.writeValueAsBytes(reverseRunList)))
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        webSocketSessionList.remove(session)
    }

    @EventListener
    fun sendData(reverseRun: ReverseRun){
        for(webSocketSession in webSocketSessionList){
            try{
                webSocketSession.sendMessage(TextMessage(objectMapper.writeValueAsBytes(ReverseRunDto(
                    created_at = reverseRun.created_at,
                    region = reverseRun.region,
                    direction = reverseRun.direction
                ))))
            }catch (e: IOException){
                throw RuntimeException(e)
            }
        }
    }


}