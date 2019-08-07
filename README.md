# Simple-Generic-Adapter

[![Download](https://jitpack.io/v/trieulh-ict/SimpleGenericAdapter.svg)]

SimpleGenericAdapter is an Android library that helps developers to easily create a Recyclerview Adapter without repeatedly building any adapter boilerplate.

## Features
- Create UI Module to bind item data
- Create UI Module for Empty state
- Create UI Module for Paging

## Demo

<img src="images/Empty.png" width="20%"> <img src="images/Items.png" width="20%"> <img src="images/Paging.png" width="20%">


## How to use it?

### Setup

On your module's `build.gradle` file add this implementation statement to the `dependencies` section:

```groovy
dependencies {
  implementation 'com.github.trieulh-ict:SimpleGenericAdapter:x.x.x'
}
```

Also make sure that Project module includes `jitpack`:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Usage

- Implement an Item Module using `ItemModule<ModelType>`

```kotlin
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
```

- Implement an Empty Module using

```kotlin
class EmployeeEmptyModule : EmptyModule() {

    override val layoutRes: Int = R.layout.item_no_employee

    override fun onBind(holder: SimpleViewHolder) {
        //Do nothing now
    }
}
```

- Create adapter and attach to your reyclerview:

```kotlin
adapter = SimpleGenericAdapter()
            .addItemModule(AdvertisementModule())
            .addEmptyModule(EmployeeEmptyModule())
            .attachTo(listView)
```

- You can create Paging module by defining a class or using anonymous object:

```kotlin
adapter = SimpleGenericAdapter()
            ...
            .addPagingModule(object : PagingModule() {
                            override fun withVisibleThreshold(): Int = 3
                            override val layoutRes: Int = R.layout.item_loading_employee
                            override fun onLoadMore(currentPage: Int) {
                                //Load new Data and add to adapter using `adapter.setItems()`
                            }

                            override fun onBind(holder: SimpleViewHolder) {
                                //Do nothing now
                            }
                        })
            .attachTo(listView)
```

- Currently the library only support OnItemClickerListener:

```kotlin
adapter = SimpleGenericAdapter()
            ...
            .addItemModule(EmployeeModule().addOnItemSelectedListener(object : OnItemSelectedListener<Employee> {
                            override fun onItemSelected(position: Int, item: Employee) {
                                Toast.makeText(this@MainActivity, "${item.id}", Toast.LENGTH_SHORT).show()
                            }
                        }))
            .attachTo(listView)
```

## In the future
The library is still under development,so you can suggest more feature by committing issues to this repository.

## License
MIT License

Copyright (c) 2019 Tristan Le

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.