package com.ila.sample.di.core.module

import com.ila.data.api.DemoAPI
import com.ila.data.repository.country.*
import com.ila.data.util.DispatchersProvider
import com.ila.domain.repository.CountryRepository
import com.ila.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by devendra on 15/05/2020
 **/
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        countryRemote: CountryDataSource.Remote
    ): CountryRepository {
        return CountryRepositoryImpl(countryRemote)
    }

    @Provides
    @Singleton
    fun provideCountryRemoteDataSource(demoAPI: DemoAPI, dispatchers: DispatchersProvider): CountryDataSource.Remote {
        return CountryRemoteDataSource(demoAPI, dispatchers)
    }

    @Provides
    fun provideGetCountryUseCase(countrysRepository: CountryRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countrysRepository)
    }
}