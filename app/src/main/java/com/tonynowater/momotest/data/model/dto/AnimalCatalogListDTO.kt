package com.tonynowater.momotest.data.model.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimalCatalogListDTO(
    @SerializedName("result")
    val result: Result
) {
    @Keep
    data class Result(
        @SerializedName("count")
        val count: Int,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("results")
        val results: List<Result>,
        @SerializedName("sort")
        val sort: String
    ) {
        @Keep
        data class Result(
            @SerializedName("e_category")
            val eCategory: String,
            @SerializedName("e_geo")
            val eGeo: String,
            @SerializedName("e_info")
            val eInfo: String,
            @SerializedName("e_memo")
            val eMemo: String,
            @SerializedName("e_name")
            val eName: String,
            @SerializedName("e_no")
            val eNo: String,
            @SerializedName("e_pic_url")
            val ePicUrl: String,
            @SerializedName("e_url")
            val eUrl: String,
            @SerializedName("_id")
            val id: Int,
            @SerializedName("_importdate")
            val importdate: Importdate
        ) {
            @Keep
            data class Importdate(
                @SerializedName("date")
                val date: String,
                @SerializedName("timezone")
                val timezone: String,
                @SerializedName("timezone_type")
                val timezoneType: Int
            )
        }
    }
}