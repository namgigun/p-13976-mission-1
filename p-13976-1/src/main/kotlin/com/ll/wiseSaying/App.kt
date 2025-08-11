package com.ll.wiseSaying

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class App {

//    val dir = File("db/wiseSaying")
//    val gson = Gson()
//    var file = File(dir, "lastId.txt")
//    var jsonString = ""
    val wiseSayingController = WiseSayingController()
    fun run() {
        println("== 명언 앱 ==")

        // lastId 불러오기
//        if (file.exists()) {
//            lastId = file.readText().trim().toInt() // 문자열 → Int 변환
//        } else {
//            println("lastId.txt 파일이 없습니다.")
//        }

        // 명언 불러오기
//        file = File(dir, "data.json")
//        if (file.exists()) {
//
//            jsonString = file.readText()
//            val listType = object : TypeToken<MutableList<WiseSaying>>() {}.type
//
//            wiseSayings = gson.fromJson(jsonString, listType)
//        }

        while(true) {
            print("명령) ")

            val input = readlnOrNull()!!.trim()

            if(input.equals("종료")) {
                println("프로그램을 종료합니다.");
                break
            }

            else if(input.equals("등록")) {
                wiseSayingController.writeWiseSaying()
            }

            else if(input.equals("목록")) {
                wiseSayingController.readWiseSayings()
            }

            else if(input.startsWith("삭제")) {
                val id = input.substringAfter("=", "-1").toInt()

                wiseSayingController.removeWiseSaying(id)
            }

            else if(input.startsWith("수정")) {
                val id = input.substringAfter("=", "-1").toInt()
                wiseSayingController.updateWiseSaying(id)
            }
        }

        // 파일 저장 코드
//        if (!dir.exists()) {
//            dir.mkdirs() // 중간 경로까지 생성
//            println("디렉터리 생성 완료: ${dir.absolutePath}")
//        }
//
//        // LastId 정보 저장
//        file = File(dir, "lastId.txt")
//        file.writeText(lastId.toString())
//
//        // 명언 정보 저장
//        file = File(dir, "data.json")
//        jsonString = gson.toJson(wiseSayings)
//        file.writeText(jsonString)
    }
}