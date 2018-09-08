package com.creative.mapper

import com.creative.Entity.Repo_Entity
import com.creative.domain.model.Repo_Domain
import com.google.gson.Gson
import javax.inject.Inject

class Repo_Mapper @Inject constructor(var ownermapper: Owner_Mapper, var gson: Gson) : Mapper<Repo_Entity, Repo_Domain> {
    override fun mapFromEntity(type: Repo_Entity): Repo_Domain {
        return Repo_Domain(type.name, type.description, type.html_url, ownermapper.mapListFromEntity(type.owner!!), type.fork)
    }

    override fun mapToEntity(type: Repo_Domain): Repo_Entity {
        return Repo_Entity(type.name, type.description, type.html_url, ownermapper.mapListToEntity(type.owner!!), type.fork)
    }

    override fun mapListFromEntity(type: List<Repo_Entity>): List<Repo_Domain> {
        return gson.fromJson(gson.toJson(type), Array<Repo_Domain>::class.java).toList()
    }

    override fun mapListToEntity(type: List<Repo_Domain>): List<Repo_Entity> {
        return gson.fromJson(gson.toJson(type), Array<Repo_Entity>::class.java).toList()
    }
}