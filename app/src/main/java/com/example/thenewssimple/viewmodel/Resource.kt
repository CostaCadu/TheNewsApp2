package com.example.thenewssimple.viewmodel

import com.example.thenewssimple.models.Article

sealed class Resource<T>(
    val data: Any? = null,
    val message: String? = null
){
    class Success<T>(data: List<Article?>?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(nothing: Nothing?) : Resource<T>()
}