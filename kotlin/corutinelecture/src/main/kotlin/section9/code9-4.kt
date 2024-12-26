package section9

import kotlinx.coroutines.*

fun main(): Unit = runBlocking<Unit> {
    val startTime: Long = System.currentTimeMillis()
    val results: Array<String> = searchByKeyword2(keyword = "Keyword") // 검색 실행 및 결과 값 반환 받기
    println("[결과] ${results.toList()}") // 결과값 출력
    println(getElapsedTime(startTime)) // 경과 시간 출력
}


suspend fun searchByKeyword2(keyword: String): Array<String> = coroutineScope {
    val dbResultsDeferred: Deferred<Array<String>> = async {
        searchFromDB(keyword)
    }
    val serverResultsDeferred: Deferred<Array<String>> = async {
        searchFromServer(keyword)
    }

    return@coroutineScope arrayOf(*dbResultsDeferred.await(), *serverResultsDeferred.await())
}