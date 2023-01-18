package com.ila.data.repository.country

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.repository.CountryRepository
import com.ila.domain.util.Result

/**
 * Created by devendra on 13/05/2020
 */
class CountryRepositoryImpl constructor(
    private val remote: CountryDataSource.Remote
) : CountryRepository {
    override suspend fun getFruits(): Result<CountriesDomain> = remote.getFruitsFromRemote()

}