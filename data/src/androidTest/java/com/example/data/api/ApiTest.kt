package com.example.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.data.remote.api.PlacesApiService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ApiTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    @Named("api_test")
    lateinit var placesApiService : PlacesApiService

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun getPlacesSuccess() = runTest{

        val ll = "53.9057644,27.558230"
        val limit = 30
        val radius = 4000
        val response = placesApiService.getNearbyPlaces(
            coordinates = ll,
            limit = limit,
            radius = radius,
            sessionToken = "Q01AYXB1YDOLVUUGWIWZIEDHANQFCFGL40X4KOMCPKVIU4S5"
        )

        Assert.assertEquals(true, response.place.isNotEmpty())
    }

    @Test
    fun getPlacesFail() = runTest{

        val ll = "53.9057644,27.558230"
        val limit = 30
        val radius = 0
        val response = placesApiService.getNearbyPlaces(
            coordinates = ll,
            limit = limit,
            radius = radius,
            sessionToken = "Q01AYXB1YDOLVUUGWIWZIEDHANQFCFGL40X4KOMCPKVIU4S5",

        )

        Assert.assertEquals(false, response.place.isNotEmpty())
    }

}