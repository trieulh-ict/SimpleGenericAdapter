package io.trieulh.simplegenericadapter.diff

/**
 * Created by Trieulh on 01,August,2019
 */
interface Diffable {
    fun getUniqueIdentifier(): Long
    fun areContentTheSame(other: Diffable): Boolean
}