package io.trieulh.sampleadapter.view.itemModules

import androidx.appcompat.widget.AppCompatTextView
import io.trieulh.sampleadapter.R
import io.trieulh.sampleadapter.model.Advertisement
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.ItemModule

/**
 * Created by Trieulh on 01,August,2019
 */
class AdvertisementModule : ItemModule<Advertisement>() {
    override val layoutRes: Int = R.layout.item_ad

    override val viewType: Int = ItemType.ADVERTISEMENT.value

    override fun onBind(item: Advertisement, holder: SimpleViewHolder) {
            holder.itemView.findViewById<AppCompatTextView>(R.id.text_content).text = item.content
    }

    override fun isModule(item: Diffable): Boolean {
        return item is Advertisement
    }
}