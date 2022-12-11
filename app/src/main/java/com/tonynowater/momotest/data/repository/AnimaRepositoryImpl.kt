package com.tonynowater.momotest.data.repository

import com.tonynowater.momotest.data.datasource.network.NetworkDatasource
import com.tonynowater.momotest.data.model.ui.AnimalCatalogDetailModel
import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel
import com.tonynowater.momotest.data.model.ui.AnimalDetailModel

// TODO decrease network requests
// TODO offline mode support
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

    override suspend fun getCategoryDetail(catalogId: Int): AnimalCatalogDetailModel? {
        val catalog = networkDatasource.getAnimalCatalogList().result.results
        val detailList = networkDatasource.getAnimalDetailList().result.results
        val animaDetail = detailList.firstOrNull { it.id == catalogId } ?: return null
        val catalogDetail = catalog.firstOrNull { it.id == catalogId } ?: return null
        return AnimalCatalogDetailModel(
            id = catalogDetail.id,
            eName = catalogDetail.eName,
            eInfo = catalogDetail.eInfo,
            eMemo = catalogDetail.eMemo,
            ePictureUrl = catalogDetail.ePicUrl,
            eLinkUrl = catalogDetail.eUrl,
            aNameCh = animaDetail.aNameCh,
            aPicture1Url = animaDetail.aPic01Url,
            aAlsoKnown = animaDetail.aAlsoknown
        )
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