package com.ila.sample.ui.countries.viewholder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ila.sample.databinding.ItemViewPagerBinding
import com.ila.sample.entities.CountriesItemP
import java.util.concurrent.Executors


class ViewPagerHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = ItemViewPagerBinding.bind(itemView)

    fun bind(country: CountriesItemP) = with(binding) {
        name.text = country.name
        loadImage(flagImage, country.flagsP.png)

    }

    fun loadImage(imageView: AppCompatImageView, url: String) {
        // Declaring executor to parse the URL
        val executor = Executors.newSingleThreadExecutor()
        // Once the executor parses the URL
        // and receives the image, handler will load it
        // in the ImageView
        val handler = Handler(Looper.getMainLooper())
        // Initializing the image
        var image: Bitmap? = null
        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {
            // Image URL
            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(url).openStream()
                image = BitmapFactory.decodeStream(`in`)
                // Only for making changes in UI
                handler.post {
                    imageView.setImageBitmap(image)
                }
            }
            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}