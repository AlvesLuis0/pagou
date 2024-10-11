package com.paydev.pagou.utils

object StringUtils {
  fun buildList(list: Collection<String>): String {
    val string = StringBuilder()
    list.forEach { item -> string.append("* ").append(item).append('\n') }
    string.deleteAt(string.lastIndex)
    return string.toString()
  }
}