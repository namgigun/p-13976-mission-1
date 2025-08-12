package com.ll.wiseSaying

import com.ll.global.dto.Page
import kotlin.math.ceil

class WiseSayingRepository {
    private val wiseSayings = mutableListOf<WiseSaying>()
    private var lastId = 1

    fun save(sentence: String, writer: String): WiseSaying {
        val wiseSaying = WiseSaying(lastId++, sentence, writer)
        wiseSayings.add(wiseSaying)

        return wiseSaying
    }

    fun findAll() :List<WiseSaying>{
        return wiseSayings.reversed()
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

    fun findByKeywordPaged(
        keywordType: String,
        keyword: String,
        itemsPerPage: Int,
        pageNo: Int
    ): Page {
        val searchData = mutableListOf<WiseSaying>()

        for (wiseSaying in wiseSayings.reversed()) {
            if (keywordType.equals("author") && wiseSaying.writer.contains(keyword)) {
                searchData.add(wiseSaying)
            }

            if (keywordType.equals("content") && wiseSaying.sentence.contains(keyword)) {
                searchData.add(wiseSaying)
            }
        }

        // 페이징 처리
        val totalPage = ceil(searchData.size.toDouble() / itemsPerPage).toInt()

        val content = searchData
            .drop((pageNo - 1) * itemsPerPage)
            .take(itemsPerPage)

        return Page(totalPage, content)
    }

    fun findByAllPaged(itemsPerPage: Int, pageNo: Int): Page {
        val totalPage = ceil(wiseSayings.size.toDouble() / itemsPerPage).toInt()

        val content = findAll()
            .drop((pageNo - 1) * itemsPerPage)
            .take(itemsPerPage)

        return Page(totalPage, content)
    }
}