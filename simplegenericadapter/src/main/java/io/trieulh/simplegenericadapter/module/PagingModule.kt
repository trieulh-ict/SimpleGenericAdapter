package io.trieulh.simplegenericadapter.module

import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class PagingModule :IModule {
    final override fun getType(): Int = hashCode()

    abstract fun withVisibleThreshold(): Int

    abstract fun onBind(holder: SimpleViewHolder)

    abstract fun onLoadMore(currentPage: Int)
}