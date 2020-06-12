package com.peterstev.peterstevuremgba.network

import com.peterstev.peterstevuremgba.models.UserAccount
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("accounts")
    fun getFilters(): Call<List<UserAccount>>
}