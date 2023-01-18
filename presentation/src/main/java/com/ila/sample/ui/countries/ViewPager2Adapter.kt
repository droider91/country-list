package com.ila.sample.ui.countries

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.ui.countries.viewholder.ViewPagerHolder

class ViewPager2Adapter(val arraylist: List<CountriesItemP>) :
    RecyclerView.Adapter<ViewPagerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(parent)
    }
    // This method binds the screen with the view
    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        when (holder) {
            is ViewPagerHolder -> holder.bind(arraylist[position])
        }
    }

    // This Method returns the size of the Array
    override fun getItemCount(): Int {
        return arraylist.size
    }
}