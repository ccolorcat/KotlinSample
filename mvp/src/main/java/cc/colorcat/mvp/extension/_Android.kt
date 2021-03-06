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

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

@Suppress("UNCHECKED_CAST")
internal fun <T> Context.getService(service: String): T = this.getSystemService(service) as T

@SuppressLint("HardwareIds")
fun getAndroidId(context: Context, defaultValue: String): String {
    return try {
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun getUmengChannel(context: Context, defaultValue: String): String {
    return try {
        context.packageManager
                .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
                .metaData
                .getString("UMENG_CHANNEL", defaultValue)
    } catch (e: PackageManager.NameNotFoundException) {
        defaultValue
    }
}

internal fun batchClick(listener: View.OnClickListener, vararg views: View) {
    for (view in views) {
        view.setOnClickListener(listener)
    }
}

internal fun batchVisibility(visibility: Int, vararg views: View) {
    for (view in views) {
        view.visibility = visibility
    }
}

internal fun Context.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
}

internal fun ViewPager.onPageSelected(callback: (Int) -> Unit) {
    this.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            callback(position)
        }
    })
}

internal fun TabLayout.onTabSelected(callback: (TabLayout.Tab) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            callback(tab)
        }
    })
}

internal fun ViewGroup.applyToChildView(processor: (View) -> Unit) {
    for (index in 0 until childCount) {
        val view = getChildAt(index)
        processor(view)
        if (view is ViewGroup) {
            view.applyToChildView(processor)
        }
    }
}

fun Requester.onResultOk(receiver: (data: Intent?) -> Unit): Requester {
    return handler { resultCode, data ->
        if (resultCode == Activity.RESULT_OK) {
            receiver(data)
        }
    }
}
