package io.trieulh.simplegenericadapter.module

import androidx.annotation.LayoutRes
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class EmptyModule {
    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun onBind(holder: SimpleViewHolder)

    fun getType() : Int = hashCode()
}