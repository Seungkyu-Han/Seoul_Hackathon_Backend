package CoBo.Seoul.CoBo.Config.Jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class JwtTokenProvider {

    private final val accessTokenValidTime = Duration.ofHours(2).toMillis()
    private final val refreshTokenValidTime = Duration.ofDays(7).toMillis()

    fun getUserId(token: String, secretKey: String):Int{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body.get("userId", Int::class.java)
    }

    fun isAccessToken(token :String, secretKey: String):Boolean{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .header.get("type").toString().equals("access")
    }

    fun createAccessToken(userId: Int, secretKey: String):String{
        return createJwtToken(userId, secretKey, "access", accessTokenValidTime)
    }

    fun createRefreshToken(userId: Int, secretKey: String):String{
        return createJwtToken(userId, secretKey, "refresh", refreshTokenValidTime)
    }

    fun createJwtToken(userId: Int, secretKey: String, type:String, tokenValidTime:Long):String{
        val claims = Jwts.claims()
        claims.put("userId", userId)

        return Jwts.builder()
            .setHeaderParam("type", type)
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }
}