package com.tonynowater.momotest.ui.animalcatalogdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonynowater.momotest.data.repository.AnimaRepository
import kotlinx.coroutines.launch

class AnimalCatalogDetailViewModel(private val animaRepository: AnimaRepository) : ViewModel() {

    private val _uiState = MutableLiveData<AnimalCatalogDetailUiState>()
    val uiState: LiveData<AnimalCatalogDetailUiState> = _uiState

    fun load(catalogId: Int) {
        viewModelScope.launch {
            _uiState.value = AnimalCatalogDetailUiState.Loading
            try {
                val data = animaRepository.getCategoryDetail(catalogId = catalogId)!!
                _uiState.value = AnimalCatalogDetailUiState.Success(data = data)
            } catch (error: Exception) {
                _uiState.value = AnimalCatalogDetailUiState.Error(error = error)
            }
        }
    }
}