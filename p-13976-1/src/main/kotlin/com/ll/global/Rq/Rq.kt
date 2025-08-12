package com.ll.global.Rq

class Rq(val input: String) {
    private val params = mutableMapOf<String, String>()

    init {
        val parts = input.split("?")
        params["action"] = parts[0]

        if(parts.size > 1) {
            parts[1].split("&").forEach { pair ->
                val kv = pair.split("=", limit = 2)
                if(kv.size == 2) {
                    params[kv[0]] = kv[1]
                }
            }
        }
    }
}