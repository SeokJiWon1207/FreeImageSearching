package com.example.freeimagesearching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freeimagesearching.data.Repository
import com.example.freeimagesearching.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        fetchRandomPhotos()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false )
        binding.recyclerView.adapter = PhotoAdapter()
    }


    // Unsplash API의 랜덤 이미지 Mapping
   private fun fetchRandomPhotos(query: String? = null) = scope.launch {
        try {
            Repository.getRandomPhotos(query)?.let { photos ->
                (binding.recyclerView.adapter as? PhotoAdapter)?.apply {
                    this.photos = photos
                    notifyDataSetChanged()
                }
            }
        binding.recyclerView.visibility = View.VISIBLE
    } catch (exception: Exception) {
        binding.recyclerView.visibility = View.INVISIBLE
        binding.errorDescriptionTextView.visibility = View.VISIBLE
    } finally {
        binding.shimmerLayout.visibility = View.GONE
        binding.refreshLayout.isRefreshing = false
    }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}