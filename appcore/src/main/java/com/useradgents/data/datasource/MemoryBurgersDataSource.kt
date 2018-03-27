package com.useradgents.data.datasource

import com.useradgents.domain.model.Burger
import com.useradgents.data.datasource.BurgerSpecification.All
import com.useradgents.data.datasource.BurgerSpecification.ByRef
import javax.inject.Inject

class MemoryBurgersDataSource @Inject constructor() : BurgerDataSource {

    private val burgers = mutableListOf<Burger>()

    /**
     * @param id [String?]
     * @return index of first item matching id
     * @throws [IllegalArgumentException] if param is null or does not match any item of the list
     */
    private fun getIndexOfItem(id: String?): Int {
        if (id == null) {
            throw IllegalArgumentException("Id cannot be null")
        }
        val index = burgers.indexOfFirst { it.ref == id }
        if (index == -1) {
            throw IllegalArgumentException("No item with ID $id")
        } else {
            return index
        }
    }

    override fun add(item: Burger) {
        try {
            getIndexOfItem(item.ref)
        } catch (e: IllegalArgumentException) {
            burgers.add(item)
        }
    }

    override fun add(items: Iterable<Burger>) {
        items.forEach { add(it) }
    }

    override fun update(item: Burger) {
        val index = getIndexOfItem(item.ref)
        burgers.removeAt(index)
        burgers.add(index, item)
    }

    override fun remove(item: Burger) {
        val index = getIndexOfItem(item.ref)
        burgers.removeAt(index)
    }

    override fun remove(specification: Specification) {
        when (specification) {
            is ByRef -> {
                val index = getIndexOfItem(specification.id)
                burgers.removeAt(index)
            }
            is All -> {
                burgers.clear()
            }
        }
    }

    override fun query(specification: Specification): List<Burger> = when (specification) {
        is ByRef -> {
            val index = getIndexOfItem(specification.id)
            listOf(burgers[index])
        }
        is All -> {
            burgers
        }
        else -> mutableListOf()
    }
}