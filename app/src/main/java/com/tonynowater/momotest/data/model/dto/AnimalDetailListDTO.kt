package com.tonynowater.momotest.data.model.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AnimalDetailListDTO(
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
            @SerializedName("a_adopt")
            val aAdopt: String,
            @SerializedName("a_alsoknown")
            val aAlsoknown: String,
            @SerializedName("a_behavior")
            val aBehavior: String,
            @SerializedName("a_cid")
            val aCid: String,
            @SerializedName("a_class")
            val aClass: String,
            @SerializedName("a_code")
            val aCode: String,
            @SerializedName("a_conservation")
            val aConservation: String,
            @SerializedName("a_crisis")
            val aCrisis: String,
            @SerializedName("a_diet")
            val aDiet: String,
            @SerializedName("a_distribution")
            val aDistribution: String,
            @SerializedName("a_family")
            val aFamily: String,
            @SerializedName("a_feature")
            val aFeature: String,
            @SerializedName("a_geo")
            val aGeo: String,
            @SerializedName("a_habitat")
            val aHabitat: String,
            @SerializedName("a_interpretation")
            val aInterpretation: String,
            @SerializedName("a_keywords")
            val aKeywords: String,
            @SerializedName("a_location")
            val aLocation: String,
            @SerializedName("a_name_ch")
            val aNameCh: String,
            @SerializedName("a_name_en")
            val aNameEn: String,
            @SerializedName("a_name_latin")
            val aNameLatin: String,
            @SerializedName("a_order")
            val aOrder: String,
            @SerializedName("a_pdf01_alt")
            val aPdf01Alt: String,
            @SerializedName("a_pdf01_url")
            val aPdf01Url: String,
            @SerializedName("a_pdf02_alt")
            val aPdf02Alt: String,
            @SerializedName("a_pdf02_url")
            val aPdf02Url: String,
            @SerializedName("a_phylum")
            val aPhylum: String,
            @SerializedName("a_pic01_alt")
            val aPic01Alt: String,
            @SerializedName("a_pic01_url")
            val aPic01Url: String,
            @SerializedName("a_pic02_alt")
            val aPic02Alt: String,
            @SerializedName("a_pic02_url")
            val aPic02Url: String,
            @SerializedName("a_pic03_alt")
            val aPic03Alt: String,
            @SerializedName("a_pic03_url")
            val aPic03Url: String,
            @SerializedName("a_pic04_alt")
            val aPic04Alt: String,
            @SerializedName("a_pic04_url")
            val aPic04Url: String,
            @SerializedName("a_poigroup")
            val aPoigroup: String,
            @SerializedName("a_summary")
            val aSummary: String,
            @SerializedName("a_theme_name")
            val aThemeName: String,
            @SerializedName("a_theme_url")
            val aThemeUrl: String,
            @SerializedName("a_update")
            val aUpdate: String,
            @SerializedName("a_vedio_url")
            val aVedioUrl: String,
            @SerializedName("a_voice01_alt")
            val aVoice01Alt: String,
            @SerializedName("a_voice01_url")
            val aVoice01Url: String,
            @SerializedName("a_voice02_alt")
            val aVoice02Alt: String,
            @SerializedName("a_voice02_url")
            val aVoice02Url: String,
            @SerializedName("a_voice03_alt")
            val aVoice03Alt: String,
            @SerializedName("a_voice03_url")
            val aVoice03Url: String,
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