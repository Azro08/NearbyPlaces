package com.example.nearbyplaces.di

import com.example.nearbyplaces.data.local.dao.PlacesDao
import com.example.nearbyplaces.data.local.repository.PlacesDbRepository
import com.example.nearbyplaces.data.remote.api.PlacesApiService
import com.example.nearbyplaces.data.remote.repository.PlacesApiRepository
import com.example.nearbyplaces.domain.usecase.PlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideApiRepository(apiService: PlacesApiService): PlacesApiRepository =
        PlacesApiRepository(apiService)

    @Provides
    @ViewModelScoped
    fun provideDbRepository(dao: PlacesDao): PlacesDbRepository =
        PlacesDbRepository(dao)

    @Provides
    @ViewModelScoped
    fun providePlacesUseCaseInstance(
        apiRepository: PlacesApiRepository,
        dbRepository: PlacesDbRepository
    ): PlacesUseCase =
        PlacesUseCase(apiRepository, dbRepository)

}