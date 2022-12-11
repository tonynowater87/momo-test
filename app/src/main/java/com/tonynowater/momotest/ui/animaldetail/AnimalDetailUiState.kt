package com.tonynowater.momotest.ui.animaldetail

import com.tonynowater.momotest.data.model.ui.AnimalDetailModel

sealed interface AnimalDetailUiState {

    object Loading : AnimalDetailUiState

    data class Success(
        val data: AnimalDetailModel
    ) : AnimalDetailUiState

    data class Error(
        val error: Throwable
    ) : AnimalDetailUiState
}