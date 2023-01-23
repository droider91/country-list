package com.ila.sample.di.core.module

import android.content.Context
import com.ila.data.repository.country.*
import com.ila.data.util.DispatchersProvider
import com.ila.domain.repository.CountryRepository
import com.ila.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        countryRemote: CountryDataSource.Local
    ): CountryRepository {
        return CountryRepositoryImpl(countryRemote)
    }

    @Provides
    @Singleton
    fun provideCountryRemoteDataSource(dispatchers: DispatchersProvider, @ApplicationContext appContext: Context): CountryDataSource.Local {
        return CountryRemoteDataSource(dispatchers, context = appContext)
    }

    @Provides
    fun provideGetCountryUseCase(countrysRepository: CountryRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countrysRepository)
    }
}