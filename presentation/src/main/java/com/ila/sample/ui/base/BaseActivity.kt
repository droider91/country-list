package com.ila.sample.ui.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.ila.sample.R
import com.ila.sample.di.core.module.AppModule
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by devendra on 17/01/2023
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    @Inject
    @Named(AppModule.PREFERENCE_APP_SETTINGS)
    lateinit var appSettings: SharedPreferences

    protected val binding: VB by lazy { inflateViewBinding(layoutInflater) }

    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    fun isDarkModeEnabled() = appSettings.getBoolean(DARK_MODE, false)

    fun enableDarkMode(enable: Boolean) = appSettings.edit().putBoolean(DARK_MODE, enable).commit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(if (isDarkModeEnabled()) R.style.DarkTheme else R.style.LightTheme)
        setContentView(binding.root)
    }

    protected fun <T> LiveData<T>.observe(observer: Observer<in T>) = observe(this@BaseActivity, observer)

    companion object {
        const val DARK_MODE = "dark_mode"
    }
}