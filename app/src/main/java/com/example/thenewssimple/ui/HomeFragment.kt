package com.example.thenewssimple.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thenewssimple.R
import com.example.thenewssimple.adapter.HomeAdapter
import com.example.thenewssimple.databinding.FragmentHomeBinding
import com.example.thenewssimple.models.Article
import com.example.thenewssimple.repository.NewsRepository
import com.example.thenewssimple.viewmodel.HomeViewModel
import com.example.thenewssimple.viewmodel.HomeViewModelFactory
import com.example.thenewssimple.viewmodel.Resource


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter
    private var newList: List<Article> = mutableListOf()
    private lateinit var repository: NewsRepository
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView = binding.recyclerview

        repository = NewsRepository()
        adapter =
            HomeAdapter(newList) // Inicialize o adapter com uma lista vazia ou com os dados iniciais
        recyclerView.adapter = adapter // Configure o adapter para o RecyclerView

        // Cria o HomeViewModel com HomeViewModelFactory e passa NewsRepository como argumento
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(NewsRepository())
        ).get(HomeViewModel::class.java)

        observeNewsResponse()
    }



    private fun observeNewsResponse() {
        viewModel.newsResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Exibir um indicador de carregamento se necessário
                }

                is Resource.Success -> {
                    adapter.setData(
                        resource.data as? List<Article> ?: emptyList()
                    ) // Defina os dados no adaptador quando estiverem disponíveis
                }

                is Resource.Error -> {
                    // Exibir uma mensagem de erro se necessário
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
