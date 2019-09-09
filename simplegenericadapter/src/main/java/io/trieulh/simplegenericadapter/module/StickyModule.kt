package io.trieulh.simplegenericadapter.module

import androidx.annotation.LayoutRes
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 09,September,2019
 */

interface StickyModule {
    fun getType(): Int

    @get:LayoutRes
    val layoutRes: Int

    fun isStickyModule(): Boolean
}