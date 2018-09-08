package com.creative.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Repo_Entity (
        @SerializedName("name") @Expose var name:String?,
        @SerializedName("description") @Expose var description:String?,
        @SerializedName("html_url") @Expose var html_url:String?,
        @SerializedName("owner") @Expose var owner:List<RepoOwner_Entity>?,
        @SerializedName("fork") @Expose var fork:Boolean?
        )
{
}