package com.example.freeimagesearching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.freeimagesearching.data.Repository
import com.example.freeimagesearching.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchRandomPhotos()
    }

    // Unsplash API의 랜덤 이미지 Mapping
   private fun fetchRandomPhotos(query: String? = null) = scope.launch {
            Repository.getRandomPhotos(query)?.let { photos ->
                photos
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}