package io.trieulh.simplegenericadapter.diff

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Trieulh on 01,August,2019
 */
abstract class SimpleDiffUtil(private val oldList: List<*>, private val newList: List<*>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition]!!, newList[newItemPosition]!!)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldList[oldItemPosition]!!, newList[newItemPosition]!!)
    }

    abstract fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean

    abstract fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean
}