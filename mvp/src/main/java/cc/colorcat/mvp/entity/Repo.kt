/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.mvp.entity

import com.alibaba.fastjson.annotation.JSONField
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
data class Repo(
        /**
         * id : 135659546
         * node_id : MDEwOlJlcG9zaXRvcnkxMzU2NTk1NDY=
         * name : Adapter
         * full_name : ccolorcat/Adapter
         * owner : {"login":"ccolorcat","id":39815818,"node_id":"MDQ6VXNlcjM5ODE1ODE4","avatar_url":"https://avatars1.githubusercontent.com/u/39815818?v=4","gravatar_id":"","url":"https://api.github.com/users/ccolorcat","html_url":"https://github.com/ccolorcat","followers_url":"https://api.github.com/users/ccolorcat/followers","following_url":"https://api.github.com/users/ccolorcat/following{/other_user}","gists_url":"https://api.github.com/users/ccolorcat/gists{/gist_id}","starred_url":"https://api.github.com/users/ccolorcat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/ccolorcat/subscriptions","organizations_url":"https://api.github.com/users/ccolorcat/orgs","repos_url":"https://api.github.com/users/ccolorcat/repos","events_url":"https://api.github.com/users/ccolorcat/events{/privacy}","received_events_url":"https://api.github.com/users/ccolorcat/received_events","type":"User","site_admin":false}
         * private : false
         * html_url : https://github.com/ccolorcat/Adapter
         * description : ListView 和 RecyclerView 的通用 Adapter
         * fork : false
         * url : https://api.github.com/repos/ccolorcat/Adapter
         * forks_url : https://api.github.com/repos/ccolorcat/Adapter/forks
         * keys_url : https://api.github.com/repos/ccolorcat/Adapter/keys{/key_id}
         * collaborators_url : https://api.github.com/repos/ccolorcat/Adapter/collaborators{/collaborator}
         * teams_url : https://api.github.com/repos/ccolorcat/Adapter/teams
         * hooks_url : https://api.github.com/repos/ccolorcat/Adapter/hooks
         * issue_events_url : https://api.github.com/repos/ccolorcat/Adapter/issues/events{/number}
         * events_url : https://api.github.com/repos/ccolorcat/Adapter/events
         * assignees_url : https://api.github.com/repos/ccolorcat/Adapter/assignees{/user}
         * branches_url : https://api.github.com/repos/ccolorcat/Adapter/branches{/branch}
         * tags_url : https://api.github.com/repos/ccolorcat/Adapter/tags
         * blobs_url : https://api.github.com/repos/ccolorcat/Adapter/git/blobs{/sha}
         * git_tags_url : https://api.github.com/repos/ccolorcat/Adapter/git/tags{/sha}
         * git_refs_url : https://api.github.com/repos/ccolorcat/Adapter/git/refs{/sha}
         * trees_url : https://api.github.com/repos/ccolorcat/Adapter/git/trees{/sha}
         * statuses_url : https://api.github.com/repos/ccolorcat/Adapter/statuses/{sha}
         * languages_url : https://api.github.com/repos/ccolorcat/Adapter/languages
         * stargazers_url : https://api.github.com/repos/ccolorcat/Adapter/stargazers
         * contributors_url : https://api.github.com/repos/ccolorcat/Adapter/contributors
         * subscribers_url : https://api.github.com/repos/ccolorcat/Adapter/subscribers
         * subscription_url : https://api.github.com/repos/ccolorcat/Adapter/subscription
         * commits_url : https://api.github.com/repos/ccolorcat/Adapter/commits{/sha}
         * git_commits_url : https://api.github.com/repos/ccolorcat/Adapter/git/commits{/sha}
         * comments_url : https://api.github.com/repos/ccolorcat/Adapter/comments{/number}
         * issue_comment_url : https://api.github.com/repos/ccolorcat/Adapter/issues/comments{/number}
         * contents_url : https://api.github.com/repos/ccolorcat/Adapter/contents/{+path}
         * compare_url : https://api.github.com/repos/ccolorcat/Adapter/compare/{base}...{head}
         * merges_url : https://api.github.com/repos/ccolorcat/Adapter/merges
         * archive_url : https://api.github.com/repos/ccolorcat/Adapter/{archive_format}{/ref}
         * downloads_url : https://api.github.com/repos/ccolorcat/Adapter/downloads
         * issues_url : https://api.github.com/repos/ccolorcat/Adapter/issues{/number}
         * pulls_url : https://api.github.com/repos/ccolorcat/Adapter/pulls{/number}
         * milestones_url : https://api.github.com/repos/ccolorcat/Adapter/milestones{/number}
         * notifications_url : https://api.github.com/repos/ccolorcat/Adapter/notifications{?since,all,participating}
         * labels_url : https://api.github.com/repos/ccolorcat/Adapter/labels{/name}
         * releases_url : https://api.github.com/repos/ccolorcat/Adapter/releases{/id}
         * deployments_url : https://api.github.com/repos/ccolorcat/Adapter/deployments
         * created_at : 2018-06-01T02:48:12Z
         * updated_at : 2018-08-16T15:10:38Z
         * pushed_at : 2018-08-16T15:10:36Z
         * git_url : git://github.com/ccolorcat/Adapter.git
         * ssh_url : git@github.com:ccolorcat/Adapter.git
         * clone_url : https://github.com/ccolorcat/Adapter.git
         * svn_url : https://github.com/ccolorcat/Adapter
         * homepage : null
         * size : 363
         * stargazers_count : 0
         * watchers_count : 0
         * language : Java
         * has_issues : true
         * has_projects : true
         * has_downloads : true
         * has_wiki : true
         * has_pages : false
         * forks_count : 0
         * mirror_url : null
         * archived : false
         * open_issues_count : 0
         * license : {"key":"apache-2.0","name":"Apache License 2.0","spdx_id":"Apache-2.0","url":"https://api.github.com/licenses/apache-2.0","node_id":"MDc6TGljZW5zZTI="}
         * forks : 0
         * open_issues : 0
         * watchers : 0
         * default_branch : master
         */

        @JSONField(name = "id")
        @JsonProperty("id")
        @SerializedName("id")
        var id: Int = 0,
        @JSONField(name = "node_id")
        @JsonProperty("node_id")
        @SerializedName("node_id")
        var nodeId: String = "",
        @JSONField(name = "name")
        @JsonProperty("name")
        @SerializedName("name")
        var name: String = "",
        @JSONField(name = "full_name")
        @JsonProperty("full_name")
        @SerializedName("full_name")
        var fullName: String = "",
        @JSONField(name = "owner")
        @JsonProperty("owner")
        @SerializedName("owner")
        var owner: Owner? = null,
        @JSONField(name = "private")
        @JsonProperty("private")
        @SerializedName("private")
        var isPrivateX: Boolean = false,
        @JSONField(name = "html_url")
        @JsonProperty("html_url")
        @SerializedName("html_url")
        var htmlUrl: String = "",
        @JSONField(name = "description")
        @JsonProperty("description")
        @SerializedName("description")
        var description: String = "",
        @JSONField(name = "fork")
        @JsonProperty("fork")
        @SerializedName("fork")
        var isFork: Boolean = false,
        @JSONField(name = "url")
        @JsonProperty("url")
        @SerializedName("url")
        var url: String = "",
        @JSONField(name = "forks_url")
        @JsonProperty("forks_url")
        @SerializedName("forks_url")
        var forksUrl: String = "",
        @JSONField(name = "keys_url")
        @JsonProperty("keys_url")
        @SerializedName("keys_url")
        var keysUrl: String = "",
        @JSONField(name = "collaborators_url")
        @JsonProperty("collaborators_url")
        @SerializedName("collaborators_url")
        var collaboratorsUrl: String = "",
        @JSONField(name = "teams_url")
        @JsonProperty("teams_url")
        @SerializedName("teams_url")
        var teamsUrl: String = "",
        @JSONField(name = "hooks_url")
        @JsonProperty("hooks_url")
        @SerializedName("hooks_url")
        var hooksUrl: String = "",
        @JSONField(name = "issue_events_url")
        @JsonProperty("issue_events_url")
        @SerializedName("issue_events_url")
        var issueEventsUrl: String = "",
        @JSONField(name = "events_url")
        @JsonProperty("events_url")
        @SerializedName("events_url")
        var eventsUrl: String = "",
        @JSONField(name = "assignees_url")
        @JsonProperty("assignees_url")
        @SerializedName("assignees_url")
        var assigneesUrl: String = "",
        @JSONField(name = "branches_url")
        @JsonProperty("branches_url")
        @SerializedName("branches_url")
        var branchesUrl: String = "",
        @JSONField(name = "tags_url")
        @JsonProperty("tags_url")
        @SerializedName("tags_url")
        var tagsUrl: String = "",
        @JSONField(name = "blobs_url")
        @JsonProperty("blobs_url")
        @SerializedName("blobs_url")
        var blobsUrl: String = "",
        @JSONField(name = "git_tags_url")
        @JsonProperty("git_tags_url")
        @SerializedName("git_tags_url")
        var gitTagsUrl: String = "",
        @JSONField(name = "git_refs_url")
        @JsonProperty("git_refs_url")
        @SerializedName("git_refs_url")
        var gitRefsUrl: String = "",
        @JSONField(name = "trees_url")
        @JsonProperty("trees_url")
        @SerializedName("trees_url")
        var treesUrl: String = "",
        @JSONField(name = "statuses_url")
        @JsonProperty("statuses_url")
        @SerializedName("statuses_url")
        var statusesUrl: String = "",
        @JSONField(name = "languages_url")
        @JsonProperty("languages_url")
        @SerializedName("languages_url")
        var languagesUrl: String = "",
        @JSONField(name = "stargazers_url")
        @JsonProperty("stargazers_url")
        @SerializedName("stargazers_url")
        var stargazersUrl: String = "",
        @JSONField(name = "contributors_url")
        @JsonProperty("contributors_url")
        @SerializedName("contributors_url")
        var contributorsUrl: String = "",
        @JSONField(name = "subscribers_url")
        @JsonProperty("subscribers_url")
        @SerializedName("subscribers_url")
        var subscribersUrl: String = "",
        @JSONField(name = "subscription_url")
        @JsonProperty("subscription_url")
        @SerializedName("subscription_url")
        var subscriptionUrl: String = "",
        @JSONField(name = "commits_url")
        @JsonProperty("commits_url")
        @SerializedName("commits_url")
        var commitsUrl: String = "",
        @JSONField(name = "git_commits_url")
        @JsonProperty("git_commits_url")
        @SerializedName("git_commits_url")
        var gitCommitsUrl: String = "",
        @JSONField(name = "comments_url")
        @JsonProperty("comments_url")
        @SerializedName("comments_url")
        var commentsUrl: String = "",
        @JSONField(name = "issue_comment_url")
        @JsonProperty("issue_comment_url")
        @SerializedName("issue_comment_url")
        var issueCommentUrl: String = "",
        @JSONField(name = "contents_url")
        @JsonProperty("contents_url")
        @SerializedName("contents_url")
        var contentsUrl: String = "",
        @JSONField(name = "compare_url")
        @JsonProperty("compare_url")
        @SerializedName("compare_url")
        var compareUrl: String = "",
        @JSONField(name = "merges_url")
        @JsonProperty("merges_url")
        @SerializedName("merges_url")
        var mergesUrl: String = "",
        @JSONField(name = "archive_url")
        @JsonProperty("archive_url")
        @SerializedName("archive_url")
        var archiveUrl: String = "",
        @JSONField(name = "downloads_url")
        @JsonProperty("downloads_url")
        @SerializedName("downloads_url")
        var downloadsUrl: String = "",
        @JSONField(name = "issues_url")
        @JsonProperty("issues_url")
        @SerializedName("issues_url")
        var issuesUrl: String = "",
        @JSONField(name = "pulls_url")
        @JsonProperty("pulls_url")
        @SerializedName("pulls_url")
        var pullsUrl: String = "",
        @JSONField(name = "milestones_url")
        @JsonProperty("milestones_url")
        @SerializedName("milestones_url")
        var milestonesUrl: String = "",
        @JSONField(name = "notifications_url")
        @JsonProperty("notifications_url")
        @SerializedName("notifications_url")
        var notificationsUrl: String = "",
        @JSONField(name = "labels_url")
        @JsonProperty("labels_url")
        @SerializedName("labels_url")
        var labelsUrl: String = "",
        @JSONField(name = "releases_url")
        @JsonProperty("releases_url")
        @SerializedName("releases_url")
        var releasesUrl: String = "",
        @JSONField(name = "deployments_url")
        @JsonProperty("deployments_url")
        @SerializedName("deployments_url")
        var deploymentsUrl: String = "",
        @JSONField(name = "created_at")
        @JsonProperty("created_at")
        @SerializedName("created_at")
        var createdAt: String = "",
        @JSONField(name = "updated_at")
        @JsonProperty("updated_at")
        @SerializedName("updated_at")
        var updatedAt: String = "",
        @JSONField(name = "pushed_at")
        @JsonProperty("pushed_at")
        @SerializedName("pushed_at")
        var pushedAt: String = "",
        @JSONField(name = "git_url")
        @JsonProperty("git_url")
        @SerializedName("git_url")
        var gitUrl: String = "",
        @JSONField(name = "ssh_url")
        @JsonProperty("ssh_url")
        @SerializedName("ssh_url")
        var sshUrl: String = "",
        @JSONField(name = "clone_url")
        @JsonProperty("clone_url")
        @SerializedName("clone_url")
        var cloneUrl: String = "",
        @JSONField(name = "svn_url")
        @JsonProperty("svn_url")
        @SerializedName("svn_url")
        var svnUrl: String = "",
        @JSONField(name = "homepage")
        @JsonProperty("homepage")
        @SerializedName("homepage")
        var homepage: String = "",
        @JSONField(name = "size")
        @JsonProperty("size")
        @SerializedName("size")
        var size: Int = 0,
        @JSONField(name = "stargazers_count")
        @JsonProperty("stargazers_count")
        @SerializedName("stargazers_count")
        var stargazersCount: Int = 0,
        @JSONField(name = "watchers_count")
        @JsonProperty("watchers_count")
        @SerializedName("watchers_count")
        var watchersCount: Int = 0,
        @JSONField(name = "language")
        @JsonProperty("language")
        @SerializedName("language")
        var language: String = "",
        @JSONField(name = "has_issues")
        @JsonProperty("has_issues")
        @SerializedName("has_issues")
        var isHasIssues: Boolean = false,
        @JSONField(name = "has_projects")
        @JsonProperty("has_projects")
        @SerializedName("has_projects")
        var isHasProjects: Boolean = false,
        @JSONField(name = "has_downloads")
        @JsonProperty("has_downloads")
        @SerializedName("has_downloads")
        var isHasDownloads: Boolean = false,
        @JSONField(name = "has_wiki")
        @JsonProperty("has_wiki")
        @SerializedName("has_wiki")
        var isHasWiki: Boolean = false,
        @JSONField(name = "has_pages")
        @JsonProperty("has_pages")
        @SerializedName("has_pages")
        var isHasPages: Boolean = false,
        @JSONField(name = "forks_count")
        @JsonProperty("forks_count")
        @SerializedName("forks_count")
        var forksCount: Int = 0,
        @JSONField(name = "mirror_url")
        @JsonProperty("mirror_url")
        @SerializedName("mirror_url")
        var mirrorUrl: String = "",
        @JSONField(name = "archived")
        @JsonProperty("archived")
        @SerializedName("archived")
        var isArchived: Boolean = false,
        @JSONField(name = "open_issues_count")
        @JsonProperty("open_issues_count")
        @SerializedName("open_issues_count")
        var openIssuesCount: Int = 0,
        @JSONField(name = "license")
        @JsonProperty("license")
        @SerializedName("license")
        var license: License? = null,
        @JSONField(name = "forks")
        @JsonProperty("forks")
        @SerializedName("forks")
        var forks: Int = 0,
        @JSONField(name = "open_issues")
        @JsonProperty("open_issues")
        @SerializedName("open_issues")
        var openIssues: Int = 0,
        @JSONField(name = "watchers")
        @JsonProperty("watchers")
        @SerializedName("watchers")
        var watchers: Int = 0,
        @JSONField(name = "default_branch")
        @JsonProperty("default_branch")
        @SerializedName("default_branch")
        var defaultBranch: String = ""
)
