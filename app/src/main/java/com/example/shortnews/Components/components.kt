package com.example.shortnews.Components

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun dataTimeFormat(date: String): String {
    val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
    val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
    return formattedDate

}