package com.useradgents.domain.usecase

import com.useradgents.data.repository.SoundRepository
import com.useradgents.di.qualifier.UI
import com.useradgents.di.qualifier.Worker
import com.useradgents.domain.model.Sound
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetSoundsUseCase @Inject constructor(
        private val soundRepository: SoundRepository,
        @Worker worker: Scheduler,
        @UI ui: Scheduler
) : UseCase<Void, List<Sound>>(worker, ui) {

    override fun createObservable(input: Void?): Observable<List<Sound>> = soundRepository.getSounds().toList().toObservable()
}