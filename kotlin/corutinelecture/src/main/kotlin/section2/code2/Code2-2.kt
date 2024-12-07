package section2.code2

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit>(CoroutineName("runBlocking")) {
    println("${Thread.currentThread().name} runBlocking 코루틴 실행")

    launch(CoroutineName("lunch")) {
        println("${Thread.currentThread().name} lunch 코루틴 실행")
    }
}