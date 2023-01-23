package com.ila.sample.ui.countries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.ila.sample.databinding.FragmentCountryBinding
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.entities.LanguageP
import com.ila.sample.ui.base.BaseFragment
import com.ila.sample.ui.countries.CountrtyViewModel.NavigationState.CountryDetails
import com.ila.sample.ui.countries.CountrtyViewModel.UiState.*
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by devendra on 17/01/2023
 */

@Suppress("DEPRECATION")
@AndroidEntryPoint
class CountryFragment : BaseFragment<FragmentCountryBinding>() {

    private val viewModel: CountrtyViewModel by viewModels()
    private var list = ArrayList<CountriesItemP>()
    private var languagePList = ArrayList<LanguageP>()

    private val countryAdapter by lazy { CountryAdapter(languagePList) }
    private val viewPager2Adapter by lazy { ViewPager2Adapter(list) }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCountryBinding =
        FragmentCountryBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupRecyclerView()
        handleSearch()
    }


    private fun handleSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countryAdapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun listenTabLayout() {
        binding.tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.setCurrentItem(tab!!.position, true)
                countryAdapter?.updateList(list[tab.position].languages)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setupRecyclerView() = with(binding.scrollableContent) {
        layoutManager = createLayoutManager()
        adapter = countryAdapter
    }

    private fun createLayoutManager(): LinearLayoutManager {
        val mgr = LinearLayoutManager(
            requireActivity(), RecyclerView.VERTICAL, false
        )
        mgr.isSmoothScrollbarEnabled = true
        return mgr
    }

    private fun observeViewModel() = with(viewModel) {

        getUiState().observe {
            when (it) {
                is FeedUiState -> {
                    list.addAll(it.countries.list)
                    languagePList.addAll(list[0].languages)
                    binding.viewPager.adapter = viewPager2Adapter
                    countryAdapter.notifyDataSetChanged()

                    TabLayoutMediator(
                        binding.tabLayout,
                        binding.viewPager
                    ) { tab, position -> }.attach()
                    listenTabLayout()
                }
                is Error -> {
                }
            }
        }

        getNavigationState().observe {
            when (it) {
                is CountryDetails -> {}
            }
        }
    }
}