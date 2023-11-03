package CoBo.Seoul.CoBo.Config.Jwt

import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.net.http.HttpHeaders
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RequiredArgsConstructor
@Component
class JwtFilter(
    var jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {

    private final val secret_key:String = "COBOCOBOCOBOCOBOCOBOCOBOCOBOCOBOCOBO"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization:String? = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION)
        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request, response)
            return
        }
        val token = authorization.split(" ")[1]

        if(!jwtTokenProvider.isAccessToken(token, secret_key)){
            filterChain.doFilter(request, response)
            return
        }

        val userId = jwtTokenProvider.getUserId(token, secret_key)

        println(userId)

        val usernamePasswordAuthenticationFilter = UsernamePasswordAuthenticationToken(userId, null, listOf(SimpleGrantedAuthority("USER")))
        SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationFilter
        filterChain.doFilter(request, response)
    }
}