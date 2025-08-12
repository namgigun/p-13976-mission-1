package com.ll.wiseSaying

import com.ll.global.dto.Page
import kotlin.math.ceil

class WiseSayingMemoryRepository : WiseSayingRepository {
    private val wiseSayings = mutableListOf<WiseSaying>()
    private var lastId = 1

    override fun save(sentence: String, writer: String): WiseSaying {
        val wiseSaying = WiseSaying(lastId++, sentence, writer)
        wiseSayings.add(wiseSaying)

        return wiseSaying
    }

    override fun save(wiseSaying: WiseSaying) :WiseSaying{
        return wiseSaying
    }

    override fun findAll() :List<WiseSaying>{
        return wiseSayings.reversed()
    }

    override fun delete(deleteId: Int): Boolean {
        var isDelete = false

        for(wiseSaying in wiseSayings) {
            if(wiseSaying.id == deleteId) {
                isDelete = true
                wiseSayings.remove(wiseSaying)
            }
        }

        return isDelete
    }

    override fun findById(id: Int) : WiseSaying? {
        for(wiseSaying in wiseSayings) {
            if(wiseSaying.id == id) {
                return wiseSaying
            }
        }

        return null
    }

    override fun findByKeywordPaged(
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

    override fun findByAllPaged(itemsPerPage: Int, pageNo: Int): Page {
        val totalPage = ceil(wiseSayings.size.toDouble() / itemsPerPage).toInt()

        val content = findAll()
            .drop((pageNo - 1) * itemsPerPage)
            .take(itemsPerPage)

        return Page(totalPage, content)
    }
}