package io.trieulh.sampleadapter.view.itemModules

import io.trieulh.sampleadapter.R
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.HeaderModule

/**
 * Created by Trieulh on 01,August,2019
 */
class HeaderEmployeeModule(private var isSticky: Boolean = false) : HeaderModule() {
    override val layoutRes: Int = R.layout.item_header

    override fun onBind(holder: SimpleViewHolder) {
        //Do nothing now
    }

    override fun isStickyModule(): Boolean = isSticky
}