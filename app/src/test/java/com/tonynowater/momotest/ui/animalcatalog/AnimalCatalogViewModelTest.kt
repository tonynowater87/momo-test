package com.tonynowater.momotest.ui.animalcatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel
import com.tonynowater.momotest.ui.repository.TestAnimalRepositoryImpl
import com.tonynowater.momotest.ui.util.MainDispatcherRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test

class AnimalCatalogViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AnimalCatalogViewModel

    @Test
    fun `Test Loading to Success(empty)`() {
        // Arrange
        viewModel = AnimalCatalogViewModel(animaRepository = object : TestAnimalRepositoryImpl() {
            override suspend fun getCategoryList(): List<AnimalCatalogModel> {
                delay(1000)
                return emptyList()
            }
        })

        // Action
        viewModel.load()

        // Assert
        assertTrue(viewModel.uiState.value is AnimalCatalogUiState.Loading)
        dispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(AnimalCatalogUiState.Success(data = emptyList()), viewModel.uiState.value)
    }


    @Test
    fun `Test Loading to Error`() {
        // Arrange
        viewModel = AnimalCatalogViewModel(animaRepository = object : TestAnimalRepositoryImpl() {
            override suspend fun getCategoryList(): List<AnimalCatalogModel> {
                delay(1000)
                throw NullPointerException()
            }
        })

        // Action
        viewModel.load()

        // Assert
        assertTrue(viewModel.uiState.value is AnimalCatalogUiState.Loading)
        dispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(viewModel.uiState.value is AnimalCatalogUiState.Error)
    }
}