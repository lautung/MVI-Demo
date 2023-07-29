package com.lautung.mvidemo.network

import com.lautung.mvidemo.model.Wallpaper
import retrofit2.http.GET

/**
 * @Auther: lautung
 * @datetime: 2023/7/29
 * @desc:
 */
interface WallpaperApiService {

    @GET("/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    suspend fun fetchWallpaper(): Wallpaper
}