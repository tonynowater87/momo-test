package com.tonynowater.momotest.ui.animalcatalogdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tonynowater.momotest.data.model.ui.AnimalDetailModel
import com.tonynowater.momotest.ui.animaldetail.AnimalDetailUiState
import com.tonynowater.momotest.ui.animaldetail.AnimalDetailViewModel
import com.tonynowater.momotest.ui.repository.TestAnimalRepositoryImpl
import com.tonynowater.momotest.ui.util.MainDispatcherRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test

class AnimalDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AnimalDetailViewModel

    @Test
    fun `Test Loading to Success`() {
        // Arrange
        val animalId = 1
        val animalDetailModel = AnimalDetailModel(
            id = 1,
            aNameCh = "aNameCh",
            aNameEn = "aNameEn",
            aPicture1Url = "aPicture1Url",
            aAlsoKnown = "aAlsoKnown",
            aFeature = "aFeature",
            aBehavior = "aBehavior",
            aImportantDate = "aImportantDate"
        )
        viewModel = AnimalDetailViewModel(animaRepository = object : TestAnimalRepositoryImpl() {
            override suspend fun getAnimalDetail(animalId: Int): AnimalDetailModel? {
                delay(1000)
                return animalDetailModel
            }
        })

        // Action
        viewModel.load(animalId = animalId)

        // Assert
        assertTrue(viewModel.uiState.value is AnimalDetailUiState.Loading)
        dispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            AnimalDetailUiState.Success(data = animalDetailModel).data.id, 1
        )
        assertEquals(
            AnimalDetailUiState.Success(data = animalDetailModel).data.aImportantDate,
            "aImportantDate"
        )
        // ...
    }

    @Test
    fun `Test Loading to Error`() {
        // Arrange
        val animalId = 1
        viewModel = AnimalDetailViewModel(animaRepository = object : TestAnimalRepositoryImpl() {
            override suspend fun getAnimalDetail(animalId: Int): AnimalDetailModel? {
                delay(1000)
                return null
            }
        })
        // Action
        viewModel.load(animalId = animalId)

        // Assert
        assertTrue(viewModel.uiState.value is AnimalDetailUiState.Loading)
        dispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(viewModel.uiState.value is AnimalDetailUiState.Error)
    }
}