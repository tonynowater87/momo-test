package com.tonynowater.momotest.ui.animalcatalog

import androidx.lifecycle.ViewModel
import com.tonynowater.momotest.data.repository.AnimaRepository

class AnimalCatalogViewModel(private val animaRepository: AnimaRepository) : ViewModel() {

    init {
        println("repo = $animaRepository")
    }

    fun load() {

    }
}