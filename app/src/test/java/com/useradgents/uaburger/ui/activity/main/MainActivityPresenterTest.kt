package com.useradgents.uaburger.ui.activity.main

import com.useradgents.domain.mapper.BurgerMapper
import com.useradgents.domain.usecase.ChangeCartItemQuantityUseCase
import com.useradgents.domain.usecase.GetBurgersUseCase
import com.useradgents.domain.usecase.ObserveCartUseCase
import com.useradgents.domain.viewobject.BurgerVO
import com.useradgents.uaburger.ui.fragment.main.MainFragmentContract
import com.useradgents.uaburger.ui.fragment.main.MainFragmentPresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify
import org.mockito.internal.verification.AtLeast
import org.mockito.junit.MockitoJUnit

class MainFragmentPresenterTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()

    @Mock
    lateinit var view: MainFragmentContract.View

    lateinit var presenter: MainFragmentContract.Presenter

    @Mock
    lateinit var getBurgersUseCase: GetBurgersUseCase
    @Mock
    lateinit var observeCartUseCase: ObserveCartUseCase
    @Mock
    lateinit var changeCartItemQuantityUseCase: ChangeCartItemQuantityUseCase
    @Mock
    lateinit var burgerMapper: BurgerMapper

    lateinit var ui: Scheduler
    lateinit var worker: Scheduler

    @Before
    fun setUp() {
        presenter = MainFragmentPresenter(view, getBurgersUseCase, observeCartUseCase, changeCartItemQuantityUseCase, burgerMapper)
        worker = Schedulers.newThread()
        ui = Schedulers.computation()
    }

    @Test
    fun testGetBurger() {
        val burgerVM = BurgerVO(name = "Test name")
//        `when`(burgerRepository.getBurgersUseCase()).thenReturn(Observable.just(Burger()))

        presenter.attach()

        verify(view, timeout(1000).times(1)).updateBurgers(mutableListOf(burgerVM))
        verify(view, AtLeast(2)).showProgress(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun testErrorGetBurger() {
//        `when`(burgerRepository.getBurgersUseCase()).thenReturn(Observable.error(Exception("MyError")))

        presenter.attach()

        verify(view, timeout(1000).times(1)).showError("MyError")
        verify(view, AtLeast(2)).showProgress(ArgumentMatchers.anyBoolean())
    }
}