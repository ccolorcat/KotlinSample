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

import cc.colorcat.mvp.api.ApiEngine
import cc.colorcat.mvp.extension.LogUtils
import cc.colorcat.mvp.extension.image.ImageLoader
import cc.colorcat.mvp.service.SampleApi
import cc.colorcat.mvp.service.SampleApiService

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
        ApiEngine.init(client.context, client.baseUrl, client.debug)
        ImageLoader.init(client.context, client.debug)
        if (client.debug) {
            LogUtils.allowAll()
        } else {
            LogUtils.disallowAny()
        }
    }

    fun releaseMemory() {
        ImageLoader.releaseMemory()
    }
}