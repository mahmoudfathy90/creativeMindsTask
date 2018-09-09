package com.creativeapp.ui.repoTask

import java.io.Serializable

class Repo_Model : Serializable {

        var id: Int? = null

        var nodeId: String? = null

        var name: String? = null

        var fullName: String? = null

        var owner: Owner? = null

        var private: Boolean? = null

        var html_url: String? = null

        var description: String? = null

        var fork: Boolean? = null

        var url: String? = null

        var forksUrl: String? = null

        var keysUrl: String? = null

        var collaboratorsUrl: String? = null

        var teamsUrl: String? = null

        var hooksUrl: String? = null

        var issueEventsUrl: String? = null

        var eventsUrl: String? = null

        var assigneesUrl: String? = null

        var branchesUrl: String? = null

        var tagsUrl: String? = null

        var blobsUrl: String? = null

        var gitTagsUrl: String? = null

        var gitRefsUrl: String? = null

        var treesUrl: String? = null

        var statusesUrl: String? = null

        var languagesUrl: String? = null

        var stargazersUrl: String? = null

        var contributorsUrl: String? = null

        var subscribersUrl: String? = null

        var subscriptionUrl: String? = null

        var commitsUrl: String? = null

        var gitCommitsUrl: String? = null

        var commentsUrl: String? = null

        var issueCommentUrl: String? = null

        var contentsUrl: String? = null

        var compareUrl: String? = null

        var mergesUrl: String? = null

        var archiveUrl: String? = null

        var downloadsUrl: String? = null

        var issuesUrl: String? = null

        var pullsUrl: String? = null

        var milestonesUrl: String? = null

        var notificationsUrl: String? = null

        var labelsUrl: String? = null

        var releasesUrl: String? = null

        var deploymentsUrl: String? = null

        var createdAt: String? = null

        var updatedAt: String? = null

        var pushedAt: String? = null

        var gitUrl: String? = null

        var sshUrl: String? = null

        var cloneUrl: String? = null

        var svnUrl: String? = null

        var homepage: Any? = null

        var size: Int? = null

        var stargazersCount: Int? = null

        var watchersCount: Int? = null

        var language: String? = null

        var hasIssues: Boolean? = null

        var hasProjects: Boolean? = null

        var hasDownloads: Boolean? = null

        var hasWiki: Boolean? = null

        var hasPages: Boolean? = null

        var forksCount: Int? = null

        var mirrorUrl: Any? = null

        var archived: Boolean? = null

        var openIssuesCount: Int? = null

        var license: License? = null

        var forks: Int? = null

        var openIssues: Int? = null

        var watchers: Int? = null

        var defaultBranch: String? = null


        inner class Owner : Serializable {


                var login: String? = null

                var id: Int? = null


                var nodeId: String? = null

                var avatarUrl: String? = null

                var gravatarId: String? = null

                var url: String? = null

                var html_url: String? = null

                var followersUrl: String? = null


                var followingUrl: String? = null


                var gistsUrl: String? = null

                var starredUrl: String? = null

                var subscriptionsUrl: String? = null

                var organizationsUrl: String? = null

                var reposUrl: String? = null

                var eventsUrl: String? = null

                var receivedEventsUrl: String? = null
                var type: String? = null

                var siteAdmin: Boolean? = null


        }

        inner class License : Serializable {


                var key: String? = null

                var name: String? = null

                var spdxId: String? = null

                var url: String? = null

                var nodeId: String? = null


        }

        companion object {
                private const val serialVersionUID = 3709101119382794301L
        }

}