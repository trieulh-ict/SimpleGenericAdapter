package io.trieulh.sampleadapter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.trieulh.sampleadapter.model.Advertisement
import io.trieulh.sampleadapter.model.Employee
import io.trieulh.sampleadapter.view.itemModules.AdvertisementModule
import io.trieulh.sampleadapter.view.itemModules.EmployeeEmptyModule
import io.trieulh.sampleadapter.view.itemModules.EmployeeModule
import io.trieulh.simplegenericadapter.SimpleGenericAdapter
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.listener.OnItemSelectedListener
import io.trieulh.simplegenericadapter.module.PagingModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var adapter: SimpleGenericAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        listView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        adapter = SimpleGenericAdapter()
            .addItemModule(EmployeeModule().addOnItemSelectedListener(object : OnItemSelectedListener<Employee> {
                override fun onItemSelected(position: Int, item: Employee) {
                    Toast.makeText(this@MainActivity, "${item.id}", Toast.LENGTH_SHORT).show()
                }
            }))
            .addItemModule(AdvertisementModule())
            .addEmptyModule(EmployeeEmptyModule())
            .addPagingModule(object : PagingModule() {
                override fun withVisibleThreshold(): Int = 3
                override val layoutRes: Int = R.layout.item_loading_employee
                override fun onLoadMore(currentPage: Int) {
                    val list = adapter.items.toMutableList()
                    for (i in 1..5) {
                        val id = Random.nextInt()
                        list.add(
                            Employee(
                                id,
                                "Name ${id}",
                                "Job ${id}"
                            )
                        )
                    }
                    adapter.setItems(list)
                }

                override fun onBind(holder: SimpleViewHolder) {
                    //Do nothing now
                }
            })
            .attachTo(listView)
        adapter.setItems(data)

        btnAdd.setOnClickListener(this)
        btnAddAd.setOnClickListener(this)
        btnRemove.setOnClickListener(this)
        btnLoadMore.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val list = adapter.items.toMutableList()
        when (v.id) {
            R.id.btnAdd -> {
                list.add(
                    0, Employee(
                        list.size,
                        "Name ${list.size}",
                        "Job ${list.size}"
                    )
                )
            }

            R.id.btnAddAd -> {
                list.add(
                    0, Advertisement(
                        list.size,
                        "Advertisement ${list.size}"
                    )
                )
            }

            R.id.btnRemove -> {
                if (list.size > 0)
                    list.removeAt(0)
            }

            R.id.btnLoadMore -> {

            }
        }
        adapter.setItems(list)
        listView.scrollToPosition(0)
    }

    companion object {
        val data = mutableListOf(
            Employee(1, "Name 1", "Job 1"),
            Employee(2, "Name 2", "Job 2"),
            Employee(3, "Name 3", "Job 3"),
            Advertisement(1, "Advertisement 1"),
            Employee(4, "Name 4", "Job 4"),
            Advertisement(2, "Advertisement 2")
        )
    }

}
