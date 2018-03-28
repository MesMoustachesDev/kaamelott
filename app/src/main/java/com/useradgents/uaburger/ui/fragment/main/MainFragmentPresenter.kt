package com.useradgents.uaburger.ui.fragment.main

import com.useradgents.domain.mapper.SoundMapper
import com.useradgents.domain.usecase.GetSoundsUseCase
import com.useradgents.domain.usecase.PlaySoundUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.addTo
import timber.log.Timber
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(
        private var mAttachedView: MainFragmentContract.View?,
        private val getSoundsUseCase: GetSoundsUseCase,
        private val playSoundUseCase: PlaySoundUseCase,
        private val soundMapper: SoundMapper
) : MainFragmentContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun attach() {
        refresh()
    }

    override fun detach() {
        disposable.dispose()
        getSoundsUseCase.dispose()
        mAttachedView = null
    }

    override fun refresh() {
        getSoundsUseCase.execute()
                .doOnSubscribe { mAttachedView?.showProgress(true) }
                .doAfterTerminate { mAttachedView?.showProgress(false) }
                .subscribe(
                        {
                            Timber.e(" onNext: $it")
                            mAttachedView?.updateSounds(it.map { soundMapper.map(it) }.toMutableList())
                        },
                        { Timber.e(it, " onError: ") },
                        { Timber.e(" onComplete") })
                .addTo(disposable)
    }

    override fun play(it: String) {
        playSoundUseCase.execute(it, { mAttachedView?.onStart(it) }, { mAttachedView?.onStop(it) })
    }
}
