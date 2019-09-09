package io.trieulh.simplegenericadapter.utils.drag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder

/**
 * Created by Trieulh on 08,August,2019
 */
class ItemMoveCallback(
    private val adapter: ItemTouchHelperContract,
    private val dragAndDropMode: SimpleDragAndDropMode
) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean = dragAndDropMode == SimpleDragAndDropMode.FULL

    override fun isItemViewSwipeEnabled(): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags =
            if (adapter.isContainingHeader() && viewHolder.adapterPosition == 0) 0 else ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null && viewHolder is SimpleViewHolder) {
            adapter.onRowSelected(viewHolder)
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is SimpleViewHolder) {
            adapter.onRowClear(viewHolder)
        }

    }

    interface ItemTouchHelperContract {
        fun isContainingHeader(): Boolean
        fun onRowMoved(fromPosition: Int, toPosition: Int)
        fun onRowSelected(myViewHolder: SimpleViewHolder)
        fun onRowClear(myViewHolder: SimpleViewHolder)
    }
}