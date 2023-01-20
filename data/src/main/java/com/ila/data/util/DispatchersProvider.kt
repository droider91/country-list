package com.ila.data.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by devendra on 17/01/2023
 **/
interface DispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getMainImmediate(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}