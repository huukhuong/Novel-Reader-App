package com.huukhuongit.novelreader.network

import com.huukhuongit.novelreader.models.BannerModel
import com.huukhuongit.novelreader.models.CategoryModel
import com.huukhuongit.novelreader.models.NovelModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("getAllCategories.php")
    fun getAllCategory() : Call<ArrayList<CategoryModel>>

    @GET("getByCategoryId.php")
    fun getByCategoryId(@Query("id") id: Int?): Call<CategoryModel>

    @GET("getByNovelId.php")
    fun getByNovelId(@Query("id") id: Int?): Call<NovelModel>

    @GET("getListBanners.php")
    fun getListBanners(): Call<ArrayList<BannerModel>>

    @GET("getTop5Novels.php")
    fun getTop5Novels(): Call<ArrayList<NovelModel>>

    @GET("getTopPopularNovels.php")
    fun getTopPopularNovels(): Call<ArrayList<NovelModel>>

    @GET("getTopRecommendedNovels.php")
    fun getTopRecommendedNovels(): Call<ArrayList<NovelModel>>

}
