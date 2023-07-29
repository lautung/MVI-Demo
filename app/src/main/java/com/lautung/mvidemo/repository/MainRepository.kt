package com.lautung.mvidemo.repository

import com.lautung.mvidemo.model.Wallpaper
import com.lautung.mvidemo.network.RetrofitUtils
import com.lautung.mvidemo.network.WallpaperApiService

/**
 * @Auther: lautung
 * @datetime: 2023/7/29
 * @desc:
 */
class MainRepository(
    private val wallpaperApiService: WallpaperApiService = RetrofitUtils.retrofit.create(
        WallpaperApiService::class.java
    )
) {

    suspend fun fetchWallpapers(): Wallpaper = wallpaperApiService.fetchWallpaper()

}