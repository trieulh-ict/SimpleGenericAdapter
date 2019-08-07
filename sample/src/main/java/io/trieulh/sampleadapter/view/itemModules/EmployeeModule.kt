package io.trieulh.sampleadapter.view.itemModules

import androidx.appcompat.widget.AppCompatTextView
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
    }

    override fun isModule(item: Diffable): Boolean {
        return item is Employee
    }
}