package com.example.recipeapp
data class UsersItem(
    val title: String,
    val author: String,
    val ingredients: String,
    val instructions: String,
    val pk : Int,
    var expandeble:Boolean = false,

)