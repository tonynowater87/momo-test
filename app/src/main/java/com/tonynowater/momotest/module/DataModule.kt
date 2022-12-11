package com.tonynowater.momotest.module

import com.tonynowater.momotest.data.datasource.network.NetworkDatasourceImpl
import com.tonynowater.momotest.data.repository.AnimaRepository
import com.tonynowater.momotest.data.repository.AnimaRepositoryImpl

class DataModule {

    companion object {
        @Volatile
        var INSTANCE: DataModule? = null

        fun getInstance(): DataModule {
            return INSTANCE ?: synchronized(this) {
                val instance = DataModule()
                INSTANCE = instance
                instance
            }
        }
    }

    var animalRepository: AnimaRepository =
        AnimaRepositoryImpl(networkDatasource = NetworkDatasourceImpl())
        private set
}