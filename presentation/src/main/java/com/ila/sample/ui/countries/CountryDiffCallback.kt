package com.aliasadi.clean.ui.feed

import androidx.recyclerview.widget.DiffUtil
import com.ila.sample.entities.CountriesItemP

/**
 * @author by Ali Asadi on 03/08/2022
 */
object CountryDiffCallback : DiffUtil.ItemCallback<CountriesItemP>() {

    override fun areItemsTheSame(oldItem: CountriesItemP, newItem: CountriesItemP): Boolean =
        if (oldItem is CountriesItemP && newItem is CountriesItemP) {
            oldItem.flag == newItem.flag
        } else {
            oldItem == newItem
        }

    override fun areContentsTheSame(oldItem: CountriesItemP, newItem: CountriesItemP): Boolean =
        oldItem == newItem
}