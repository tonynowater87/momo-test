package com.tonynowater.momotest.ui.animalcatalogdetail

import androidx.lifecycle.ViewModel
import com.tonynowater.momotest.data.repository.AnimaRepository

class AnimalCatalogDetailViewModel(private val animaRepository: AnimaRepository) : ViewModel() {

    init {
        println("repo = $animaRepository")
    }

    fun load() {

    }
}