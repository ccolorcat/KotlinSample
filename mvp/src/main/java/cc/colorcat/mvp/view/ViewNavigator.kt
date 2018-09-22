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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable

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

        fun <T : Fragment> newFragment(clazz: Class<T>, vararg pairs: Pair<String, Any>): T {
            return newFragment(clazz, bundleOf(*pairs))
        }

        fun <T : Fragment> newFragment(clazz: Class<T>, extra: Bundle?): T {
            val fragment = clazz.newInstance()
            extra?.also {
                val args = Bundle(1)
                args.putBundle(EXTRA, it)
                fragment.arguments = args
            }
            return fragment
        }

        fun newIntent(context: Context, clazz: Class<out Activity>, vararg pairs: Pair<String, Any>): Intent {
            return newIntent(context, clazz, bundleOf(*pairs))
        }

        fun newIntent(context: Context, clazz: Class<out Activity>, extra: Bundle? = null): Intent {
            val intent = Intent(context, clazz)
            extra?.also { intent.putExtra(EXTRA, it) }
            return intent
        }

        fun bundleOf(vararg pairs: Pair<String, Any>): Bundle? {
            if (pairs.isEmpty()) return null
            val args = Bundle()
            pairs.forEach { (k, v) ->
                when (v) {
                    is CharSequence -> args.putCharSequence(k, v)
                    is Int -> args.putInt(k, v)
                    is Parcelable -> args.putParcelable(k, v)
                    is Serializable -> args.putSerializable(k, v)
                    is Long -> args.putLong(k, v)
                    is Char -> args.putChar(k, v)
                    is Float -> args.putFloat(k, v)
                    is Double -> args.putDouble(k, v)
                    is Short -> args.putShort(k, v)
                    is Byte -> args.putByte(k, v)
                    else -> throw IllegalArgumentException("unsupported data type, type=${v.javaClass}, value=$v")
                }
            }
            return args
        }
    }


    val mContext: Context?

    var mExtra: Bundle?

    fun navigateTo(clazz: Class<out Activity>, vararg pairs: Pair<String, Any>) {
        navigateTo(clazz, bundleOf(*pairs))
    }

    fun navigateTo(clazz: Class<out Activity>, extra: Bundle? = null, processor: ((Intent) -> Unit)? = null) {
        mContext?.also {
            val intent = ViewNavigator.newIntent(it, clazz, extra)
            if (processor != null) {
                processor(intent)
            }
            it.startActivity(intent)
        }
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