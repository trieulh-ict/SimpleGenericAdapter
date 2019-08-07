package io.trieulh.simplegenericadapter.diff

/**
 * Created by Trieulh on 02,August,2019
 */

internal object LoadingIndicator : Diffable {
    override fun getUniqueIdentifier(): Long = hashCode().toLong()

    override fun areContentTheSame(other: Diffable): Boolean = false
}

internal object EmptyIndicator : Diffable {
    override fun getUniqueIdentifier(): Long = hashCode().toLong()

    override fun areContentTheSame(other: Diffable): Boolean = false

}