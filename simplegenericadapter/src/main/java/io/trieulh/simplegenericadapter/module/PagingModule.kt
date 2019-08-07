package io.trieulh.simplegenericadapter.module

import androidx.annotation.LayoutRes
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class PagingModule {
    fun getType(): Int = hashCode()

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun withVisibleThreshold(): Int

    abstract fun onBind(holder: SimpleViewHolder)

    abstract fun onLoadMore(currentPage: Int)
}