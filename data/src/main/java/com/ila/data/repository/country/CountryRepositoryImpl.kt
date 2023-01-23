package com.ila.data.repository.country

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.repository.CountryRepository
import com.ila.domain.util.Result

/**
 * Created by devendra on 17/01/2023
 */
class CountryRepositoryImpl constructor(
    private val local: CountryDataSource.Local
) : CountryRepository {
    override suspend fun getCountries(): Result<CountriesDomain> = local.getCountriesFromAssets()

}