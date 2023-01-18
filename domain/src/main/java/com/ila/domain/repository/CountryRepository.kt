package com.ila.domain.repository

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.util.Result

/**
 * Created by devendra on 13/05/2020
 */
interface CountryRepository {
    suspend fun getFruits(): Result<CountriesDomain>
}