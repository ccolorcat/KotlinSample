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

package cc.colorcat.mvp

import android.util.Log
import cc.colorcat.kingfisher.core.KingFisher
import cc.colorcat.mvp.api.AndroidCacheInterceptor
import cc.colorcat.mvp.api.JsonLoggingTailInterceptor
import cc.colorcat.mvp.extension.JsonUtils
import cc.colorcat.mvp.extension.LogUtils
import cc.colorcat.mvp.extension.image.ImageLoader
import cc.colorcat.mvp.service.ResultParserFactory
import cc.colorcat.mvp.service.SampleApi
import cc.colorcat.mvp.service.SampleApiService
import cc.colorcat.netbird.Level
import cc.colorcat.netbird.NetBird
import cc.colorcat.netbird.android.AndroidPlatform
import cc.colorcat.parser.gson.GsonParser

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
object ClientHelper {
    private lateinit var mClient: IClient
    val mService: SampleApi by lazy { SampleApiService() }

    fun init(client: IClient) {
        mClient = client
        initKingFisher(client)
        ImageLoader.init(client.context, client.debug)
        LogUtils.setThreshold(if (client.debug) Log.VERBOSE else Log.ASSERT + 1)
    }

    private fun initKingFisher(client: IClient) {
        GsonParser.setGson(JsonUtils.GSON)
        val builder = NetBird.Builder(client.baseUrl)
                .platform(AndroidPlatform())
                .connectTimeOut(10000)
                .readTimeOut(10000)
                .enableGzip(true)
        if (client.debug) {
            builder.logLevel(Level.VERBOSE).addTailInterceptor(JsonLoggingTailInterceptor())
        } else {
            builder.logLevel(Level.NOTHING)
        }
        val cacheInterceptor = AndroidCacheInterceptor.create(client.context, emptyList())
        if (cacheInterceptor != null) {
            builder.addTailInterceptor(cacheInterceptor)
        }
        KingFisher.Builder()
                .client(builder.build())
                .addParserFactory(ResultParserFactory<Any>(JsonUtils.GSON))
                .initialize()
    }

    fun releaseMemory() {
        ImageLoader.releaseMemory()
    }
}