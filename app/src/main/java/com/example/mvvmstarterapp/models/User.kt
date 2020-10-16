package com.example.mvvmstarterapp.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("emai")
    val email: String? = "",
    @SerializedName("website")
    val website: String? = ""
)