package com.example.myimagenetwork.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myimagenetwork.exceptions.AppError
import com.example.myimagenetwork.model.Student
import com.example.myimagenetwork.repository.StudentRepository
import com.example.myimagenetwork.viewmodels.state.StudentUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = StudentRepository()
    private var cachedStudents: List<Student> = emptyList()

    private val _uiState = MutableStateFlow<StudentUiState>(StudentUiState.Loading)
    val uiState: StateFlow<StudentUiState> = _uiState

    init {
        loadStudents()
    }

    fun loadStudents() {
        viewModelScope.launch {
            _uiState.value = StudentUiState.Loading
            try {
                val data = repository.getStudents()
                cachedStudents = data
                _uiState.value = StudentUiState.Success(data)
            } catch (e: Exception) {
                val appError = if (e.message?.contains("internet") == true) {
                    AppError.NoInternet
                } else {
                    AppError.ServerError
                }
                _uiState.value = StudentUiState.Error(appError)
            }
        }
    }

    fun selectStudent(student: Student) {
        _uiState.value = StudentUiState.Detail(student = student)
    }

    fun resetSelection() {
        _uiState.value = StudentUiState.Success(cachedStudents)
    }
}