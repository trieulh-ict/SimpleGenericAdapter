package io.trieulh.simplegenericadapter.module

import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.listener.OnItemSelectedListener

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class ItemModule<T : Diffable> : IModule {
    private var onItemSelectedListener: OnItemSelectedListener<T>? = null

    abstract fun onBind(item: T, holder: SimpleViewHolder)

    abstract fun isModule(item: Diffable): Boolean

    abstract fun isStickyModule(item: T): Boolean

    fun onBindItem(position: Int, item: T, holder: SimpleViewHolder) {
        onItemSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onItemSelected(position, item)
            }
        }
        onBind(item, holder)
    }

    fun addOnItemSelectedListener(listener: OnItemSelectedListener<T>): ItemModule<T> {
        this.onItemSelectedListener = listener
        return this
    }
}