package com.ila.data.api

import com.ila.data.entities.CountriesItemData
import retrofit2.http.GET

/**
 * Created by devendra on 13/05/2020
 */
interface DemoAPI {
    @GET("all")
    suspend fun executeFruitApi(): ArrayList<CountriesItemData>
}