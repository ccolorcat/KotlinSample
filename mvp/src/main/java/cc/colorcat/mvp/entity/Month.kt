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

package cc.colorcat.mvp.entity

import android.util.SparseArray
import cc.colorcat.wheelview.MultiWheelView
import java.util.*

/**
 * Author: cxx
 * Date: 2018-09-26
 * GitHub: https://github.com/ccolorcat
 */
data class Month(override val value: Int, private val year: Year) : TimeNode() {
    private companion object {
        private val Days = SparseArray<List<Day>>(4)

        init {
            val days28 = ArrayList<Day>(28)
            val days29 = ArrayList<Day>(29)
            val days30 = ArrayList<Day>(30)
            val days31 = ArrayList<Day>(31)
            for (i in 1..31) {
                days31.add(Day(i))
            }
            days30.addAll(days31.subList(0, 30))
            days29.addAll(days31.subList(0, 29))
            days28.addAll(days31.subList(0, 28))
            Days.put(31, days31)
            Days.put(30, days30)
            Days.put(29, days29)
            Days.put(28, days28)
        }
    }

    private lateinit var days: List<Day>

    override fun children(): List<MultiWheelView.Node> {
        if (!this::days.isInitialized) {
            val calendar = Calendar.getInstance()
            calendar.clear()
            calendar.set(Calendar.YEAR, year.value)
            calendar.set(Calendar.MONTH, this.value - 1)
            days = Days.get(calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        }
        return days
    }
}