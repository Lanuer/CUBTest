package com.example.cubtest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttractionResponse(
    @SerializedName("data")
    val attractions: List<Attraction>
) : Parcelable