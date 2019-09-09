package io.trieulh.simplegenericadapter.diff

import java.util.*

/**
 * Created by Trieulh on 02,August,2019
 */

internal object LoadingIndicator : Diffable {
    override fun getUniqueIdentifier(): Long = UUID.randomUUID().hashCode().toLong()
    override fun areContentTheSame(other: Diffable): Boolean = false
}

internal object EmptyIndicator : Diffable {
    override fun getUniqueIdentifier(): Long = UUID.randomUUID().hashCode().toLong()
    override fun areContentTheSame(other: Diffable): Boolean = false
}

internal object HeaderIndicator : Diffable {
    override fun getUniqueIdentifier(): Long = UUID.randomUUID().hashCode().toLong()
    override fun areContentTheSame(other: Diffable): Boolean = false
}