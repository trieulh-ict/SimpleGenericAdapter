package io.trieulh.sampleadapter.view.itemModules

import android.graphics.Color
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import io.trieulh.sampleadapter.R
import io.trieulh.sampleadapter.model.Employee
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.ItemModule

/**
 * Created by Trieulh on 01,August,2019
 */
class EmployeeModule : ItemModule<Employee>() {
    override val layoutRes: Int = R.layout.item_employee

    override val viewType: Int = ItemType.EMPLOYEE.value

    override fun onBind(item: Employee, holder: SimpleViewHolder) {
        holder.itemView.findViewById<AppCompatTextView>(R.id.text_name).text = item.name
        holder.itemView.findViewById<AppCompatTextView>(R.id.text_job).text = item.job

        holder.setDragAndDropByView(holder.itemView.findViewById<AppCompatTextView>(R.id.text_name))

        holder.setOnChangeDragState(object : SimpleViewHolder.OnChangeDragStateListener {
            override fun onStateChanged(viewHolder: SimpleViewHolder, state: SimpleViewHolder.SimpleDragState) {
                if (state == SimpleViewHolder.SimpleDragState.SELECTED) {
                    holder.itemView.findViewById<CardView>(R.id.container).setCardBackgroundColor(Color.GRAY)
                } else {
                    holder.itemView.findViewById<CardView>(R.id.container).setCardBackgroundColor(Color.WHITE)
                }
            }
        })
    }

    override fun isModule(item: Diffable): Boolean {
        return item is Employee
    }
}