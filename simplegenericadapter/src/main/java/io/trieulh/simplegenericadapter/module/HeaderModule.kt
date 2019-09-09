package io.trieulh.simplegenericadapter.module

import androidx.annotation.LayoutRes
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class HeaderModule : StickyModule {
    override fun getType(): Int = hashCode()

    abstract fun onBind(holder: SimpleViewHolder)

    override fun isStickyModule(): Boolean = false
}