package com.ila.sample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.ila.sample.databinding.ActivityMainBinding
import com.ila.sample.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author by devendra on 07/08/2022
 */

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { binding.container.getFragment<NavHostFragment>().navController }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
    }
}