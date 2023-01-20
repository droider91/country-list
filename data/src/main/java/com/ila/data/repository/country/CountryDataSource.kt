package com.ila.data.repository.country

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.util.Result

/**
 * Created by devendra on 17/01/2023
 */
interface CountryDataSource {

    interface Local {
        suspend fun getCountriesFromAssets(): Result<CountriesDomain>
    }
}