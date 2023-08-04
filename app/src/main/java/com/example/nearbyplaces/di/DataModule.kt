package com.example.nearbyplaces.di

import android.app.Application
import androidx.room.Room
import com.example.nearbyplaces.data.local.room.PlacesDataBase
import com.example.nearbyplaces.data.remote.api.PlacesApiService
import com.example.nearbyplaces.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofitInstance(BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePlacesApiServiceInstance(retrofit: Retrofit): PlacesApiService =
        retrofit
            .create(PlacesApiService::class.java)

    @Singleton
    @Provides
    fun provideDB(app: Application): PlacesDataBase =
        Room.databaseBuilder(
            app,
            PlacesDataBase::class.java,
            PlacesDataBase.dbName
        ).build()

    @Provides
    @Singleton
    fun providePlacesDao(db: PlacesDataBase) =
        db.getPlacesDao()
}