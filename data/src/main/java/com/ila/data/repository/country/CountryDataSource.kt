package com.ila.data.repository.country

import com.ila.domain.entities.CountriesDomain
import com.ila.domain.util.Result

/**
 * Created by devendra on 13/05/2020
 */
interface CountryDataSource {

    interface Remote {
        suspend fun getFruitsFromRemote(): Result<CountriesDomain>
    }
}