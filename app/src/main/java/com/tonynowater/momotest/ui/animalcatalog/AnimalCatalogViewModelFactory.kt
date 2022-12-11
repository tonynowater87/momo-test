package com.tonynowater.momotest.ui.animalcatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tonynowater.momotest.data.repository.AnimaRepository

class AnimalCatalogViewModelFactory(private val animalRepository: AnimaRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalCatalogViewModel(animalRepository) as T
    }
}