package com.tonynowater.momotest.data.repository

import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel
import com.tonynowater.momotest.data.model.ui.AnimalDetailModel

interface AnimaRepository {
    suspend fun getCategoryList(): List<AnimalCatalogModel>
    suspend fun getCategoryDetail(catalogId: Int): AnimalCatalogModel?
    suspend fun getAnimalDetail(animalId: Int): AnimalDetailModel?
}