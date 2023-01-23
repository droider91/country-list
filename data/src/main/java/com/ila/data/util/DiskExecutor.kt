package com.ila.data.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by devendra on 17/01/2023
 */
class DiskExecutor : Executor {

    private val diskExecutor: Executor = Executors.newSingleThreadExecutor()

    override fun execute(runnable: Runnable) {
        diskExecutor.execute(runnable)
    }
}