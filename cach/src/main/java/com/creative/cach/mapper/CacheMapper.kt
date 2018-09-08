package com.creative.cach.mapper

interface CacheMapper<C,E> {
    fun mapFromCahce(type: List<C>): List<E>

    fun mapToCache(type: List<E>): List<C>
}