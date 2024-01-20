package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.room.PlacesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDB(app: Application): PlacesDataBase =
        Room.databaseBuilder(
            app,
            PlacesDataBase::class.java,
            PlacesDataBase.dbName
        ).build()

    @Provides
    fun providePlacesDao(db: PlacesDataBase) =
        db.getPlacesDao()
}