package com.example.fluentd.demo1

import lombok.Builder
import lombok.Getter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigDecimal

@SpringBootApplication
class Demo1Application

fun main(args: Array<String>) {
//	runApplication<Demo1Application>(*args)
    val test: ArrayList<Int>

    val person = Person("test", 12);

}

fun add(a: Int, b: Int, c: Int): Int {
    return a + b + c
}

fun add(a: Long, b: Long, c: Long) = a + b + c

class Person(private val name: String?, private val age: Int?) {
    constructor(name:String) : this(name,12) {

    }

}