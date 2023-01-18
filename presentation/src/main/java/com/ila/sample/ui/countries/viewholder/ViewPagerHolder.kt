package com.ila.sample.ui.countries.viewholder

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ila.sample.R
import com.ila.sample.databinding.ItemViewPagerBinding
import com.ila.sample.entities.CountriesItemP


class ViewPagerHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = ItemViewPagerBinding.bind(itemView)

    fun bind(country: CountriesItemP) = with(binding) {
        loadImage(flagImage, country.flagsP.png)

    }
    fun unBind() = with(binding) {
        clearImage(flagImage)
        root.setOnClickListener(null)
    }

    private fun loadImage(image: AppCompatImageView, url: String) = Glide.with(image)
        .asDrawable()
        .format(DecodeFormat.PREFER_RGB_565)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .thumbnail(getThumbnailRequest(image, url))
        .load(url)
        .placeholder(R.color.light_gray)
        .into(image)

    private fun getThumbnailRequest(imageView: ImageView, url: String): RequestBuilder<Drawable> =
        Glide.with(imageView)
            .asDrawable()
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .sizeMultiplier(0.2F)
            .load(url)

    private fun clearImage(image: AppCompatImageView) = Glide.with(image).clear(image)
}