package com.ll.wiseSaying

class WiseSayingRepository {
    private val wiseSayings = mutableListOf<WiseSaying>()
    private var lastId = 1

    fun save(sentence: String, writer: String): WiseSaying {
        val wiseSaying = WiseSaying(lastId++, sentence, writer)
        wiseSayings.add(wiseSaying)

        return wiseSaying
    }

    fun findAll(): MutableList<WiseSaying> {
        return wiseSayings
    }

    fun delete(deleteId: Int): Boolean {
        var isDelete = false

        for(wiseSaying in wiseSayings) {
            if(wiseSaying.id == deleteId) {
                isDelete = true
                wiseSayings.remove(wiseSaying)
            }
        }

        return isDelete
    }

    fun findById(id: Int) : WiseSaying? {
        for(wiseSaying in wiseSayings) {
            if(wiseSaying.id == id) {
                return wiseSaying
            }
        }

        return null
    }
}