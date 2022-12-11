package com.tonynowater.momotest.ui.animalcatalogdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tonynowater.momotest.data.model.ui.AnimalCatalogDetailModel
import com.tonynowater.momotest.ui.repository.TestAnimalRepositoryImpl
import com.tonynowater.momotest.ui.util.MainDispatcherRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test

class AnimalCatalogDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AnimalCatalogDetailViewModel

    @Test
    fun `Test Loading to Success`() {
        // Arrange
        val catalogId = 1
        val animalCatalogDetailModel = AnimalCatalogDetailModel(
            id = 1,
            eName = "eName",
            eInfo = "eInfo",
            eMemo = "eMemo",
            ePictureUrl = "ePictureUrl",
            eLinkUrl = "eLinkUrl",
            aNameCh = "aNameCh",
            aPicture1Url = "aPicture1Url",
            aAlsoKnown = "aAlsoKnown"
        )
        viewModel =
            AnimalCatalogDetailViewModel(animaRepository = object : TestAnimalRepositoryImpl() {
                override suspend fun getCategoryDetail(catalogId: Int): AnimalCatalogDetailModel? {
                    delay(1000)
                    return animalCatalogDetailModel
                }
            })

        // Action
        viewModel.load(catalogId = catalogId)

        // Assert
        assertTrue(viewModel.uiState.value is AnimalCatalogDetailUiState.Loading)
        dispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            AnimalCatalogDetailUiState.Success(data = animalCatalogDetailModel).data.eInfo,
            "eInfo"
        )
        // ...
    }

    @Test
    fun `Test Loading to Error`() {
        // Arrange
        viewModel =
            AnimalCatalogDetailViewModel(animaRepository = object : TestAnimalRepositoryImpl() {
                override suspend fun getCategoryDetail(catalogId: Int): AnimalCatalogDetailModel? {
                    delay(1000)
                    return null
                }
            })
        val catalogId = 1

        // Action
        viewModel.load(catalogId = catalogId)

        // Assert
        assertTrue(viewModel.uiState.value is AnimalCatalogDetailUiState.Loading)
        dispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
        assertTrue(viewModel.uiState.value is AnimalCatalogDetailUiState.Error)
    }
}