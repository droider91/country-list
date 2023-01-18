package com.ila.data.repository.country

import com.ila.data.api.DemoAPI
import com.ila.data.mapper.CountryDataMapper
import com.ila.data.util.DispatchersProvider
import com.ila.domain.entities.CountriesDomain
import com.ila.domain.util.Result
import kotlinx.coroutines.withContext

/**
 * Created by devendra on 13/05/2020
 */
class CountryRemoteDataSource(
    private val demoAPI: DemoAPI,
    private val dispatchers: DispatchersProvider
) : CountryDataSource.Remote {

    override suspend fun getFruitsFromRemote(): Result<CountriesDomain> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = demoAPI.executeFruitApi()
                Result.Success(CountryDataMapper.toDomain(result))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}