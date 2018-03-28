package com.useradgents.uaburger.ui.activity.main

import com.useradgents.domain.mapper.SoundMapper
import com.useradgents.domain.usecase.GetSoundsUseCase
import com.useradgents.domain.viewobject.SoundVO
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
    lateinit var getBurgersUseCase: GetSoundsUseCase
    @Mock
    lateinit var soundMapper: SoundMapper

    lateinit var ui: Scheduler
    lateinit var worker: Scheduler

    @Before
    fun setUp() {
        presenter = MainFragmentPresenter(view, getBurgersUseCase, soundMapper)
        worker = Schedulers.newThread()
        ui = Schedulers.computation()
    }

    @Test
    fun testGetBurger() {
        val burgerVM = SoundVO(title = "Test name")
//        `when`(burgerRepository.getBurgersUseCase()).thenReturn(Observable.just(Sound()))

        presenter.attach()

        verify(view, timeout(1000).times(1)).updateSounds(mutableListOf(burgerVM))
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