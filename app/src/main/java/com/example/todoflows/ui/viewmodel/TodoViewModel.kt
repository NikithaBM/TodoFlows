package com.example.todoflows.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoflows.data.repository.TodoRepository
import com.example.todoflows.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            repository.fetchTodos().collect { todoList ->
                _todos.value = todoList
            }
        }
    }
}