package com.peterstev.peterstevuremgba.models

import com.google.gson.annotations.SerializedName

data class FilterResult(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("country") val country: String,
    @SerializedName("car_model") val carModel: String,
    @SerializedName("car_model_year") val carModelYear: Int,
    @SerializedName("car_color") val carColor: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("job_title") val jobTitle: String,
    @SerializedName("bio") val bio: String
) {
    override fun toString(): String {
        return "id: $id name: $firstName $lastName email: $email country: $country model: $carColor $carModelYear $carModel work: $gender $country \n" +
                "bio: $bio"
    }
}