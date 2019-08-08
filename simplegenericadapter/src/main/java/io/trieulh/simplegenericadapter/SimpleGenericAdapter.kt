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

/**
 * Created by Trieulh on 01,August,2019
 */
class SimpleGenericAdapter {

    private val adapter = InternalAdapter()
    val items: List<Diffable>
        get() = adapter.data.filter {
            !EmptyIndicator.javaClass.isInstance(it)
                    && !LoadingIndicator.javaClass.isInstance(it)
        }

    fun setItems(items: List<Diffable>) {
        adapter.updateData(items.toMutableList())
    }

    fun <T : Diffable> addItemModule(itemModule: ItemModule<T>): SimpleGenericAdapter {
        adapter.addItemModule(itemModule)
        return this
    }

    fun addEmptyModule(emptyModule: EmptyModule): SimpleGenericAdapter {
        adapter.addEmptyModule(emptyModule)
        return this
    }

    fun addPagingModule(pagingModule: PagingModule): SimpleGenericAdapter {
        adapter.addPagingModule(pagingModule)
        return this
    }


    fun attachTo(recyclerView: RecyclerView): SimpleGenericAdapter {
        recyclerView.adapter = adapter
        adapter.attachTo(recyclerView)
        return this
    }

    fun addItemAnimation(@AnimRes resId: Int): SimpleGenericAdapter {
        adapter.addItemAnimation(resId)
        return this
    }

    fun addItemAnimation(enumType: SimpleAnimationType): SimpleGenericAdapter {
        adapter.addItemAnimation(enumType.value)
        return this
    }
}