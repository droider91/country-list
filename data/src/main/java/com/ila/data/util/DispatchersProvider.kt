package com.ila.data.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by devendra on 13/05/2020
 **/
interface DispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getMainImmediate(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}