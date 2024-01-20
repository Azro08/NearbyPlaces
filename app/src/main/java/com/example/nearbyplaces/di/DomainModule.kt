package com.example.nearbyplaces.di

import com.example.data.remote.api.PlacesApiService
import com.example.data.remote.repository.PlacesApiRepositoryImpl
import com.example.domain.domain.usecase.PlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideApiRepository(apiService: PlacesApiService): PlacesApiRepositoryImpl =
        PlacesApiRepositoryImpl(apiService)

    @Provides
    @ViewModelScoped
    fun provideDbRepository(dao: com.example.data.local.dao.PlacesDao): com.example.data.local.repository.PlacesDbRepositoryImpl =
        com.example.data.local.repository.PlacesDbRepositoryImpl(dao)

    @Provides
    @ViewModelScoped
    fun providePlacesUseCaseInstance(
        apiRepository: PlacesApiRepositoryImpl,
        dbRepository: com.example.data.local.repository.PlacesDbRepositoryImpl
    ): PlacesUseCase =
        PlacesUseCase(apiRepository, dbRepository)

}