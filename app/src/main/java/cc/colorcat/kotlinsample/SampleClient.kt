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

package cc.colorcat.kotlinsample

import android.app.Application
import android.content.Context
import android.os.Build
import cc.colorcat.mvp.ClientHelper
import cc.colorcat.mvp.IClient

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class SampleClient : Application(), IClient {
    override val context: Context = this
    override val baseUrl: String = "http://www.imooc.com/"
    override val debug: Boolean = BuildConfig.DEBUG
    override val platform: String = "android"
    override val channel: String = "test"
    override val osVer: String = Build.VERSION.RELEASE
    override val appVer: Int = BuildConfig.VERSION_CODE

    override fun onCreate() {
        super.onCreate()
        ClientHelper.init(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ClientHelper.releaseMemory()
    }
}