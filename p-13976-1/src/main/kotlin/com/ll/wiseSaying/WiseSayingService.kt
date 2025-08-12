package com.ll.wiseSaying

class WiseSayingService {
    private val wiseSayingRepository = WiseSayingRepository()
    fun write(sentence: String, writer: String): WiseSaying {
        return wiseSayingRepository.save(sentence, writer)
    }

    fun getWiseSayings(): MutableList<WiseSaying> {
        return wiseSayingRepository.findAll()
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