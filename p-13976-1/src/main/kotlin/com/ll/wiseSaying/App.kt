package com.ll.wiseSaying

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ll.global.Rq.Rq
import java.io.File

class App {
    private val wiseSayingController = WiseSayingController()
    fun run() {
        println("== 명언 앱 ==")

        while(true) {
            print("명령) ")

            val input = readlnOrNull()!!.trim()

            val rq = Rq(input)

            if(rq.getAction().equals("종료")) {
                println("프로그램을 종료합니다.");
                break
            }

            else if(rq.getAction().equals("등록")) {
                wiseSayingController.writeWiseSaying()
            }

            else if(rq.getAction().equals("목록")) {
                wiseSayingController.readWiseSayings(rq)
            }

            else if(rq.getAction().equals("삭제")) {
                val id = input.substringAfter("=", "-1").toInt()

                wiseSayingController.removeWiseSaying(id)
            }

            else if(rq.getAction().equals("수정")) {
                val id = input.substringAfter("=", "-1").toInt()
                wiseSayingController.updateWiseSaying(id)
            }

            else if(rq.getAction().equals("빌드")) {
                wiseSayingController.build();
            }

            else {
                println("해당 작업은 존재하지 않습니다.")
            }
        }
    }
}