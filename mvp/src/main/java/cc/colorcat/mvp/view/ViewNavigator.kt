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

package cc.colorcat.mvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cc.colorcat.mvp.extension.bundleOf

/**
 * 用于 Fragment 的实例生成和 Activity 的跳转，主要解决传入数据的自动保存与恢复。
 * 目前仅支持 Number, Char, CharSequence, Parcelable 和 Serializable 这几类数据。
 * 不建议传入过大的数据如 Bitmap 等，数据过大可能会丢失，传输较大的数据建议使用通信类框架。
 *
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
interface ViewNavigator {
    companion object {
        const val EXTRA = "cc.colorcat.mvp.view.ViewNavigator"

        fun <T : BaseFragment> newFragment(clazz: Class<T>, vararg pairs: Pair<String, Any>): T {
            return if (pairs.isEmpty()) clazz.newInstance() else newFragment(clazz, bundleOf(*pairs))
        }

        fun <T : BaseFragment> newFragment(clazz: Class<T>, extra: Bundle): T {
            val fragment = clazz.newInstance()
            val args = Bundle(1)
            args.putBundle(EXTRA, extra)
            fragment.arguments = args
            return fragment
        }

        fun <T : BaseActivity> newIntent(context: Context, clazz: Class<T>, vararg pairs: Pair<String, Any>): Intent {
            return if (pairs.isEmpty()) Intent(context, clazz) else newIntent(context, clazz, bundleOf(*pairs))
        }

        fun <T : BaseActivity> newIntent(context: Context, clazz: Class<T>, extra: Bundle): Intent {
            val intent = Intent(context, clazz)
            intent.putExtra(EXTRA, extra)
            return intent
        }
    }


    var mExtra: Bundle?

    fun <T : BaseFragment> newFragment(clazz: Class<T>, vararg pairs: Pair<String, Any>): T {
        return if (pairs.isEmpty()) clazz.newInstance() else newFragment(clazz, bundleOf(*pairs))
    }

    fun <T : BaseFragment> newFragment(clazz: Class<T>, extra: Bundle): T {
        val fragment = clazz.newInstance()
        val args = Bundle(1)
        args.putBundle(EXTRA, extra)
        fragment.arguments = args
        return fragment
    }

    fun <T : BaseActivity> newIntent(context: Context, clazz: Class<T>, vararg pairs: Pair<String, Any>): Intent {
        return if (pairs.isEmpty()) Intent(context, clazz) else newIntent(context, clazz, bundleOf(*pairs))
    }

    fun <T : BaseActivity> newIntent(context: Context, clazz: Class<T>, extra: Bundle): Intent {
        val intent = Intent(context, clazz)
        intent.putExtra(EXTRA, extra)
        return intent
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getExtra(key: String): T? {
        return mExtra?.get(key) as T?
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getExtra(key: String, defaultValue: T): T {
        return mExtra?.get(key) as? T ?: defaultValue
    }

    fun handleExtra(state: Bundle?, save: Boolean) {
        if (save) {
            mExtra?.also { state?.putBundle(EXTRA, it) }
        } else {
            state?.also { mExtra = it.getBundle(EXTRA) }
        }
    }
}