package section1.code2

class ExampleThread : Thread() {
    override fun run() {
        println("${currentThread().name} 시작")
        sleep(2000)
        println("${currentThread().name} 종료")
    }
}

fun main() {
    println("${Thread.currentThread().name} 시작")
    ExampleThread().start()
    Thread.sleep(1000)
    println("${Thread.currentThread().name} 종료")
}