package io.trieulh.simplegenericadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.diff.EmptyIndicator
import io.trieulh.simplegenericadapter.diff.LoadingIndicator
import io.trieulh.simplegenericadapter.diff.SimpleDiffUtil
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.EmptyModule
import io.trieulh.simplegenericadapter.module.ItemModule
import io.trieulh.simplegenericadapter.module.PagingModule
import io.trieulh.simplegenericadapter.utils.let2
import io.trieulh.simplegenericadapter.utils.paging.EndlessScrollListener
import io.trieulh.simplegenericadapter.utils.paging.LoadMoreObserver
import io.trieulh.simplegenericadapter.utils.removeClassIfExist

class InternalAdapter : RecyclerView.Adapter<SimpleViewHolder>(), LoadMoreObserver {
    private var recyclerView: RecyclerView? = null
    internal var data: MutableList<Diffable> = mutableListOf()
        private set

    private var modules: MutableMap<Int, ItemModule<Diffable>> = mutableMapOf()
        private set

    // Emptiness
    private var emptyModule: EmptyModule? = null

    //Paging
    private var pagingModule: PagingModule? = null
    private var endlessScrollListener: EndlessScrollListener? = null

    private val uiHandler
        get() = recyclerView?.handler

    init {
        setHasStableIds(true)
    }

    fun attachTo(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView.apply {
            adapter = this@InternalAdapter
            configureItemModule(this)
            configureEmptyModule()
            configurePagingModule(this)
        }
    }

    private fun configureItemModule(recyclerView: RecyclerView) {
        //Do Nothing now
    }

    private fun configureEmptyModule() {
        if (data.isEmpty()) {
            data.add(EmptyIndicator)
        }
    }

    private fun configurePagingModule(recyclerView: RecyclerView) {
        let2(recyclerView.layoutManager, pagingModule) { layoutManager, module ->
            endlessScrollListener = EndlessScrollListener(
                layoutManager,
                module.withVisibleThreshold(),
                emptyModule != null,
                this@InternalAdapter
            ).also {
                recyclerView.addOnScrollListener(it)
            }
        }
    }

    fun updateData(items: MutableList<Diffable>) {
        if (items.isEmpty()) {
            items.add(EmptyIndicator)
        } else {
            items.removeClassIfExist(EmptyIndicator.javaClass)
            items.removeClassIfExist(LoadingIndicator.javaClass)
        }

        endlessScrollListener?.resetState()

        val diffResult = DiffUtil.calculateDiff(object : SimpleDiffUtil(data, items) {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean =
                oldItem is Diffable && newItem is Diffable && oldItem.javaClass == newItem.javaClass && oldItem.getUniqueIdentifier() == newItem.getUniqueIdentifier()

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean =
                oldItem is Diffable && newItem is Diffable && oldItem.javaClass == newItem.javaClass && oldItem.areContentTheSame(
                    newItem
                )

        })

        data.clear()
        data.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun <T : Diffable> addItemModule(itemModule: ItemModule<T>) {
        modules.set(itemModule.viewType, itemModule as ItemModule<Diffable>)
    }

    fun addEmptyModule(emptyModule: EmptyModule) {
        this.emptyModule = emptyModule
    }

    fun addPagingModule(pagingModule: PagingModule) {
        this.pagingModule = pagingModule
    }

    override fun getItemViewType(position: Int): Int {
        when (data[position]) {
            is EmptyIndicator -> return emptyModule!!.getType()
            is LoadingIndicator -> return pagingModule!!.getType()
            else ->
                modules.forEach { entry ->
                    if (entry.value.isModule(data[position] as Diffable))
                        return entry.key
                }
        }

        throw IllegalArgumentException("No associated Module attached.")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        if (emptyModule != null && viewType == emptyModule!!.getType()) {
            return SimpleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    emptyModule!!.layoutRes,
                    parent,
                    false
                )
            )
        }

        if (pagingModule != null && viewType == pagingModule!!.getType()) {
            return SimpleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    pagingModule!!.layoutRes,
                    parent,
                    false
                )
            )
        }

        return SimpleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                modules[viewType]!!.layoutRes,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long {
        return when (val item = data[position]) {
            // javaClass is used for lettings different Diffable models share the same unique identifier
            is EmptyIndicator -> hashCode().toLong()
            is LoadingIndicator -> hashCode().toLong()
            else -> item.getUniqueIdentifier()
        }
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        when (data[position]) {
            is EmptyIndicator -> return emptyModule!!.onBind(holder)
            is LoadingIndicator -> return pagingModule!!.onBind(holder)
            else -> modules.forEach { entry ->
                if (entry.value.isModule(data[position]))
                    return entry.value.onBindItem(position, data[position], holder)
            }
        }

        throw IllegalArgumentException("No associated Module attached.")
    }

    override fun onLoadingStateChanged(loading: Boolean) {
        if (loading) {
            data.indexOfFirst { it is LoadingIndicator }.let { index ->
                if (index == -1) {
                    data.add(data.size, LoadingIndicator)

                    uiHandler?.post { notifyItemInserted(data.size) }
                }
            }
        }
    }

    override fun onLoadMore(currentPage: Int) {
        uiHandler?.post { pagingModule?.onLoadMore(currentPage) }
    }

}
