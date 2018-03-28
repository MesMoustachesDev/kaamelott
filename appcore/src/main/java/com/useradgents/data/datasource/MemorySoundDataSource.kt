package com.useradgents.data.datasource

import com.useradgents.data.datasource.SoundSpecification.All
import com.useradgents.data.datasource.SoundSpecification.ByRef
import com.useradgents.domain.model.Sound
import javax.inject.Inject

class MemorySoundDataSource @Inject constructor() : SoundDataSource {

    private val burgers = mutableListOf<Sound>()

    /**
     * @param id [String?]
     * @return index of first item matching id
     * @throws [IllegalArgumentException] if param is null or does not match any item of the list
     */
    private fun getIndexOfItem(id: String?): Int {
        if (id == null) {
            throw IllegalArgumentException("Id cannot be null")
        }
        val index = burgers.indexOfFirst { it.title == id }
        if (index == -1) {
            throw IllegalArgumentException("No item with ID $id")
        } else {
            return index
        }
    }

    override fun add(item: Sound) {
        try {
            getIndexOfItem(item.title)
        } catch (e: IllegalArgumentException) {
            burgers.add(item)
        }
    }

    override fun add(items: Iterable<Sound>) {
        items.forEach { add(it) }
    }

    override fun update(item: Sound) {
        val index = getIndexOfItem(item.title)
        burgers.removeAt(index)
        burgers.add(index, item)
    }

    override fun remove(item: Sound) {
        val index = getIndexOfItem(item.title)
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

    override fun query(specification: Specification): List<Sound> = when (specification) {
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