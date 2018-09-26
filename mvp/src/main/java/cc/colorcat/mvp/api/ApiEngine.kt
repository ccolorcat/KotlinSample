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

package cc.colorcat.mvp.api

import android.content.Context
import cc.colorcat.mvp.extension.json.JsonUtils
import cc.colorcat.netbird.Level
import cc.colorcat.netbird.MRequest
import cc.colorcat.netbird.NetBird
import cc.colorcat.netbird.android.AndroidPlatform
import cc.colorcat.parser.gson.GsonParser
import java.io.IOException

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
object ApiEngine {
    private lateinit var netBird: NetBird

    fun init(context: Context, baseUrl: String, debug: Boolean) {
        GsonParser.setGson(JsonUtils.GSON)
        val builder = NetBird.Builder(baseUrl)
                .platform(AndroidPlatform())
                .connectTimeOut(10000)
                .readTimeOut(10000)
                .enableGzip(true)
        if (debug) {
            builder.logLevel(Level.VERBOSE).addTailInterceptor(JsonLoggingTailInterceptor())
        } else {
            builder.logLevel(Level.NOTHING)
        }
        val cacheInterceptor = AndroidCacheInterceptor.create(context, emptyList())
        if (cacheInterceptor != null) {
            builder.addTailInterceptor(cacheInterceptor)
        }
        netBird = builder.build()
    }

    fun <T> sendRequest(request: MRequest<T>): Any {
        return netBird.send(request)
    }

    @Throws(IOException::class)
    fun <T> execute(request: MRequest<T>): T? {
        return netBird.execute(request)
    }

    fun cancel(tag: Any) {
        netBird.cancelWaiting(tag)
    }
}
