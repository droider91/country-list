package com.ila.domain.repository

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.util.Result

/**
 * Created by devendra on 17/01/2023
 */
interface CountryRepository {
    suspend fun getCountries(): Result<CountriesDomain>
}