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
import android.support.annotation.CallSuper
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import cc.colorcat.mvp.R
import cc.colorcat.mvp.extension.PermissionListener
import cc.colorcat.mvp.extension.Requester
import cc.colorcat.tip.Tip

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
abstract class BaseActivity : AppCompatActivity(), UI {
    protected val manager: FragmentManager
        get() = supportFragmentManager

    final override val isActive: Boolean
        get() = !isFinishing

    final override val mContext: Context
        get() = this@BaseActivity

    final override var mPermissionListener: PermissionListener? = null

    final override val mRequesterStore: SparseArray<Requester> by lazy { SparseArray<Requester>(4) }

    final override var extra: Bundle? = null

    override val mTip: Tip by lazy {
        Tip.from(this, R.layout.network_error, this@BaseActivity as? Tip.OnTipClickListener)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleExtra(intent.extras, false)
    }

    final override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        handleExtra(outState, true)
    }

    final override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        handleExtra(savedInstanceState, false)
    }

    override fun onBackPressed() {
        for (fragment in manager.fragments) {
            fragment as BaseFragment
            if (fragment.isActive && fragment.isVisible && fragment.handleBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }

    final override fun requestPermissionsCompat(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    final override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        handleRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    final override fun startForResult(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

    final override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleActivityResult(requestCode, resultCode, data)
    }

    final override fun startActivity(intent: Intent, finish: Boolean) {
        startActivity(intent)
        if (finish) {
            finish()
        }
    }
}