package com.ll.wiseSaying

import com.ll.global.Rq.Rq

class WiseSayingController {
    private val wiseSayingService = WiseSayingService()

    fun writeWiseSaying() {
        print("명언 : ")
        val sentence = readln()
        print("작가 : ")
        val writer = readln()

        val wiseSaying = wiseSayingService.write(sentence, writer)
        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    fun readWiseSayings(rq: Rq) {
        val keywordType = rq.getKeywordType()
        val keyword = rq.getKeyword()

        if(keyword.isNotBlank()) {
            println("----------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("----------------------")
        }

        val pageNo = rq.getPageToInt()
        val itemsPerPage = 5

        val wiseSayings = if (keyword.isNotBlank())
            wiseSayingService.getWiseSayings(keywordType, keyword, itemsPerPage, pageNo)
        else
            wiseSayingService.getWiseSayings(itemsPerPage, pageNo)

        println("번호 / 작가 / 명언")
        println("----------------------")
        for (wiseSaying in wiseSayings.data) {
            println("${wiseSaying.id} / ${wiseSaying.writer} / ${wiseSaying.sentence}")
        }
        println("----------------------")

        print("페이지 : ")

        val pageMenu = (1..wiseSayings.totalPages)
            .joinToString(" ") {
                if (it == pageNo) "[$it]" else it.toString()
            }

        println(pageMenu)
    }

    fun removeWiseSaying(id: Int) {
        val msg = wiseSayingService.remove(id)
        println(msg)
    }

    fun updateWiseSaying(id: Int) {
        val wiseSaying = wiseSayingService.getWiseSaying(id);

        if(wiseSaying == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return
        }

        println("명언(기존) : ${wiseSaying.sentence}")
        print("명언 : ")
        wiseSaying.sentence = readln()

        println("작가(기존) : ${wiseSaying.writer}")
        print("명언 : ")
        wiseSaying.writer = readln()
    }
}