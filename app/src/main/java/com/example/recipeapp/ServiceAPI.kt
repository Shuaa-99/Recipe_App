package com.example.recipeapp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {
    @GET("recipes/")
    fun getAPIUsers(): Call<Users>

    @POST("recipes/")
    fun addUser(@Body userData: UsersItem): Call<UsersItem>
}