package com.useradgents.data.repository

import com.useradgents.data.datasource.BurgerDataSource
import com.useradgents.domain.model.Burger
import com.useradgents.data.datasource.BurgerSpecification
import com.useradgents.data.network.BurgerAPIService
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class BurgerRepositoryImpl @Inject constructor(
        private val burgerAPIService: BurgerAPIService,
        private val repository: BurgerDataSource,
        private val cacheStrategy: BurgerCacheStrategy
) : BurgerRepository {

    private fun getBurgersFromApi(): Observable<Burger> {
        return burgerAPIService
                .getBurgers()
                .retry(2)
                .doOnSuccess {
                    repository.remove(BurgerSpecification.All())
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

    override fun getBurgers(): Observable<Burger> {
        val cache = repository.query(BurgerSpecification.All())
        return if (!cacheStrategy.isDataValid()) {
            getBurgersFromApi()
                    .doOnError { Timber.e("Problem while retrieving Burgers, returning cache...") }
                    .onErrorResumeNext(Observable.fromIterable(cache))
        } else {
            Observable.fromIterable(cache)
        }
    }

    override fun getBurgersWithIds(burgers: List<String>): Observable<Burger> {
        return getBurgers()
                .filter {
                    it.ref in burgers
                }
    }
}