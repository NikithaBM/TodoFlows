package com.example.todoflows.data.repository

import com.example.todoflows.data.RetrofitInstance
import com.example.todoflows.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TodoRepository {
    private val apiService = RetrofitInstance.apiService

    fun fetchTodos(): Flow<List<Todo>> = flow {
        // Emit the result of the API call
        val todos = apiService.getTodos()
        emit(todos)
    }.flowOn(Dispatchers.IO) // Run the flow on IO thread for network operation
}
