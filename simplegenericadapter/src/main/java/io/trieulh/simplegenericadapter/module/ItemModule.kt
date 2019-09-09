package io.trieulh.simplegenericadapter.module

import androidx.annotation.LayoutRes
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.listener.OnItemSelectedListener

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class ItemModule<T : Diffable> : StickyModule {
    private var onItemSelectedListener: OnItemSelectedListener<T>? = null

    abstract fun onBind(item: T, holder: SimpleViewHolder)

    abstract fun isModule(item: Diffable): Boolean

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