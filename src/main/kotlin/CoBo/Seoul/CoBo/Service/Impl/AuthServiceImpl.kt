package CoBo.Seoul.CoBo.Service.Impl

import CoBo.Seoul.CoBo.Config.Jwt.JwtTokenProvider
import CoBo.Seoul.CoBo.Data.Dto.Res.LoginRes
import CoBo.Seoul.CoBo.Repository.UserRepository
import CoBo.Seoul.CoBo.Service.AuthService
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonParser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

@Service
class AuthServiceImpl(
    val userRepository: UserRepository,
    val jwtTokenProvider: JwtTokenProvider
): AuthService {

    private final val client_id:String = "014fd6985cda29d0e5fe4263fc3c366b"
    private final val secret_key:String = "COBOCOBOCOBOCOBOCOBOCOBOCOBOCOBOCOBO"
    private final val redirect_uri = "http://localhost:8080/api/auth/login"

    override fun login(code: String): ResponseEntity<LoginRes> {


        val userId = getKakaoUserIdByKakaoAccessToken(getKakaoAccessToken(code))

        val accessToken = jwtTokenProvider.createAccessToken(userId.toInt(), secret_key)
        val refreshToken = jwtTokenProvider.createRefreshToken(userId.toInt(), secret_key)

        return ResponseEntity(LoginRes(accessToken = accessToken, refreshToken = refreshToken), HttpStatus.OK)
    }

    private fun getKakaoAccessToken(code: String): String {
        val accessToken:String
        val requestURL = "https://kauth.kakao.com/oauth/token"

        val url = URL(requestURL)
        val httpURLConnection:HttpURLConnection = url.openConnection() as HttpURLConnection

        httpURLConnection.requestMethod = "POST"
        httpURLConnection.doOutput = true

        val bufferedWriter = BufferedWriter(OutputStreamWriter(httpURLConnection.outputStream))
        val stringBuilder = "grant_type=authorization_code" +
                "&client_id=" + client_id +
                "&redirect_uri=" + redirect_uri +
                "&code=" + code
        bufferedWriter.write(stringBuilder)
        bufferedWriter.flush()

        httpURLConnection.responseCode

        val element:JsonElement = getJsonElement(httpURLConnection)

        accessToken = element.asJsonObject.get("access_token").asString

        bufferedWriter.close()

        return accessToken
    }

    private fun getKakaoUserIdByKakaoAccessToken(token: String): Long {
        val element = getJsonElementByAccessToken(token)

        val id = element?.asJsonObject?.get("id")?.asLong ?: 0

        println("** LOGIN: 로그인 시도하는 유저의 KAKAO ID :$id")

        return userRepository.findById(id).get().id

    }

    private fun getJsonElementByAccessToken(token : String): JsonElement? {
        val reqUrl = "https://kapi.kakao.com/v2/user/me"

        val url = URL(reqUrl)
        val httpURLConnection = url.openConnection() as HttpURLConnection

        httpURLConnection.requestMethod = "POST"
        httpURLConnection.doOutput = true
        httpURLConnection.setRequestProperty("Authorization", "Bearer $token")

        return getJsonElement(httpURLConnection)
    }

    private fun getJsonElement(httpURLConnection: HttpURLConnection): JsonElement {
        val bufferedReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
        val result = StringBuilder() // 라인을 누적하기 위해 StringBuilder를 사용합니다.

        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            result.append(line)
        }

        bufferedReader.close()

        val jsonString = result.toString()
        return JsonParser.parseString(jsonString)
    }

}