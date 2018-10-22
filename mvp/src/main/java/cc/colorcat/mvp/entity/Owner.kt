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
data class Owner(
        /**
         * login : ccolorcat
         * id : 39815818
         * node_id : MDQ6VXNlcjM5ODE1ODE4
         * avatar_url : https://avatars1.githubusercontent.com/u/39815818?v=4
         * gravatar_id :
         * url : https://api.github.com/users/ccolorcat
         * html_url : https://github.com/ccolorcat
         * followers_url : https://api.github.com/users/ccolorcat/followers
         * following_url : https://api.github.com/users/ccolorcat/following{/other_user}
         * gists_url : https://api.github.com/users/ccolorcat/gists{/gist_id}
         * starred_url : https://api.github.com/users/ccolorcat/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/ccolorcat/subscriptions
         * organizations_url : https://api.github.com/users/ccolorcat/orgs
         * repos_url : https://api.github.com/users/ccolorcat/repos
         * events_url : https://api.github.com/users/ccolorcat/events{/privacy}
         * received_events_url : https://api.github.com/users/ccolorcat/received_events
         * type : User
         * site_admin : false
         */

        @JsonProperty("login")
        @JSONField(name = "login")
        @SerializedName("login")
        var login: String = "",
        @JsonProperty("id")
        @JSONField(name = "id")
        @SerializedName("id")
        var id: Int = 0,
        @JsonProperty("node_id")
        @JSONField(name = "node_id")
        @SerializedName("node_id")
        var nodeId: String = "",
        @JsonProperty("avatar_url")
        @JSONField(name = "avatar_url")
        @SerializedName("avatar_url")
        var avatarUrl: String = "",
        @JsonProperty("gravatar_id")
        @JSONField(name = "gravatar_id")
        @SerializedName("gravatar_id")
        var gravatarId: String = "",
        @JsonProperty("url")
        @JSONField(name = "url")
        @SerializedName("url")
        var url: String = "",
        @JsonProperty("html_url")
        @JSONField(name = "html_url")
        @SerializedName("html_url")
        var htmlUrl: String = "",
        @JsonProperty("followers_url")
        @JSONField(name = "followers_url")
        @SerializedName("followers_url")
        var followersUrl: String = "",
        @JsonProperty("following_url")
        @JSONField(name = "following_url")
        @SerializedName("following_url")
        var followingUrl: String = "",
        @JsonProperty("gists_url")
        @JSONField(name = "gists_url")
        @SerializedName("gists_url")
        var gistsUrl: String = "",
        @JsonProperty("starred_url")
        @JSONField(name = "starred_url")
        @SerializedName("starred_url")
        var starredUrl: String = "",
        @JsonProperty("subscriptions_url")
        @JSONField(name = "subscriptions_url")
        @SerializedName("subscriptions_url")
        var subscriptionsUrl: String = "",
        @JsonProperty("organizations_url")
        @JSONField(name = "organizations_url")
        @SerializedName("organizations_url")
        var organizationsUrl: String = "",
        @JsonProperty("repos_url")
        @JSONField(name = "repos_url")
        @SerializedName("repos_url")
        var reposUrl: String = "",
        @JsonProperty("events_url")
        @JSONField(name = "events_url")
        @SerializedName("events_url")
        var eventsUrl: String = "",
        @JsonProperty("received_events_url")
        @JSONField(name = "received_events_url")
        @SerializedName("received_events_url")
        var receivedEventsUrl: String = "",
        @JsonProperty("type")
        @JSONField(name = "type")
        @SerializedName("type")
        var type: String = "",
        @JsonProperty("site_admin")
        @JSONField(name = "site_admin")
        @SerializedName("site_admin")
        var isSiteAdmin: Boolean = false
)
