package com.tonynowater.momotest.data.repository

import com.tonynowater.momotest.data.datasource.network.NetworkDatasource
import com.tonynowater.momotest.data.model.dto.AnimalDetailListDTO
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
        val catalogDetail = catalog.firstOrNull { it.id == catalogId } ?: return null
        val animaDetail = detailList.filter { it.id == catalogId }.let {
            mapperToAnimalDetailModel(it)
        }
        return AnimalCatalogDetailModel(
            id = catalogDetail.id,
            eName = catalogDetail.eName,
            eInfo = catalogDetail.eInfo,
            eMemo = catalogDetail.eMemo,
            ePictureUrl = catalogDetail.ePicUrl,
            eLinkUrl = catalogDetail.eUrl,
            animals = animaDetail
        )
    }

    override suspend fun getAnimalDetail(animalId: Int): AnimalDetailModel? {
        val detailList = networkDatasource.getAnimalDetailList().result.results.let {
            mapperToAnimalDetailModel(it)
        }
        return detailList.firstOrNull { it.id == animalId }
    }

    private fun mapperToAnimalDetailModel(result: List<AnimalDetailListDTO.Result.Result>): List<AnimalDetailModel> {
        return result.map {
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
    }
}