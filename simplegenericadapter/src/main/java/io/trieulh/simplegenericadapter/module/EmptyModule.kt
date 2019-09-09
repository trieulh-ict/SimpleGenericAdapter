package io.trieulh.simplegenericadapter.module

import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class EmptyModule : IModule {
    final override fun getType() : Int = hashCode()

    abstract fun onBind(holder: SimpleViewHolder)
}