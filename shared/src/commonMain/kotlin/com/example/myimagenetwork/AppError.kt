package com.example.myimagenetwork.exceptions

sealed class AppError(open val message: String) {
    data object NoInternet : AppError("Відсутнє підключення до інтернету. Перевірте мережу.")
    data object ServerError : AppError("Помилка сервера (500). Спробуйте пізніше.")
    data class Unknown(override val message: String) : AppError(message)
}
