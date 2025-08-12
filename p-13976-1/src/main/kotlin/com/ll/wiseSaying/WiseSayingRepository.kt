package com.ll.wiseSaying

import com.ll.global.dto.Page
import java.io.File
import kotlin.math.ceil

interface WiseSayingRepository {
    fun save(sentence: String, writer: String): WiseSaying

    fun save(wiseSaying: WiseSaying) : WiseSaying

    fun findAll() :List<WiseSaying>

    fun delete(deleteId: Int): Boolean

    fun findById(id: Int) : WiseSaying?

    fun findByKeywordPaged(
        keywordType: String,
        keyword: String,
        itemsPerPage: Int,
        pageNo: Int
    ): Page

    fun findByAllPaged(itemsPerPage: Int, pageNo: Int): Page
}