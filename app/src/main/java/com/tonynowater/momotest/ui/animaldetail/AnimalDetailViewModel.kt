package com.tonynowater.momotest.ui.animaldetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonynowater.momotest.data.repository.AnimaRepository
import kotlinx.coroutines.launch

class AnimalDetailViewModel(private val animaRepository: AnimaRepository) : ViewModel() {

    private val _uiState = MutableLiveData<AnimalDetailUiState>()
    val uiState: LiveData<AnimalDetailUiState> = _uiState

    fun load(animalId: Int) {
        viewModelScope.launch {
            _uiState.value = AnimalDetailUiState.Loading
            try {
                val data = animaRepository.getAnimalDetail(animalId = animalId)!!
                _uiState.value = AnimalDetailUiState.Success(data = data)
            } catch (error: Exception) {
                _uiState.value = AnimalDetailUiState.Error(error = error)
            }
        }
    }
}