package io.trieulh.sampleadapter.model

import io.trieulh.simplegenericadapter.diff.Diffable

/**
 * Created by Trieulh on 01,August,2019
 */
data class Employee(val id: Int, val name: String, val job: String) : Diffable {
    override fun getUniqueIdentifier(): Long = id.toLong()

    override fun areContentTheSame(other: Diffable): Boolean {
        return other is Employee && name.contentEquals(other.name) && job.contentEquals(other.job)
    }
}