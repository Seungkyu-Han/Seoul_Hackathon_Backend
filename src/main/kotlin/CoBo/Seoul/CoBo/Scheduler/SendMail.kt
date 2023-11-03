package CoBo.Seoul.CoBo.Scheduler

import CoBo.Seoul.CoBo.Repository.UserRepository
import CoBo.Seoul.CoBo.Service.ReverseRunService
import CoBo.Seoul.CoBo.Service.SpeedService
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import javax.mail.MessagingException

@Component
class SendMail(
    val userRepository: UserRepository,
    val javaMailSender: JavaMailSender,
    val reverseRunService: ReverseRunService,
    val speedService: SpeedService
    ){

    @Scheduled(cron = "0 * * * * *")
    fun sendMail(){
        val userList = userRepository.findAllByAlarmIsTrue()

        val reverseTime = reverseRunService.timeReverseRunCount().body?.keys?.toList()
        val reverseRegion = reverseRunService.regionReverseRunCount().body?.keys?.toList()
        val speedTime = speedService.timeSpeedRate().body?.keys?.toList()
        val speedRegion = speedService.regionSpeedRate().body?.keys?.toList()

        for(user in userList){
            val mimeMessage = javaMailSender.createMimeMessage()
            return try{
                val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")
                helper.setTo(user.email)
                helper.setSubject("한강 자전거 보고서입니다.")
                helper.setText("""
                    <!doctype html>
                    <html>
                    <head>
                        <meta charset="utf-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <title>이메일</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
                        <style>
                            .email-page {
                        width: 640px;
                        height: 766px;
                        background: #FFF;
                    }

                    .title-bar {
                        width: 640px;
                        height: 80px;
                        border-bottom: 1px solid #EAF476;
                        padding-top: 10px;
                        padding-bottom: 10px;
                        padding-left: 30px;
                    }

                    .logo-img {
                        width: 119px;
                        height: 60px;
                    }

                    .email-illustration {
                        color: #000;
                        font-family: Pretendard;
                        font-size: 20px;
                        font-style: normal;
                        font-weight: 400;
                        line-height: 30px; /* 150% */
                        padding-top: 70px;
                        padding-left: 40px;
                    }

                    .contents {
                        margin-top: 90px;
                        display: inline-block;
                    }

                    .left-contents {
                        float: left;
                    }

                    .right-contents {
                        float: left;
                    }

                    .content {
                        margin-left: 40px;
                        margin-bottom: 50px;
                    }

                    .content-title {
                        color: #000;
                        font-family: Pretendard;
                        font-size: 16px;
                        font-style: normal;
                        font-weight: 600;
                        line-height: 26px; /* 162.5% */
                        letter-spacing: -0.4px;
                    }

                    .content-data {
                        width: 232px;
                        height: 106px;
                        border-radius: 16px;
                        border: 0.5px solid #DBDBDB;
                        margin-top: 11px;
                    }

                    .ranking {
                        list-style: none;
                        margin-top: 10px;
                        padding: 0 10px;
                    }

                    .ranking > li {
                        padding: 5px 15px;
                        border-radius: 100px;
                        margin-bottom: 7px;

                        color: #000;
                        font-family: Pretendard;
                        font-size: 16px;
                        font-style: normal;
                        font-weight: 400;
                        line-height: 14px; /* 100% */
                        letter-spacing: -0.35px;
                    }

                    .ranking > li:nth-child(1) {
                        background-color: rgba(106, 67, 215, 0.50);
                    }

                    .ranking > li > span {
                        margin-left: 20px;
                        color: #000;
                        font-family: Pretendard;
                        font-size: 14px;
                        font-style: normal;
                        font-weight: 400;
                        line-height: 14px; /* 100% */
                        letter-spacing: -0.35px;
                    }

                    .under-bar {
                        width: 640px;
                        height: 60px;
                        border-top: 1px solid #A58EE7;
                    }
                        </style>
                    </head>
                    <body>
                        <div class="email-page">
                            <div class="title-bar">
                                <img class="logo-img" src="cid:image">
                            </div>
                            <div class="main-container">
                                <div class="email-illustration">바로에서 알려드려요.<br>내 주위의 역주행, 과속 정보를 살펴보세요.</div>
                                <div class="contents">
                                    <div class="left-contents">
                                        <div class="content">
                                            <div class="content-title">역주행은 이 시간에 가장 많이 발생했어요!</div>
                                            <div class="content-data">
                                                <ul class="ranking" id="ranking-time">
                                                    <li>01<span>${reverseTime?.get(0) ?: 0}시 - ${reverseTime?.get(0)?.plus(1) ?: 0}시</span></li>
                                                    <li>02<span>${reverseTime?.get(1) ?: 0}시 - ${reverseTime?.get(1)?.plus(1) ?: 0}시</span></li>
                                                    <li>03<span>${reverseTime?.get(2) ?: 0}시 - ${reverseTime?.get(2)?.plus(1) ?: 0}시</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="content">
                                            <div class="content-title">역주행은 이곳에서 가장 많이 발생했어요!</div>
                                            <div class="content-data">
                                                <ul class="ranking" id="ranking-time">
                                                    <li>01<span>${reverseRegion?.get(0) ?: "기록 없음"}</span></li>
                                                    <li>02<span>${reverseRegion?.get(1) ?: "기록 없음"}</span></li>
                                                    <li>03<span>${reverseRegion?.get(2) ?: "기록 없음"}</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="right-contents">
                                        <div class="content">
                                            <div class="content-title">과속은 이 시간에 가장 많이 발생했어요!</div>
                                            <div class="content-data">
                                                <ul class="ranking" id="ranking-time">
                                                    <li>01<span>${speedTime?.get(0) ?: 0}시 - ${speedTime?.get(0)?.plus(1) ?: 0}시</span></li>
                                                    <li>03<span>${speedTime?.get(1) ?: 0}시 - ${speedTime?.get(1)?.plus(1) ?: 0}시</span></li>
                                                    <li>02<span>${speedTime?.get(2) ?: 0}시 - ${speedTime?.get(2)?.plus(1) ?: 0}시</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="content">
                                            <div class="content-title">과속은 이곳에서 가장 많이 발생했어요!</div>
                                            <div class="content-data">
                                                <ul class="ranking" id="ranking-time">
                                                    <li>01<span>${speedRegion?.get(0) ?: "기록 없음"}</span></li>
                                                    <li>02<span>${speedRegion?.get(1) ?: "기록 없음"}</span></li>
                                                    <li>03<span>${speedRegion?.get(2) ?: "기록 없음"}</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="under-bar"></div>
                        </div>

                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                        crossorigin="anonymous"></script>
                        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                        crossorigin="anonymous"></script>
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                        crossorigin="anonymous"></script>
                    </body>
                    </html>
                """.trimIndent(), true)
                val image = ClassPathResource("static/baro.png")
                helper.addInline("image", image)
                javaMailSender.send(mimeMessage)
            }catch(e:MessagingException){
                println("에러 발생")
                throw RuntimeException(e)
            }
        }
    }
}