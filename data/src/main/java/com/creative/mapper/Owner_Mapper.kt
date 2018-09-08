package com.creative.mapper

import com.creative.Entity.RepoOwner_Entity
import com.creative.domain.model.RepoOwner_domain
import com.google.gson.Gson
import javax.inject.Inject

class Owner_Mapper @Inject constructor(var gson: Gson) :Mapper<RepoOwner_Entity,RepoOwner_domain> {
    override fun mapFromEntity(type: RepoOwner_Entity): RepoOwner_domain {
        return RepoOwner_domain(type.ownerName,type.html_url)
    }

    override fun mapToEntity(type: RepoOwner_domain): RepoOwner_Entity {
        return RepoOwner_Entity(type.ownerName,type.html_url)
    }

    override fun mapListFromEntity(type: List<RepoOwner_Entity>): List<RepoOwner_domain> {
        return gson.fromJson(gson.toJson(type),Array<RepoOwner_domain>::class.java).toList()

    }

    override fun mapListToEntity(type: List<RepoOwner_domain>): List<RepoOwner_Entity> {
        return gson.fromJson(gson.toJson(type),Array<RepoOwner_Entity>::class.java).toList()

    }
}