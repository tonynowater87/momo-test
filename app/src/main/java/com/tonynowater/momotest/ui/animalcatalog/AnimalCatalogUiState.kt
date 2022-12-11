package com.tonynowater.momotest.ui.animalcatalog

import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel

sealed interface AnimalCatalogUiState {

    object Loading : AnimalCatalogUiState

    data class Success(
        val data: List<AnimalCatalogModel>
    ) : AnimalCatalogUiState

    data class Error(
        val error: Throwable
    ) : AnimalCatalogUiState
}