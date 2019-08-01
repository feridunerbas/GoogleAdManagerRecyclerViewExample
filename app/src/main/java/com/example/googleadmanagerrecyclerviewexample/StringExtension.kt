package com.example.googleadmanagerrecyclerviewexample


fun String.loremIpsum(): String{
    val words = (10 until 30).random()
   return loremIpsum(words)
}

fun String.loremIpsum(words: Int): String{

    val full = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    val arr = full.split(" ")
    return arr.firstUpTo(words).joinToString(" ")

}

fun <T>List<T>.firstUpTo(count: Int): List<T>{
    val result = mutableListOf<T>()
    val c = kotlin.math.min(count, size)
    for (i in 1..c){
        result.add(this[i])
    }
    return result
}