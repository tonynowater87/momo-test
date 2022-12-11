package com.tonynowater.momotest.data.datasource.network

import com.tonynowater.momotest.BuildConfig
import com.tonynowater.momotest.data.model.dto.AnimalCatalogListDTO
import com.tonynowater.momotest.data.model.dto.AnimalDetailListDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private interface RetrofitNetworkApi {
    @GET(value = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getAnimalCatalogList(): AnimalCatalogListDTO

    @GET(value = "a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimalDetail(): AnimalDetailListDTO
}

class NetworkDatasourceImpl : NetworkDatasource {

    // TODO set baseUrl by different flavor
    private val networkApi = Retrofit.Builder()
        .baseUrl("https:///data.taipei/api/v1/dataset/")
        .client(
            OkHttpClient.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor().apply {
                            setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                        })
                    }
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getAnimalCatalogList(): AnimalCatalogListDTO {
        return networkApi.getAnimalCatalogList()
    }

    override suspend fun getAnimalDetailList(): AnimalDetailListDTO {
        return networkApi.getAnimalDetail()
    }
}