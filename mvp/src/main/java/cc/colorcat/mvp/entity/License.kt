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
data class License(
        @JSONField(name = "key")
        @JsonProperty("key")
        @SerializedName("key")
        var key: String = "",
        @JSONField(name = "name")
        @JsonProperty("name")
        @SerializedName("name")
        var name: String = "",
        @JSONField(name = "spdx_id")
        @JsonProperty("spdx_id")
        @SerializedName("spdx_id")
        var spdxId: String = "",
        @JSONField(name = "url")
        @JsonProperty("url")
        @SerializedName("url")
        var url: String = "",
        @JSONField(name = "node_id")
        @JsonProperty("node_id")
        @SerializedName("node_id")
        var nodeId: String = ""
)