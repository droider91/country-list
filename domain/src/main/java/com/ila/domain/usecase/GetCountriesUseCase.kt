package com.ila.domain.usecase

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.repository.CountryRepository
import com.ila.domain.util.Result

/**
 * Created by devendra on 17/01/2023
 **/
open class GetCountriesUseCase(
    private val countrysRepository: CountryRepository
) {
    suspend fun execute(): Result<CountriesDomain> = countrysRepository.getCountries()
}
