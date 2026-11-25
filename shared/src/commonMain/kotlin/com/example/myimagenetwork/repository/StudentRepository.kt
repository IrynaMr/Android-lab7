package com.example.myimagenetwork.repository

import com.example.myimagenetwork.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okio.IOException

class StudentRepository {
    private var shouldFail = false

    suspend fun getStudents(): List<Student> {
        return withContext(Dispatchers.Default) {
            delay(1500) // Симулюємо очікування відповіді від сервера
            shouldFail = !shouldFail
            if (shouldFail) {
                // Штучно викликаємо помилку мережі для демонстрації
                throw IOException("No internet connection")
            }
            // Повертаємо список із реальними ID картинок із сервісу Lorem Picsum
            listOf(
                Student(1, "Іван", "Петренко", "ІП-21", avatarId = 1012),
                Student(2, "Марія", "Ковальчук", "ІП-22", avatarId = 1026),
                Student(3, "Олег", "Сидоренко", "ІП-21", avatarId = 65),
                Student(4, "Анна", "Ткаченко", "ІП-22", avatarId = 339),
                Student(5, "Дмитро", "Козак", "ІП-23", avatarId = 238)
            )
        }
    }
}