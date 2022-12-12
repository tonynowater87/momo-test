package com.tonynowater.momotest.data.model.ui

data class AnimalCatalogDetailModel(
    val id: Int,
    val eName: String,
    val eInfo: String,
    val eMemo: String,
    val ePictureUrl: String,
    val eLinkUrl: String,
    val animals: List<AnimalDetailModel>
)
