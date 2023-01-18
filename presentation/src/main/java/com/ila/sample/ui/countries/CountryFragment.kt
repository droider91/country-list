package com.ila.sample.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.ila.sample.databinding.FragmentCountryBinding
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.ui.base.BaseFragment
import com.ila.sample.ui.countries.CountrtyViewModel.NavigationState.CountryDetails
import com.ila.sample.ui.countries.CountrtyViewModel.UiState.*
import com.ila.sample.util.hide
import com.ila.sample.util.show
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by devendra on 13/05/2020
 */

@Suppress("DEPRECATION")
@AndroidEntryPoint
class CountryFragment : BaseFragment<FragmentCountryBinding>() {

    private lateinit var tabbedListMediator: TabbedListMediator
    private val viewModel: CountrtyViewModel by viewModels()
    private var list = listOf<CountriesItemP>()

    private val countryAdapter by lazy { CountryAdapter() }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCountryBinding =
        FragmentCountryBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupRecyclerView()
        listenTabLayout()
        listenViewPager()
        handleSearch()
    }


    private fun handleSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                updateRecyclerView(newText!!)
                return false
            }

        })
    }

    private fun updateRecyclerView(searchTerm: String) {
        var filteredList: List<CountriesItemP> =
            list.filter { s -> s.name.toLowerCase().contains(searchTerm.toLowerCase()) }
        countryAdapter.submitList(filteredList)
        updateViewPagerTabLayout(filteredList)
    }

    private fun updateViewPagerTabLayout(filteredList: List<CountriesItemP>) {
        binding.viewPager.adapter = ViewPager2Adapter(filteredList)
        tabbedListMediator.detach()
        binding.tabLayout.removeAllTabs()
        if (filteredList.isNotEmpty()) {
            repeat(filteredList.size) {
                binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""))
            }
            tabbedListMediator = TabbedListMediator(
                binding.scrollableContent,
                binding.tabLayout,
                filteredList.indices.toList()
            )
            tabbedListMediator.attach()
        }

    }

    private fun listenTabLayout() {
        binding.tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.setCurrentItem(tab!!.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun listenViewPager() {
        val myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val tab = binding.tabLayout.getTabAt(position)
                tab?.select()
                val layoutManager = binding.scrollableContent.layoutManager as LinearLayoutManager
                layoutManager.scrollToPositionWithOffset(position, 0)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(myPageChangeCallback)
    }

    private fun setupRecyclerView() = with(binding.scrollableContent) {
        adapter = countryAdapter
        layoutManager = createLayoutManager()

        val layoutManager = binding.scrollableContent.layoutManager as LinearLayoutManager
        val firstVisiblePosition: Int = layoutManager.findFirstVisibleItemPosition()
        binding.viewPager.setCurrentItem(firstVisiblePosition, true)
        val tab = binding.tabLayout.getTabAt(firstVisiblePosition)
        tab?.select()
    }

    private fun createLayoutManager(): LinearLayoutManager = LinearLayoutManager(
        requireActivity(), RecyclerView.VERTICAL, false
    )

    private fun observeViewModel() = with(viewModel) {

        getUiState().observe {
            when (it) {
                is FeedUiState -> {
                    list = it.countries.list
                    countryAdapter.submitList(it.countries.list)
                    binding.viewPager.adapter = ViewPager2Adapter(it.countries.list)
                    repeat(it.countries.list.size) {
                        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""))
                    }
                    tabbedListMediator = TabbedListMediator(
                        binding.scrollableContent,
                        binding.tabLayout,
                        it.countries.list.indices.toList()
                    )
                    tabbedListMediator.attach()

                }
                is Loading -> binding.progressBar.show()
                is NotLoading -> {
                    binding.progressBar.elevation = -3f
                    binding.progressBar.hide()
                }
                is Error -> {
                    /* var str = requireContext().applicationContext.getJsonFromAssets("data.json")
                     val jsonElement = JsonParser.parseString(str)
                     val listType: Type = object : TypeToken<List<CountriesItemP?>?>() {}.type
                     val list = Gson().fromJson(jsonElement, listType)*/
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