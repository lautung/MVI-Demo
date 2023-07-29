package com.lautung.mvidemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lautung.mvidemo.data.intent.MainIntent
import com.lautung.mvidemo.data.state.MainState
import com.lautung.mvidemo.network.RetrofitUtils
import com.lautung.mvidemo.network.WallpaperApiService
import com.lautung.mvidemo.repository.MainRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * @Auther: lautung
 * @datetime: 2023/7/29
 * @desc:
 */
class MainViewModel(private val repository: MainRepository = MainRepository()) : ViewModel() {

    //创建意图管道，容量无限大
    val mainIntentChannel = Channel<MainIntent>(Channel.UNLIMITED)

    //可变状态数据流
    private val _state = MutableStateFlow<MainState>(MainState.Idle)

    //可观察状态数据流
    val state: StateFlow<MainState> get() = _state

    init {
        viewModelScope.launch {
            mainIntentChannel.consumeAsFlow().collect {
                when (it) {
                    //发现意图为获取壁纸
                    is MainIntent.FetchWallpaper -> getWallpaper()
                    else -> {}
                }
            }
        }
    }

    private fun getWallpaper() {

        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Wallpapers(repository.fetchWallpapers())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage ?: "UnKnown Error")
            }
        }
    }

}