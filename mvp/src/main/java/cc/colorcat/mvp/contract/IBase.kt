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

package cc.colorcat.mvp.contract

import android.support.annotation.StringRes

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
interface IBase {
    interface View {
        val isActive: Boolean

        fun showTip()

        fun hideTip()

        fun isTipShowing(): Boolean

        fun toast(@StringRes resId: Int)

        fun toast(text: CharSequence)

        fun finish()
    }

    interface Presenter<in V : View> {
        fun onCreate(view: V)

        fun onDestroy()
    }
}