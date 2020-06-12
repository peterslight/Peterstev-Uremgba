package com.peterstev.peterstevuremgba.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class
UserAccount(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("countries")
    val countries: List<String>,
    @SerializedName("colors")
    val colors: List<String>
) : Serializable {
    override fun toString(): String {
        return "UserAccount(createdAt='$createdAt', gender='$gender', fullName='$fullName',avatar='$avatar', countries=$countries, colors=$colors)"
    }
}