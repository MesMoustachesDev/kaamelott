package com.useradgents.data.repository

import com.useradgents.data.datasource.BurgerDataSource
import com.useradgents.domain.model.Burger
import com.useradgents.data.datasource.BurgerSpecification
import com.useradgents.data.datasource.MemoryBurgersDataSource
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
    lateinit var repo: BurgerDataSource

    lateinit var cartRepository: CartRepository

    val b1 = Burger(ref = "0", price = 100f)
    val b2 = Burger(ref = "1", price = 1000f)

    @Before
    fun setUp() {
        repo = MemoryBurgersDataSource()

        repo.add(b1)
        repo.add(b2)

        cartRepository = CartRepositoryImpl(repo)
    }

    @After
    fun tearDown() {
        repo.remove(BurgerSpecification.All())
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