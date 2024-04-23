package com.example.thenewssimple.api

import com.example.thenewssimple.models.Article

interface OnItemClickListener {
    fun onItemClick(article: Article)
}