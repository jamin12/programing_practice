package section9

import kotlinx.coroutines.delay

suspend fun searchByKeyword(keyword: String): Array<String> {
    val dbResults: Array<String> = searchFromDB(keyword)
    val serverResults: Array<String> = searchFromServer(keyword)
    return arrayOf(*dbResults, *serverResults)
}

suspend fun searchFromDB(keyword: String): Array<String> {
    delay(timeMillis = 1000L)
    return arrayOf("[DB]${keyword}1", "[DB]${keyword}2")
}

suspend fun searchFromServer(keyword: String): Array<String> {
    delay(timeMillis = 1000L)
    return arrayOf("[Server]${keyword}1", "[Server]${keyword}2")
}
