package com.tonynowater.momotest.ui.animaldetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tonynowater.momotest.data.repository.AnimaRepository

class AnimalDetailViewModelFactory(private val animalRepository: AnimaRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalDetailViewModel(animalRepository) as T
    }
}