package io.trieulh.sampleadapter.model

import io.trieulh.simplegenericadapter.diff.Diffable

/**
 * Created by Trieulh on 01,August,2019
 */

data class Advertisement(val id: Int, val content: String) : Diffable {
    override fun getUniqueIdentifier(): Long = id.toLong()

    override fun areContentTheSame(other: Diffable): Boolean {
        return other is Advertisement && content == other.content
    }
}