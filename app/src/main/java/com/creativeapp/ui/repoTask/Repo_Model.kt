package com.creativeapp.ui.repoTask

class Repo_Model (
        var name:String?,
        var description:String?,
        var html_url:String?,
        var owner:List<RepoOwner_Model>?,
        var fork:Boolean?
        )
{
}