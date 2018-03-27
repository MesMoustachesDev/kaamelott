package com.useradgents.data.datasource

/**
 * https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006
 */
interface DataSource<T> {
    fun add(item: T)
    fun add(items: Iterable<T>)
    fun update(item: T)
    fun remove(item: T)
    fun remove(specification: Specification)
    fun query(specification: Specification): List<T>
}