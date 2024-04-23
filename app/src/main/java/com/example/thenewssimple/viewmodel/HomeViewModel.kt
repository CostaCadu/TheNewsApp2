package com.example.thenewssimple.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenewssimple.models.Article
import com.example.thenewssimple.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private var _newsResponse = MutableLiveData<Resource<List<Article>>>()
    val newsResponse: LiveData<Resource<List<Article>>> = _newsResponse

    init {
        fetchNews()
    }

    //buscar notícias fetch = buscar
    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsResponse.postValue(Resource.Loading(null)) // Indica que estamos carregando os dados

            //O try-catch é uma estrutura em muitas linguagens de programação que permite lidar
            // com exceções, ou seja, erros que podem ocorrer durante a execução do programa.

            try {
                val response = newsRepository.getAllNews()
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    if (articles != null) {
                        _newsResponse.postValue(Resource.Success(articles))
                    } else {
                        _newsResponse.postValue(Resource.Error("Empty response", null))
                    }
                } else {
                    _newsResponse.postValue(Resource.Error("Error: ${response.code()}", null))
                }
            } catch (e: Exception) {
                _newsResponse.postValue(Resource.Error("Something went wrong: ${e.message}", null))
            }
        }
    }

}

