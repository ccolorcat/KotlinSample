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

package cc.colorcat.mvp.presenter

import android.support.annotation.CallSuper
import cc.colorcat.mvp.contract.IBase

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
abstract class BasePresenter<V : IBase.View> : IBase.Presenter<V> {
    protected var mView: V? = null

    @CallSuper
    override fun onCreate(view: V) {
        mView = view
    }

    @CallSuper
    override fun onDestroy() {
        mView = null
    }
}
