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

import cc.colorcat.mvp.contract.ITime
import cc.colorcat.mvp.entity.Day
import cc.colorcat.mvp.entity.Month
import cc.colorcat.mvp.entity.Year
import java.util.*

/**
 * Author: cxx
 * Date: 2018-09-26
 * GitHub: https://github.com/ccolorcat
 */
class TimePresenter : BasePresenter<ITime.View>(), ITime.Presenter {
    private val mYears: MutableList<Year> = ArrayList(100)

    override fun onCreate(view: ITime.View) {
        super.onCreate(view)
        doGetYears()
    }

    override fun doGetYears() {
        if (mYears.isEmpty()) {
            for (i in 1950..2050) {
                mYears.add(Year(i))
            }
        }
        mView?.refreshYears(mYears)
    }

    override fun toSelected(vararg positions: Int) {
        val year = mYears[positions[0]]
        val month = year.children()[positions[1]] as Month
        val day = month.children().get(positions[2]) as Day
        mView?.setSelectedTime("${year.value} - ${month.value} - ${day.value}")
    }
}
