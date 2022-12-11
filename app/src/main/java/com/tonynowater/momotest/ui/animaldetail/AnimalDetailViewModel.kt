package com.tonynowater.momotest.ui.animaldetail

import androidx.lifecycle.ViewModel
import com.tonynowater.momotest.data.repository.AnimaRepository

class AnimalDetailViewModel(private val animaRepository: AnimaRepository) : ViewModel() {

    init {
        println("repo = $animaRepository")
    }

    fun load() {

    }
}