package com.aliasadi.clean.ui.feed

import androidx.recyclerview.widget.DiffUtil
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.entities.LanguageP

/**
 * @author by Ali Asadi on 03/08/2022
 */
object CountryDiffCallback : DiffUtil.ItemCallback<LanguageP>() {

    override fun areItemsTheSame(oldItem: LanguageP, newItem: LanguageP): Boolean =
        if (oldItem is LanguageP && newItem is LanguageP) {
            oldItem.name == newItem.name
        } else {
            oldItem == newItem
        }

    override fun areContentsTheSame(oldItem: LanguageP, newItem: LanguageP): Boolean =
        oldItem == newItem
}