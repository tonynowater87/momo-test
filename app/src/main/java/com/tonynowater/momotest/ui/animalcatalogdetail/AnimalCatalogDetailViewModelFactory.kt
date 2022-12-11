package com.tonynowater.momotest.ui.animalcatalogdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tonynowater.momotest.data.repository.AnimaRepository

class AnimalCatalogDetailViewModelFactory(private val animalRepository: AnimaRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnimalCatalogDetailViewModel(animalRepository) as T
    }
}