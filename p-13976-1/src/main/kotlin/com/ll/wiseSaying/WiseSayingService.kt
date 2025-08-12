package com.ll.wiseSaying

import com.ll.global.dto.Page

class WiseSayingService {
    private val wiseSayingRepository = WiseSayingFileRepository()
    fun write(sentence: String, writer: String): WiseSaying {
        return wiseSayingRepository.save(sentence, writer)
    }

    fun getWiseSayings(
        keywordType: String,
        keyword: String,
        itemsPerPage: Int,
        pageNo: Int
    ): Page {
        return wiseSayingRepository.findByKeywordPaged(
            keywordType,
            keyword,
            itemsPerPage,
            pageNo,
        )
    }

    fun getWiseSayings(
        itemsPerPage: Int,
        pageNo: Int
    ) : Page {
        return wiseSayingRepository.findByAllPaged(
            itemsPerPage,
            pageNo
        )
    }

    fun remove(id: Int): String {
        val isDelete = wiseSayingRepository.delete(id)

        val msg = if(isDelete) "${id}번 명언이 삭제되었습니다." else "${id}번 명언은 존재하지 않습니다."
        return msg
    }

    fun getWiseSaying(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun update(wiseSaying: WiseSaying) {
        wiseSayingRepository.save(wiseSaying)
    }

    fun build() {
        wiseSayingRepository.saveAllToDataJson()
    }
}