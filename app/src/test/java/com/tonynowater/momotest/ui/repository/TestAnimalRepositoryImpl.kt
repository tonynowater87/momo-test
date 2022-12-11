package com.tonynowater.momotest.ui.repository

import com.tonynowater.momotest.data.model.ui.AnimalCatalogDetailModel
import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel
import com.tonynowater.momotest.data.model.ui.AnimalDetailModel
import com.tonynowater.momotest.data.repository.AnimaRepository

abstract class TestAnimalRepositoryImpl : AnimaRepository {

    override suspend fun getCategoryList(): List<AnimalCatalogModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimalDetail(animalId: Int): AnimalDetailModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryDetail(catalogId: Int): AnimalCatalogDetailModel? {
        TODO("Not yet implemented")
    }
}
