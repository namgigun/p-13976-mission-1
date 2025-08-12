package com.ll.wiseSaying

import com.ll.global.Rq.Rq

class WiseSayingService {
    private val wiseSayingRepository = WiseSayingRepository()
    fun write(sentence: String, writer: String): WiseSaying {
        return wiseSayingRepository.save(sentence, writer)
    }

    fun getWiseSayings(rq: Rq): MutableList<WiseSaying> {
        val wiseSayings = wiseSayingRepository.findAll()
        val keywordType = rq.getKeywordType()
        val keyword = rq.getKeyword()

        if(keywordType == null || keyword == null) {
            return wiseSayings
        }

        val ret = mutableListOf<WiseSaying>()
        for(wiseSaying in wiseSayings) {
            if(keywordType.equals("author") && wiseSaying.writer.contains(keyword)) {
                ret.add(wiseSaying)
            }

            if(keywordType.equals("content") && wiseSaying.sentence.contains(keyword)) {
                ret.add(wiseSaying)
            }
        }

        return ret
    }

    fun remove(id: Int): String {
        val isDelete = wiseSayingRepository.delete(id)

        val msg = if(isDelete) "${id}번 명언이 삭제되었습니다." else "${id}번 명언은 존재하지 않습니다."
        return msg
    }

    fun getWiseSaying(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }
}