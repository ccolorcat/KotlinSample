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

import cc.colorcat.mvp.entity.Year

/**
 * Author: cxx
 * Date: 2018-09-26
 * GitHub: https://github.com/ccolorcat
 */
interface ITime {
    interface View : IBase.View {
        fun refreshYears(years: List<Year>)

        fun setSelectedTime(time: String)
    }

    interface Presenter : IBase.Presenter<View> {
        fun doGetYears()

        fun toSelected(vararg positions: Int)
    }
}