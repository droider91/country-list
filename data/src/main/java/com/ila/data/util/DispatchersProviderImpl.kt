package com.ila.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by devendra on 17/01/2023
 **/
object DispatchersProviderImpl : DispatchersProvider {
    override fun getMain(): CoroutineDispatcher = Dispatchers.Main
    override fun getMainImmediate(): CoroutineDispatcher = Dispatchers.Main.immediate
    override fun getIO(): CoroutineDispatcher = Dispatchers.IO
    override fun getDefault(): CoroutineDispatcher = Dispatchers.Default
}