package com.example.myimagenetwork.viewmodels.state

import com.example.myimagenetwork.exceptions.AppError
import com.example.myimagenetwork.model.Student

sealed interface StudentUiState {
    data object Loading : StudentUiState
    data class Success(val students: List<Student>) : StudentUiState
    data class Error(val error: AppError) : StudentUiState
    data class Detail(val student: Student) : StudentUiState
}