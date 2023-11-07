package com.example.bottomnavtest.domain

class ServerApi {

    /*
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().build()

    fun getTimeTables(): List<TimeTable>? {
        val url = "https://novsuTimeTable.ru/api/getTimeTables"
        val listType = Types.newParameterizedType(List::class.java, TimeTable::class.java)

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            val adapter = moshi.adapter<List<TimeTable>>(listType)
            return adapter.fromJson(json)
        }

        return null
    }

    fun getGroups(): List<Groups>? {
        val url = "https://novsuTimeTable.ru/api/getGroups"
        val listType = Types.newParameterizedType(List::class.java, Groups::class.java)

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            val adapter = moshi.adapter<List<Groups>>(listType)
            return adapter.fromJson(json)
        }

        return null
    }

    fun getTeachers(): List<Teachers>? {
        val url = "https://novsuTimeTable.ru/api/getTeachers"
        val listType = Types.newParameterizedType(List::class.java, Teachers::class.java)

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            val adapter = moshi.adapter<List<Teachers>>(listType)
            return adapter.fromJson(json)
        }

        return null
    }

    fun getAuditoriums(): List<Auditoriums>? {
        val url = "https://novsuTimeTable.ru/api/getAuditoriums"
        val listType = Types.newParameterizedType(List::class.java, Auditoriums::class.java)

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            val adapter = moshi.adapter<List<Auditoriums>>(listType)
            return adapter.fromJson(json)
        }

        return null
    }
    */

}