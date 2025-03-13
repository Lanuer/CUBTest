package com.example.cubtest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attraction(
    @SerializedName("name")
    val name: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("address")
    val address: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("official_site")
    val officialSite: String?
) : Parcelable