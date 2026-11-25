package com.example.myimagenetwork

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.myimagenetwork.ui.StudentListScreen
import com.example.myimagenetwork.viewmodels.StudentViewModel

@Composable
fun App() {
    val viewModel = StudentViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        StudentListScreen(
            uiState = uiState,
            onStudentClick = { student ->
                viewModel.selectStudent(student)
            },
            onRetryClick = {
                viewModel.loadStudents()
            },
            onBackClick = {
                viewModel.resetSelection()
            }
        )
    }
}