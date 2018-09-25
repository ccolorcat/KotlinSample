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
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cc.colorcat.mvp.R
import cc.colorcat.mvp.extension.PermissionListener
import cc.colorcat.mvp.extension.Requester
import cc.colorcat.tip.Tip

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
abstract class BaseFragment : Fragment(), UI {
    protected val mChildManager: FragmentManager
        get() = childFragmentManager

    final override val mContext: Context?
        get() = this@BaseFragment.context

    final override var mExtra: Bundle? = null

    final override var mPermissionListener: PermissionListener? = null

    final override val mRequesterStore: SparseArray<Requester> by lazy {
        SparseArray<Requester>(2)
    }

    override val mTip: Tip by lazy {
        Tip.from(this@BaseFragment, R.layout.network_error, this@BaseFragment as? Tip.OnTipClickListener)
    }

    @LayoutRes
    protected open val mLayoutId: Int = -1

    private var mActive = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleExtra(arguments, false)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)
        if (mLayoutId != -1) {
            view = inflater.inflate(mLayoutId, container, false)
        }
        return view
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActive = true
    }

    final override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        handleExtra(outState, true)
    }

    final override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        handleExtra(savedInstanceState, false)
    }

    open fun handleBackPressed(): Boolean {
        return false
    }

    @CallSuper
    override fun onDestroyView() {
        mActive = false
        super.onDestroyView()
    }

    final override fun requestPermissionsCompat(permissions: Array<String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
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

    final override fun finish() {
        activity?.finish()
    }

    final override val isActive: Boolean
        get() = mActive
}