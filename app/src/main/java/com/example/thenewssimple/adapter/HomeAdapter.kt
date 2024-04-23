package com.example.thenewssimple.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thenewssimple.databinding.ItemNewsBinding
import com.example.thenewssimple.models.Article
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeAdapter(private var news: List<Article>) :
    RecyclerView.Adapter<HomeAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(article: Article){
                binding.articleSource.text = article.source?.name
                binding.articleTitle.text = article.title
                binding.articleDescription.text = article.description


                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = inputFormat.parse(article.publishedAt)
                val formattedDate = outputFormat.format(date ?: Date())

                binding.articleDateTime.text = formattedDate

                Glide.with(binding.root)
                    .load(article.urlToImage)
                    .into(binding.articleImage)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return NewsViewHolder (binding)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news [position])
    }

    fun setData(newsList: List<Article>){
        this.news = newsList
        notifyDataSetChanged()
    }


}