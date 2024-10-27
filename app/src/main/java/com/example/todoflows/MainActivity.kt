package com.example.todoflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoflows.data.repository.TodoRepository
import com.example.todoflows.model.Todo
import com.example.todoflows.ui.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by lazy {
        TodoViewModel(TodoRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListScreen(todoViewModel)
        }
    }

    @Composable
    fun TodoListScreen(viewModel: TodoViewModel) {
        val todoListState = remember { mutableStateOf(emptyList<Todo>()) }

        // Collect the Flow in the UI
        LaunchedEffect(Unit) {
            viewModel.todos.collect { todos ->
                todoListState.value = todos
            }
        }

        // Display the to-do list
        Column(modifier = Modifier.padding(16.dp)) {
            Text("To-Do List", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(todoListState.value) { todo ->
                    TodoItem(todo)
                }
            }
        }
    }

    @Composable
    fun TodoItem(todo: Todo) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Title: ${todo.title}")
            Text("Completed: ${todo.completed}")
            Divider(modifier = Modifier.padding(vertical = 4.dp))
        }
    }

}
