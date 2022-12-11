package com.tonynowater.momotest.ui.animalcatalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonynowater.momotest.data.repository.AnimaRepository
import kotlinx.coroutines.launch

class AnimalCatalogViewModel(private val animaRepository: AnimaRepository) : ViewModel() {

    private val _uiState = MutableLiveData<AnimalCatalogUiState>()
    val uiState: LiveData<AnimalCatalogUiState> = _uiState

    fun load() {
        viewModelScope.launch {
            _uiState.value = AnimalCatalogUiState.Loading
            try {
                val catalog = animaRepository.getCategoryList()
                _uiState.value = AnimalCatalogUiState.Success(data = catalog)
            } catch (error: Exception) {
                _uiState.value = AnimalCatalogUiState.Error(error = error)
            }
        }
    }
}