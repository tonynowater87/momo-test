package com.tonynowater.momotest.data.repository

import com.tonynowater.momotest.data.datasource.network.NetworkDatasource
import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel
import com.tonynowater.momotest.data.model.ui.AnimalDetailModel

class AnimaRepositoryImpl(private val networkDatasource: NetworkDatasource) : AnimaRepository {

    override suspend fun getCategoryList(): List<AnimalCatalogModel> {
        return networkDatasource.getAnimalCatalogList().result.results.map {
            AnimalCatalogModel(
                id = it.id,
                eName = it.eName,
                eInfo = it.eInfo,
                eMemo = it.eMemo,
                ePictureUrl = it.ePicUrl
            )
        }
    }

    override suspend fun getCategoryDetail(catalogId: Int): AnimalCatalogModel? {
        val categoryList = getCategoryList()
        return categoryList.firstOrNull { it.id == catalogId }
    }

    override suspend fun getAnimalDetail(animalId: Int): AnimalDetailModel? {
        val detailList = networkDatasource.getAnimalDetailList().result.results.map {
            AnimalDetailModel(
                id = it.id,
                aNameCh = it.aNameCh,
                aNameEn = it.aNameEn,
                aPicture1Url = it.aPic01Url,
                aAlsoKnown = it.aAlsoknown,
                aBehavior = it.aBehavior,
                aFeature = it.aFeature,
                aImportantDate = it.importdate.date
            )
        }

        return detailList.firstOrNull { it.id == animalId }
    }
}