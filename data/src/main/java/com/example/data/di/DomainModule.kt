package com.example.data.di

import com.example.data.local.repository.PlacesDbRepositoryImpl
import com.example.data.remote.api.PlacesApiService
import com.example.data.remote.repository.PlacesApiRepositoryImpl
import com.example.domain.repository.PlacesApiRepository
import com.example.domain.repository.PlacesDbRepository
import com.example.domain.usecase.PlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideApiRepository(apiService: PlacesApiService): PlacesApiRepository =
        PlacesApiRepositoryImpl(apiService)

    @Provides
    fun provideDbRepository(dao: com.example.data.local.dao.PlacesDao): PlacesDbRepository =
        PlacesDbRepositoryImpl(dao)

    @Provides
    fun providePlacesUseCaseInstance(
        apiRepository: PlacesApiRepository,
        dbRepository: PlacesDbRepository
    ): PlacesUseCase =
        PlacesUseCase(apiRepository, dbRepository)

}