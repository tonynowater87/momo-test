package com.tonynowater.momotest.ui.animalcatalogdetail

import com.tonynowater.momotest.data.model.ui.AnimalCatalogDetailModel

sealed interface AnimalCatalogDetailUiState {

    object Loading : AnimalCatalogDetailUiState

    data class Success(
        val data: AnimalCatalogDetailModel
    ) : AnimalCatalogDetailUiState

    data class Error(
        val error: Throwable
    ) : AnimalCatalogDetailUiState
}