package com.example.myimagenetwork.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myimagenetwork.exceptions.AppError
import com.example.myimagenetwork.model.Student
import com.example.myimagenetwork.viewmodels.state.StudentUiState

@Composable
fun StudentListScreen(
    uiState: StudentUiState,
    onStudentClick: (Student) -> Unit,
    onRetryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (uiState) {
                            is StudentUiState.Detail -> "Деталі студента"
                            else -> "Список студентів"
                        }
                    )
                },
                navigationIcon = {
                    if (uiState is StudentUiState.Detail) {
                        TextButton(onClick = onBackClick) {
                            Text("Назад")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is StudentUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is StudentUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(uiState.students) { student ->
                            StudentCard(
                                student = student,
                                onClick = { onStudentClick(student) }
                            )
                        }
                    }
                }
                is StudentUiState.Error -> {
                    ErrorView(
                        error = uiState.error,
                        onRetry = onRetryClick
                    )
                }
                is StudentUiState.Detail -> {
                    StudentDetailContent(student = uiState.student)
                }
            }
        }
    }
}

@Composable
fun ErrorView(
    error: AppError,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error.message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Спробувати знову")
        }
    }
}

@Composable
fun StudentDetailContent(student: Student) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StudentAvatar(
            url = student.avatarUrl,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${student.firstName} ${student.lastName}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Група: ${student.group}",
            style = MaterialTheme.typography.titleMedium
        )
    }
}