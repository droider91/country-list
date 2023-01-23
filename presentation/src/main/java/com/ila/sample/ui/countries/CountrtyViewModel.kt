package com.ila.sample.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ila.data.util.DispatchersProvider
import com.ila.domain.usecase.GetCountriesUseCase
import com.ila.domain.util.onError
import com.ila.domain.util.onSuccess
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.entities.CountriesP
import com.ila.sample.mapper.CountryEntityMapper
import com.ila.sample.ui.base.BaseViewModel
import com.ila.sample.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by devendra on 17/01/2023
 */
@HiltViewModel
class CountrtyViewModel @Inject internal constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    sealed class NavigationState {
        data class CountryDetails(val country: CountriesItemP) : NavigationState()
    }

    sealed class UiState {
        data class FeedUiState(val countries: CountriesP) : UiState()
        data class Error(val message: String?) : UiState()
        object Loading : UiState()
        object NotLoading : UiState()
    }

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    private val navigationState: SingleLiveEvent<NavigationState> = SingleLiveEvent()

    init {
        onInitialState()
    }

    private fun onInitialState() = launchOnMainImmediate {
        loadMovies()
    }

    fun onCountryClicked(country: CountriesItemP) = launchOnMainImmediate {
        navigationState.value = NavigationState.CountryDetails(country)
    }

    private suspend fun loadMovies() = launchOnMainImmediate {
        uiState.value = UiState.Loading
        getCountriesUseCase.execute()
            .onSuccess {
                uiState.value = UiState.NotLoading
                uiState.value = UiState.FeedUiState(CountryEntityMapper.toPresentation(it))
            }.onError {
                uiState.value = UiState.NotLoading
                uiState.value = UiState.Error(it.message)
            }
    }

    fun getNavigationState(): LiveData<NavigationState> = navigationState
    fun getUiState(): LiveData<UiState> = uiState

    class Factory(
        private val getCountriesUseCase: GetCountriesUseCase,
        private val dispatchers: DispatchersProvider
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CountrtyViewModel(getCountriesUseCase, dispatchers) as T
        }
    }
}