package com.ll.wiseSaying

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class App {
    var wiseSayings = mutableListOf<WiseSaying>()
    var lastId = 1
    val dir = File("db/wiseSaying")
    val gson = Gson()
    var file = File(dir, "lastId.txt")
    var jsonString = ""
    val wiseSayingController = WiseSayingController()
    fun run() {
        println("== 명언 앱 ==")

        // lastId 불러오기
        if (file.exists()) {
            lastId = file.readText().trim().toInt() // 문자열 → Int 변환
        } else {
            println("lastId.txt 파일이 없습니다.")
        }

        // 명언 불러오기
        file = File(dir, "data.json")
        if (file.exists()) {

            jsonString = file.readText()
            val listType = object : TypeToken<MutableList<WiseSaying>>() {}.type

            wiseSayings = gson.fromJson(jsonString, listType)
        }

        while(true) {
            print("명령) ")

            val input = readlnOrNull()!!.trim()

            if(input.equals("종료")) {
                println("프로그램을 종료합니다.");
                break
            }

            else if(input.equals("등록")) {
                print("명언 : ")
                val sentence = readln()
                print("작가 : ")
                val writer = readln()

                wiseSayings.add(WiseSaying(lastId++, sentence, writer))
                println("${wiseSayings.size}번 명언이 등록되었습니다.")
            }

            else if(input.equals("목록")) {
                println("번호 / 작가 / 명언")
                println("----------------------")
                for (wiseSaying in wiseSayings.reversed()) {
                    println("${wiseSaying.id} / ${wiseSaying.writer} / ${wiseSaying.sentence}")
                }
            }

            else if(input.startsWith("삭제")) {
                val deleteId = input.substringAfter("=", "-1").toInt()
                var isDelete = false

                for(wiseSaying in wiseSayings) {
                    if(wiseSaying.id == deleteId) {
                        isDelete = true
                        wiseSayings.remove(wiseSaying)
                    }
                }

                val msg = if(isDelete) "${deleteId}번 명언이 삭제되었습니다." else "${deleteId}번 명언은 존재하지 않습니다."
                println(msg)
            }

            else if(input.startsWith("수정")) {
                val reviseId = input.substringAfter("=", "-1").toInt()
                var isRevise = false;

                for(wiseSaying in wiseSayings) {
                    if(wiseSaying.id == reviseId) {
                        isRevise = true

                        println("명언(기존) : ${wiseSaying.sentence}")
                        print("명언 : ")
                        wiseSaying.sentence = readln()

                        println("작가(기존) : ${wiseSaying.writer}")
                        print("명언 : ")
                        wiseSaying.writer = readln()
                    }
                }

                if(!isRevise) {
                    println("${reviseId}번 명언은 존재하지 않습니다.")
                }
            }
        }

        // 파일 저장 코드
        if (!dir.exists()) {
            dir.mkdirs() // 중간 경로까지 생성
            println("디렉터리 생성 완료: ${dir.absolutePath}")
        }

        // LastId 정보 저장
        file = File(dir, "lastId.txt")
        file.writeText(lastId.toString())

        // 명언 정보 저장
        file = File(dir, "data.json")
        jsonString = gson.toJson(wiseSayings)
        file.writeText(jsonString)
    }
}