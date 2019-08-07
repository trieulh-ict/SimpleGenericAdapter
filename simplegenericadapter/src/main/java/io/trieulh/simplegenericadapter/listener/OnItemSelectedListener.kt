package io.trieulh.simplegenericadapter.listener

import io.trieulh.simplegenericadapter.diff.Diffable

/**
 * Created by Trieulh on 07,August,2019
 */
interface OnItemSelectedListener<T : Diffable> {
    fun onItemSelected(position: Int, item: T)
}