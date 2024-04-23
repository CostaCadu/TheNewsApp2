
package com.example.thenewssimple.repository

import com.example.thenewssimple.api.RetrofitInstance
import com.example.thenewssimple.models.NewsResponse
import retrofit2.Response

class NewsRepository {

    suspend fun getAllNews(): Response<NewsResponse> {
        return RetrofitInstance.api.getHeadLines()
    }
}
