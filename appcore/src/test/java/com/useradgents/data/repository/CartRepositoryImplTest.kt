package com.useradgents.data.repository

import com.useradgents.data.datasource.SoundDataSource
import com.useradgents.domain.model.Sound
import com.useradgents.data.datasource.SoundSpecification
import com.useradgents.data.datasource.MemorySoundDataSource
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class CartRepositoryImplTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()

    @Mock
    lateinit var repo: SoundDataSource

    lateinit var cartRepository: CartRepository

    val b1 = Sound(ref = "0", price = 100f)
    val b2 = Sound(ref = "1", price = 1000f)

    @Before
    fun setUp() {
        repo = MemorySoundDataSource()

        repo.add(b1)
        repo.add(b2)

        cartRepository = CartRepositoryImpl(repo)
    }

    @After
    fun tearDown() {
        repo.remove(SoundSpecification.All())
    }

    @Test
    fun addToCart() {
        cartRepository.addToCart("0", 2).test()
                .assertValue(2)
                .awaitTerminalEvent()

        assertEquals(2, cartRepository.getNbItemsInCart())
        assertEquals(2, cartRepository.getNbItemsInCart("0"))
        assertEquals(listOf("0"), cartRepository.getItemIds())
    }

    @Test
    fun removeFromCart() {
        addToCart()

        cartRepository.removeFromCart("0")
        assertEquals(0, cartRepository.getNbItemsInCart())
        assertEquals(0, cartRepository.getNbItemsInCart("0"))
    }

    @Test
    fun clearCart() {
        addToCart()

        cartRepository.clearCart().test().awaitTerminalEvent()

        assertEquals(0, cartRepository.getNbItemsInCart())
        assertEquals(0, cartRepository.getNbItemsInCart("0"))
        assertEquals(listOf<String>(), cartRepository.getItemIds())
    }

    @Test
    fun getNbItemsInCart() {
        assertEquals(0, cartRepository.getNbItemsInCart())
        assertEquals(0, cartRepository.getNbItemsInCart("0"))
        assertEquals(listOf<String>(), cartRepository.getItemIds())
    }

    @Test
    fun getTotalPrice() {
        assertEquals(0f, cartRepository.getTotalPrice())

        addToCart()

        assertEquals(2f, cartRepository.getTotalPrice())
    }

    @Test
    fun getItemIds() {
        addToCart()
        assertEquals(listOf("0"), cartRepository.getItemIds())
    }
}