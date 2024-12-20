package section7

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val parentJob: Job = launch(Dispatchers.IO) { // 부모 코루틴 생성
        val dbResultsDeferred: List<Deferred<String>> = listOf("db1", "db2", "db3").map { it: String ->
            async { // 자식 코루틴 생성
                delay(timeMillis = 1000L) // DB로부터 데이터를 가져오는데 걸리는 시간
                println("${it}로부터 데이터를 가져오는데 성공했습니다")
                return@async "${it}data"
            }
        }
        val dbResults: List<String> = dbResultsDeferred.awaitAll() // 모든 코루틴이 완료될 때까지 대기

        println(dbResults) // 화면에 표시
    }
    parentJob.cancel()
}
