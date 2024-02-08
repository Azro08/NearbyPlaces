package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.PlacesDataBase
import com.example.data.remote.api.PlacesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestDataModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, PlacesDataBase::class.java)
            .allowMainThreadQueries()
            .build()


    private const val BASE_URL = "https://api.foursquare.com/"

    @Provides
    @Named("api_test")
    fun providePlacesApiService(retrofit: Retrofit): PlacesApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlacesApiService::class.java)

}