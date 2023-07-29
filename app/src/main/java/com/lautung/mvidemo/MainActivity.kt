package com.lautung.mvidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.lautung.mvidemo.adapter.WallpaperAdapter
import com.lautung.mvidemo.data.intent.MainIntent
import com.lautung.mvidemo.data.state.MainState
import com.lautung.mvidemo.databinding.ActivityMainBinding
import com.lautung.mvidemo.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val wallPaperAdapter by lazy {
        WallpaperAdapter(ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvWallpaper.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = wallPaperAdapter
        }
        binding.btnGetWallpaper.setOnClickListener {
            lifecycleScope.launch {
                viewModel.mainIntentChannel.send(MainIntent.FetchWallpaper)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //状态收集
                viewModel.state.collect {
                    when (it) {
                        is MainState.Idle -> {

                        }

                        is MainState.Loading -> {
                            binding.btnGetWallpaper.visibility = View.GONE
                            binding.pbLoading.visibility = View.VISIBLE
                        }

                        is MainState.Wallpapers -> {     //数据返回
                            binding.btnGetWallpaper.visibility = View.GONE
                            binding.pbLoading.visibility = View.GONE

                            binding.rvWallpaper.visibility = View.VISIBLE
                            it.wallpaper.let { paper ->
                                wallPaperAdapter.addData(paper.res.vertical)
                            }

                        }

                        is MainState.Error -> {
                            binding.pbLoading.visibility = View.GONE
                            binding.btnGetWallpaper.visibility = View.VISIBLE
                            Log.e("TAG", "observeViewModel: $it.error")
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

    }
}