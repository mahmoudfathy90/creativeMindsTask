package com.creative.domain.model

class Repo_Domain (
        var name:String?,
        var description:String?,
       var html_url:String?,
         var owner:List<RepoOwner_domain>?,
        var fork:Boolean?
        )
{
}