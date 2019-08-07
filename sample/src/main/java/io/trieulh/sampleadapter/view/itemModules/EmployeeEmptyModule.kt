package io.trieulh.sampleadapter.view.itemModules

import io.trieulh.sampleadapter.R
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.EmptyModule

/**
 * Created by Trieulh on 01,August,2019
 */
class EmployeeEmptyModule : EmptyModule() {

    override val layoutRes: Int = R.layout.item_no_employee

    override fun onBind(holder: SimpleViewHolder) {
        //Do nothing now
    }
}