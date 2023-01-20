package com.ila.sample.ui.countries.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ila.sample.databinding.ItemCountryBinding
import com.ila.sample.entities.LanguageP

/**
 * Created by devendra on 17/01/2023
 */
class CountryViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = ItemCountryBinding.bind(itemView)

    fun bind(country: LanguageP) = with(binding) {
        name.text = country.name
    }

    fun unBind() = with(binding) {
        root.setOnClickListener(null)
    }
}