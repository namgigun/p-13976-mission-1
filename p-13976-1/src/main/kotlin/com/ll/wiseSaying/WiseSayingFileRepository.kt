package com.ll.wiseSaying

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ll.global.dto.Page
import java.io.File
import kotlin.math.ceil


class WiseSayingFileRepository : WiseSayingRepository {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val lastIdFile = File("db/wiseSaying/lastId.txt")
    override fun save(sentence: String, writer: String): WiseSaying {
        var lastId = readLastId()

        val wiseSaying = WiseSaying(++lastId, sentence, writer)

        val file = File(getFilePath(lastId))
        // 폴더가 없으면 생성
        file.parentFile.mkdirs()
        file.writeText(gson.toJson(wiseSaying))

        // lastId 파일에 저장
        saveLastId(lastId)
        return wiseSaying
    }

    override fun save(wiseSaying: WiseSaying) : WiseSaying {
        val file = File(getFilePath(wiseSaying.id))
        file.writeText(gson.toJson(wiseSaying))

        return wiseSaying
    }

    override fun findAll() :List<WiseSaying>{
        val dir = File("db/wiseSaying")
        if (!dir.exists() || !dir.isDirectory) return emptyList()

        return dir.listFiles { file ->
            file.isFile && file.extension == "json" && file.name != "lastId.txt"
        }?.sortedBy { file -> // id 순으로 정렬
            file.nameWithoutExtension.toIntOrNull() ?: Int.MAX_VALUE
        }?.mapNotNull { file ->
            try {
                gson.fromJson(file.readText(), WiseSaying::class.java)
            } catch (e: Exception) {
                null // 파싱 실패 시 무시
            }
        }?.reversed() ?: emptyList()
    }

    override fun delete(deleteId: Int): Boolean {
        val file = File(getFilePath(deleteId))
        return if (file.exists()) {
            file.delete()
        } else {
            false
        }
    }

    override fun findById(id: Int) : WiseSaying? {
        val file = File(getFilePath(id))
        return if (file.exists()) {
            gson.fromJson(file.readText(), WiseSaying::class.java)
        } else {
            null
        }
    }

    override fun findByKeywordPaged(
        keywordType: String,
        keyword: String,
        itemsPerPage: Int,
        pageNo: Int
    ): Page {
        val wiseSayings = findAll()
        val searchData = mutableListOf<WiseSaying>()

        for (wiseSaying in wiseSayings) {
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
        val data = findAll()
        val totalPage = ceil(data.size.toDouble() / itemsPerPage).toInt()

        val content = data
            .drop((pageNo - 1) * itemsPerPage)
            .take(itemsPerPage)

        return Page(totalPage, content)
    }


    private fun getFilePath(id: Int): String = "db/wiseSaying/$id.json"

    private fun readLastId() : Int {
        return if (lastIdFile.exists()) {
            lastIdFile.readText().toIntOrNull() ?: 0
        } else {
            0
        }
    }

    private fun saveLastId(id : Int) {
        lastIdFile.writeText(id.toString())
    }
}