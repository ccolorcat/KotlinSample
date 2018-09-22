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

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.util.SparseArray
import android.widget.Toast
import cc.colorcat.mvp.contract.IBase
import cc.colorcat.mvp.extension.Const
import cc.colorcat.mvp.extension.PermissionListener
import cc.colorcat.mvp.extension.Requester
import cc.colorcat.mvp.extension.hasPermission
import cc.colorcat.tip.Tip

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
interface UI : ViewNavigator, IBase.View {
    companion object {
        const val REQUEST_CODE_PERMISSION = 0x900
    }

    var mPermissionListener: PermissionListener?

    val mRequesterStore: SparseArray<Requester>

    val mTip: Tip

    fun requestPermissions(permissions: Array<String>, onAllGranted: () -> Unit) {
        requestPermissions(permissions, object : PermissionListener {
            override fun onAllGranted() {
                onAllGranted()
            }
        })
    }

    fun requestPermissions(permissions: Array<String>, listener: PermissionListener?) {
        mPermissionListener = listener
        val denied = permissions.filter { !mContext!!.hasPermission(it) }
        if (denied.isEmpty()) {
            mPermissionListener?.onAllGranted()
            mPermissionListener = null
        } else {
            requestPermissionsCompat(denied.toTypedArray(), UI.REQUEST_CODE_PERMISSION)
        }
    }

    fun requestPermissionsCompat(permissions: Array<String>, requestCode: Int)

    fun handleRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        mPermissionListener?.apply {
            if (requestCode == UI.REQUEST_CODE_PERMISSION) {
                val denied = grantResults.indices.filter {
                    grantResults[it] != PackageManager.PERMISSION_GRANTED
                }.map {
                    permissions[it]
                }
                if (denied.isEmpty()) {
                    onAllGranted()
                } else {
                    onDenied(denied.toTypedArray())
                }
            }
            mPermissionListener = null
        }
    }

    fun startForResult(requester: Requester) {
        mRequesterStore.put(requester.requestCode(), requester)
        startForResult(requester.intent(), requester.requestCode())
    }

    fun startForResult(intent: Intent, requestCode: Int)

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mRequesterStore[requestCode]?.also {
            mRequesterStore.delete(requestCode)
            it.handleResult(resultCode, data)
        }
    }

    fun navigateToFragment(clazz: Class<out BaseFragment>, vararg pairs: Pair<String, Any>) {
        navigateTo(ContainerActivity::class.java, Const.key.fragment_name_String to clazz.name, *pairs)
    }

    override fun showTip() {
        mTip.showTip()
    }

    override fun hideTip() {
        mTip.hideTip()
    }

    override fun isTipShowing(): Boolean = mTip.isShowing


    override fun toast(@StringRes resId: Int) {
        mContext?.also {
            Toast.makeText(it, resId, Toast.LENGTH_SHORT).show()
        }
    }

    override fun toast(text: CharSequence) {
        mContext?.also {
            Toast.makeText(it, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun obtainColor(@ColorRes id: Int): Int {
        val ctx = mContext!!
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ctx.getColor(id)
        } else {
            @Suppress("DEPRECATION")
            ctx.resources.getColor(id)
        }
    }

    fun obtainColorStateList(@ColorRes id: Int): ColorStateList? {
        val ctx = mContext!!
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ctx.getColorStateList(id)
        } else {
            @Suppress("DEPRECATION")
            ctx.resources.getColorStateList(id)
        }
    }
}