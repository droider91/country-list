package com.ila.sample.ui.countries

import android.util.Log
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aliasadi.clean.ui.feed.CountryDiffCallback
import com.ila.sample.R
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.entities.LanguageP
import com.ila.sample.ui.countries.viewholder.CountryViewHolder

/**
 * Created by devendra on 17/01/2023
 */
class CountryAdapter(val languageList: List<LanguageP>) : ListAdapter<LanguageP, ViewHolder>(CountryDiffCallback), Filterable {
    var countryFilterList = ArrayList<LanguageP>()

    fun updateList(list: List<LanguageP>) {
        countryFilterList.clear()
        countryFilterList.addAll(list)
        notifyDataSetChanged()
    }
    init {
        countryFilterList = languageList as ArrayList<LanguageP>
    }
    override fun getItemViewType(position: Int): Int = when (countryFilterList[position]) {
        is LanguageP -> R.layout.item_country
        else -> 11111
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            R.layout.item_country -> CountryViewHolder(parent)
            else -> throw RuntimeException("Illegal view type")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = countryFilterList[position]
        when (holder) {
            is CountryViewHolder -> holder.bind(item)
        }
    }
    override fun getItemCount(): Int {
        return countryFilterList.size
    }
    override fun onViewRecycled(holder: ViewHolder) {
        when (holder) {
            is CountryViewHolder -> holder.unBind()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = languageList as ArrayList<LanguageP>
                } else {
                    val resultList = ArrayList<LanguageP>()
                    for (row in languageList) {
                        if (row.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<LanguageP>
                notifyDataSetChanged()
            }
        }
    }
}