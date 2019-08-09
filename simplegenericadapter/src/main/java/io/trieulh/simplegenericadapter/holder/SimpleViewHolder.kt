package io.trieulh.simplegenericadapter.holder

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.trieulh.simplegenericadapter.utils.let2

class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var itemTouchHelper: ItemTouchHelper? = null
    internal var onChangeDragStateListener: OnChangeDragStateListener? = null

    fun setOnChangeDragState(onChangeDragStateListener: OnChangeDragStateListener) {
        this.onChangeDragStateListener = onChangeDragStateListener
    }

    fun setDragAndDropByView(view: View?) {
        let2(view, itemTouchHelper) { view, helper ->
            view.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN
                ) {
                    helper.startDrag(this)
                }
                false
            }
        }
    }

    internal fun setTouchHelper(itemTouchHelper: ItemTouchHelper?) {
        this.itemTouchHelper = itemTouchHelper
    }

    interface OnChangeDragStateListener {
        fun onStateChanged(viewHolder: SimpleViewHolder, state: SimpleDragState)
    }

    enum class SimpleDragState {
        SELECTED,
        UNSELECTED
    }
}
