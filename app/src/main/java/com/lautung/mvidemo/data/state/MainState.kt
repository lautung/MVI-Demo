package com.lautung.mvidemo.data.state

import com.lautung.mvidemo.model.Wallpaper

/**
 * @Auther: lautung
 * @datetime: 2023/7/29
 * @desc:
 */
sealed class MainState{
    /**
     * 空闲
     */
    object Idle : MainState()

    /**
     * 加载
     */
    object Loading : MainState()

    /**
     * 获取壁纸
     */
    data class Wallpapers(val wallpaper: Wallpaper) : MainState()

    /**
     * 错误信息
     */
    data class Error(val error: String) : MainState()

}
