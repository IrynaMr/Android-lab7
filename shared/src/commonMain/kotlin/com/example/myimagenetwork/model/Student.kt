package com.example.myimagenetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val group: String,
    val avatarId: Int // Лише числовий ідентифікатор із сервісу Picsum
) {
    // Динамічна генерація URL-адреси
    val avatarUrl: String
        get() = "https://picsum.photos/$avatarId"
}