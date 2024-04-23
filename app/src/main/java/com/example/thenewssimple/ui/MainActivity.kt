package com.example.thenewssimple.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thenewssimple.R
import com.example.thenewssimple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.homeFragment, HomeFragment()) // Substitua R.id.fragment_container pelo ID do contêiner em seu layout
                commit()
            }
        }
    }
}
