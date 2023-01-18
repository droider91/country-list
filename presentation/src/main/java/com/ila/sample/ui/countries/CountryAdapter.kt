package com.ila.sample.ui.countries

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aliasadi.clean.ui.feed.CountryDiffCallback
import com.ila.sample.R
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.ui.countries.viewholder.CountryViewHolder

/**
 * Created by devendra on 13/05/2020
 */
class CountryAdapter : ListAdapter<CountriesItemP, ViewHolder>(CountryDiffCallback) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is CountriesItemP -> R.layout.item_country
        else -> 11111
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            R.layout.item_country -> CountryViewHolder(parent)
            else -> throw RuntimeException("Illegal view type")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CountryViewHolder -> holder.bind(item as CountriesItemP)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        when (holder) {
            is CountryViewHolder -> holder.unBind()
        }
    }
}