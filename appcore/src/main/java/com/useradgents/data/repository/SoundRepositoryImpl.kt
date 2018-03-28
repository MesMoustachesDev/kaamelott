package com.useradgents.data.repository

import com.useradgents.data.datasource.SoundDataSource
import com.useradgents.data.datasource.SoundSpecification
import com.useradgents.data.network.SoundAPIService
import com.useradgents.domain.model.Sound
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class SoundRepositoryImpl @Inject constructor(
        private val soundAPIService: SoundAPIService,
        private val repository: SoundDataSource,
        private val cacheStrategy: SoundCacheStrategy
) : SoundRepository {

    private fun getSoundsFromApi(): Observable<Sound> {
        return soundAPIService
                .getSounds()
                .retry(2)
                .doOnSuccess {
                    repository.remove(SoundSpecification.All())
                    repository.add(it)
                    cacheStrategy.dataIsSet()
                }
                .flatMapObservable {
                    if (it.isEmpty()) {
                        Observable.empty()
                    } else {
                        Observable.fromIterable(it)
                    }
                }
    }

    override fun getSounds(): Observable<Sound> {
        val cache = repository.query(SoundSpecification.All())
        return if (!cacheStrategy.isDataValid()) {
            getSoundsFromApi()
                    .doOnError { Timber.e("Problem while retrieving sounds list, returning cache...") }
                    .onErrorResumeNext(Observable.fromIterable(cache))
        } else {
            Observable.fromIterable(cache)
        }
    }

    override fun getSoundsWithIds(sounds: List<String>): Observable<Sound> {
        return Observable.empty()
    }
}