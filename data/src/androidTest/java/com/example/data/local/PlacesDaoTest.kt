package com.example.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.data.local.dao.PlacesDao
import com.example.data.local.room.PlacesDataBase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class PlacesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: PlacesDataBase
    private lateinit var dao: PlacesDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.getPlacesDao()
    }

    @Test
    fun testFetchingDataSuccess() = runTest{
        val places = dao.getPlaces()
        Assert.assertEquals(true, !places.isNullOrEmpty())
    }

    @Test
    fun testFetchingDataFailed() = runTest{
        val places = dao.getPlaces()
        Assert.assertEquals(true, places.isNullOrEmpty())
    }

    @After
    fun teardown() {
        database.close()
    }


}