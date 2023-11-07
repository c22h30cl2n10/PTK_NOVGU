package com.example.bottomnavtest.domain

class ServerApiTest {
    /*private val mockWebServer = MockWebServer()
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().build()
    private val serverApi = ServerApi()

    @Test
    fun testGetTimeTablesSuccess() {
        val responseBody = """
            [
                {
                    "id": 1,
                    "name": "TimeTable 1"
                },
                {
                    "id": 2,
                    "name": "TimeTable 2"
                }
            ]
        """
        val expectedTimeTables = listOf(
            TimeTable(1, "TimeTable 1"),
            TimeTable(2, "TimeTable 2")
        )

        mockWebServer.enqueue(MockResponse().setBody(responseBody))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val timeTables = serverApi.getTimeTables()
        assertEquals(expectedTimeTables, timeTables)
    }

    @Test
    fun testGetTimeTablesFailure() {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val timeTables = serverApi.getTimeTables()
        assertNull(timeTables)
    }

    @Test
    fun testGetGroupsSuccess() {
        val responseBody = """
            [
                {
                    "id": 1,
                    "name": "Group 1"
                },
                {
                    "id": 2,
                    "name": "Group 2"
                }
            ]
        """
        val expectedGroups = listOf(
            Groups(1, "Group 1"),
            Groups(2, "Group 2")
        )

        mockWebServer.enqueue(MockResponse().setBody(responseBody))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val groups = serverApi.getGroups()
        assertEquals(expectedGroups, groups)
    }

    @Test
    fun testGetGroupsFailure() {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val groups = serverApi.getGroups()
        assertNull(groups)
    }

    @Test
    fun testGetTeachersSuccess() {
        val responseBody = """
            [
                {
                    "id": 1,
                    "name": "Teacher 1"
                },
                {
                    "id": 2,
                    "name": "Teacher 2"
                }
            ]
        """
        val expectedTeachers = listOf(
            Teachers(1, "Teacher 1"),
            Teachers(2, "Teacher 2")
        )

        mockWebServer.enqueue(MockResponse().setBody(responseBody))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val teachers = serverApi.getTeachers()
        assertEquals(expectedTeachers, teachers)
    }

    @Test
    fun testGetTeachersFailure() {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val teachers = serverApi.getTeachers()
        assertNull(teachers)
    }

    @Test
    fun testGetAuditoriumsSuccess() {
        val responseBody = """
            [
                {
                    "id": 1,
                    "name": "Auditorium 1"
                },
                {
                    "id": 2,
                    "name": "Auditorium 2"
                }
            ]
        """
        val expectedAuditoriums = listOf(
            Auditoriums(1, "Auditorium 1"),
            Auditoriums(2, "Auditorium 2")
        )

        mockWebServer.enqueue(MockResponse().setBody(responseBody))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val auditoriums = serverApi.getAuditoriums()
        assertEquals(expectedAuditoriums, auditoriums)
    }

    @Test
    fun testGetAuditoriumsFailure() {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        mockWebServer.start()
        val baseUrl: HttpUrl = mockWebServer.url("/")
        val response = client.newCall(Request.Builder().url(baseUrl).build()).execute()

        val auditoriums = serverApi.getAuditoriums()
        assertNull(auditoriums)
    }*/
}