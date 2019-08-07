package io.trieulh.simplegenericadapter.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Created by Trieulh on 02,August,2019
 */

internal inline fun <A : Any?, B : Any?, R> let2(v1: A?, v2: B?, callback: (A, B) -> R): R? {
    return v1?.let { v1Verified ->
        v2?.let { v2Verified ->
            callback(v1Verified, v2Verified)
        }
    }
}

internal fun <T, M> MutableList<T>.removeClassIfExist(classToRemove: Class<M>) {
    indexOfFirst { classToRemove.isInstance(it) }.takeIf { it != -1 }?.let { foundIndex -> removeAt(foundIndex) }
}

internal fun RecyclerView.LayoutManager.findFirstVisibleItemPosition(): Int {
    return when (this) {
        is LinearLayoutManager -> findFirstVisibleItemPosition()
        is GridLayoutManager -> findFirstVisibleItemPosition()
        is StaggeredGridLayoutManager -> {
            val firstVisibleItemPositions = findFirstVisibleItemPositions(null)

            // get maximum element within the list
            var maxSize = 0
            for (i in firstVisibleItemPositions.indices) {
                if (i == 0) {
                    maxSize = firstVisibleItemPositions[i]
                } else if (firstVisibleItemPositions[i] > maxSize) {
                    maxSize = firstVisibleItemPositions[i]
                }
            }
            return maxSize
        }
        else -> throw IllegalStateException("Recycler view layout manager is not supported")
    }
}