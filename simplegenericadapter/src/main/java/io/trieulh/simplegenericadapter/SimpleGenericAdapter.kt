package io.trieulh.simplegenericadapter

import androidx.annotation.AnimRes
import androidx.recyclerview.widget.RecyclerView
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.diff.EmptyIndicator
import io.trieulh.simplegenericadapter.diff.LoadingIndicator
import io.trieulh.simplegenericadapter.module.EmptyModule
import io.trieulh.simplegenericadapter.module.ItemModule
import io.trieulh.simplegenericadapter.module.PagingModule
import io.trieulh.simplegenericadapter.utils.animation.SimpleAnimationType
import io.trieulh.simplegenericadapter.utils.drag.SimpleDragAndDropMode

/**
 * Created by Trieulh on 01,August,2019
 */
class SimpleGenericAdapter internal constructor(private var adapter: InternalAdapter) {

    val items: List<Diffable>
        get() = adapter.data.filter {
            !EmptyIndicator.javaClass.isInstance(it)
                    && !LoadingIndicator.javaClass.isInstance(it)
        }

    fun setItems(items: List<Diffable>) {
        adapter.updateData(items.toMutableList())
    }

    fun setDragAndDropMode(mode: SimpleDragAndDropMode) {
        adapter.updateDragAndDropMode(mode)
        adapter.notifyDataSetChanged()
    }

    class Builder {
        private val adapter = InternalAdapter()

        fun <T : Diffable> addItemModule(itemModule: ItemModule<T>): Builder {
            adapter.addItemModule(itemModule)
            return this
        }

        fun addEmptyModule(emptyModule: EmptyModule): Builder {
            adapter.addEmptyModule(emptyModule)
            return this
        }

        fun addPagingModule(pagingModule: PagingModule): Builder {
            adapter.addPagingModule(pagingModule)
            return this
        }


        fun attachTo(recyclerView: RecyclerView): Builder {
            recyclerView.adapter = adapter
            adapter.attachTo(recyclerView)
            return this
        }

        fun addItemAnimation(@AnimRes resId: Int): Builder {
            adapter.addItemAnimation(resId)
            return this
        }

        fun addItemAnimation(enumType: SimpleAnimationType): Builder {
            adapter.addItemAnimation(enumType.value)
            return this
        }

        fun setDragAndDropMode(mode: SimpleDragAndDropMode): Builder {
            adapter.setDragAndDropMode(mode)
            return this
        }

        fun build() = SimpleGenericAdapter(adapter)
    }
}