package com.tonynowater.momotest.data.datasource.network

import com.tonynowater.momotest.data.model.dto.AnimalCatalogListDTO
import com.tonynowater.momotest.data.model.dto.AnimalDetailListDTO

interface NetworkDatasource {
    suspend fun getAnimalCatalogList(): AnimalCatalogListDTO
    suspend fun getAnimalDetailList(): AnimalDetailListDTO
}