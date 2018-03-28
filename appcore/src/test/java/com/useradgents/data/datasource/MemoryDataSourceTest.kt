package com.useradgents.data.datasource

import com.useradgents.domain.model.Sound
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MemoryDataSourceTest {

    lateinit var repo: SoundDataSource

    private fun getRepoSize() = repo.query(SoundSpecification.All()).size
    private fun getBurgerWithId(id: String) = repo.query(SoundSpecification.ByRef(id))

    private val testBurger = Sound(ref = "test", price = 1f)

    @Before
    fun setUp() {
        repo = MemorySoundDataSource()
    }

    @Test
    fun add() {
        assertEquals(0, getRepoSize())
        repo.add(testBurger)
        repo.add(testBurger)

        assertEquals(1, getRepoSize())
    }

    @Test
    fun addList() {
        assertEquals(0, getRepoSize())
        repo.add(listOf(testBurger, testBurger))
        assertEquals(1, getRepoSize())
    }

    @Test
    fun update() {
        assertEquals(0, getRepoSize())

        repo.add(testBurger)
        assertEquals(1f, testBurger.ref?.let { getBurgerWithId(it)[0].price })
        val updatedBurger = Sound(ref = testBurger.ref)
        updatedBurger.price = 2f
        repo.update(updatedBurger)
        assertEquals(2f, testBurger.ref?.let { getBurgerWithId(it)[0].price })
    }

    @Test
    fun remove() {
        assertEquals(0, getRepoSize())

        repo.add(testBurger)
        assertEquals(1, getRepoSize())
        repo.remove(testBurger)
        assertEquals(0, getRepoSize())
    }

    @Test
    fun removeSpecific() {
        assertEquals(0, getRepoSize())

        repo.add(testBurger)
        assertEquals(1, getRepoSize())
        testBurger.ref?.let { SoundSpecification.ByRef(it) }?.let { repo.remove(it) }
        assertEquals(0, getRepoSize())
    }

    @Test
    fun query() {
        assertEquals(0, getRepoSize())

        repo.add(testBurger)
        assertEquals(1, getRepoSize())
        val result = testBurger.ref?.let { getBurgerWithId(it) }
        assertEquals(testBurger, result?.get(0))
    }

}