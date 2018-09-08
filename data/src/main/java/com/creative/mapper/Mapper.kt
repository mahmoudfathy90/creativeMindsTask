package com.creative.mapper

interface Mapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E

    fun mapListFromEntity(type: List<E>):List<D>{
        return ArrayList()
    }
    fun mapListToEntity(type: List<D>):List<E>{
        return ArrayList()

    }
}