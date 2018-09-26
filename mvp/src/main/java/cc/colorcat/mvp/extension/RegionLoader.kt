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

package cc.colorcat.mvp.extension

import android.content.Context
import android.os.AsyncTask
import cc.colorcat.mvp.R
import cc.colorcat.mvp.entity.Province
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.util.*

/**
 * Author: cxx
 * Date: 2018-08-13
 * GitHub: https://github.com/ccolorcat
 */
class RegionLoader(private val mContext: Context) {
    private val mProvinces = ArrayList<Province>(31)

    fun get(): List<Province> {
        return Collections.unmodifiableList(mProvinces)
    }

    fun load(listener: OnLoadedListener) {
        if (!mProvinces.isEmpty()) {
            listener.onLoaded(get())
            return
        }
        AsyncLoader(object : OnLoadedListener {
            override fun onLoaded(provinces: List<Province>) {
                mProvinces.clear()
                mProvinces.addAll(provinces)
                listener.onLoaded(provinces)
            }
        }).execute(mContext)
    }


    private class AsyncLoader(private val mListener: OnLoadedListener) : AsyncTask<Context, Void, List<Province>>() {

        override fun doInBackground(vararg contexts: Context): List<Province> {
            val reader = InputStreamReader(contexts[0].resources.openRawResource(R.raw.region))
            val token = object : TypeToken<List<Province>>() {}
            return GsonBuilder().create().fromJson(reader, token.type)
        }

        override fun onPostExecute(provinces: List<Province>) {
            super.onPostExecute(provinces)
            mListener.onLoaded(provinces)
        }
    }


    interface OnLoadedListener {
        fun onLoaded(provinces: List<Province>)
    }
}
