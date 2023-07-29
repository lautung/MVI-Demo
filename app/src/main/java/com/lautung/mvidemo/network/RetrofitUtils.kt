package com.lautung.mvidemo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

/**
 * @Auther: lautung
 * @datetime: 2023/7/29
 * @desc:
 */
object RetrofitUtils {

    private const val BASE_URL = "http://service.picasso.adesk.com"


    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
}