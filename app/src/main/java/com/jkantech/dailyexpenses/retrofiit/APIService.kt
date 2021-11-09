package com.jkantech.dailyexpenses.retrofiit

import com.jkantech.dailyexpenses.data.LoginResponseData
import com.jkantech.dailyexpenses.utils.Constants.Companion.baseURLAPI
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import kotlin.collections.HashMap

interface APIService {

    //Login
    @POST("auth")
    fun login(@QueryMap params: HashMap<String?, String?>): Call<LoginResponseData>

    //Home
    /*@GET("home")
    fun  home(@Header("Authorization") token: String,@QueryMap params: HashMap<String?, String?>):Call<ResponseDataDetail<HomeData>>
*/


    companion object {
        operator fun invoke(): APIService {
            return Retrofit.Builder()
                .baseUrl(baseURLAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }
}