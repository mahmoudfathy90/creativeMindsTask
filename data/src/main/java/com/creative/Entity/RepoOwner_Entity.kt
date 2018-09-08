package com.creative.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepoOwner_Entity
(
        @SerializedName("login") @Expose var ownerName: String,
        @SerializedName("html_url") @Expose var html_url: String

) {
}