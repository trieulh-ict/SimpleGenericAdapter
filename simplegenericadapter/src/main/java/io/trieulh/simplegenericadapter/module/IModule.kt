package io.trieulh.simplegenericadapter.module

import androidx.annotation.LayoutRes
import io.trieulh.simplegenericadapter.diff.Diffable

/**
 * Created by Trieulh on 09,September,2019
 */

internal interface IModule {
    fun getType(): Int

    @get:LayoutRes
    val layoutRes: Int
}