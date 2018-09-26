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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cc.colorcat.mvp.R
import cc.colorcat.mvp.contract.ITime
import cc.colorcat.mvp.entity.Day
import cc.colorcat.mvp.entity.Month
import cc.colorcat.mvp.entity.Year
import cc.colorcat.mvp.presenter.TimePresenter
import cc.colorcat.wheelview.MultiWheelView
import cc.colorcat.wheelview.WheelView
import kotlinx.android.synthetic.main.fragment_multi_wheel_view.*

/**
 * Author: cxx
 * Date: 2018-09-26
 * GitHub: https://github.com/ccolorcat
 */
class TimeFragment : BaseFragment(), ITime.View {
    override val mLayoutId: Int = R.layout.fragment_multi_wheel_view
    private val mPresenter: ITime.Presenter = TimePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(multi_wheel_view) {
            addOnSelectedChangeListener(object : MultiWheelView.SafeOnSelectedChangeListener() {
                override fun onSafeSelectedChanged(vararg positions: Int) {
                    mPresenter.toSelected(*positions)
                }
            })
            setMultiItemAdapter(0, YearAdapter())
            setMultiItemAdapter(1, MonthAdapter())
            setMultiItemAdapter(2, DayAdapter())
        }
        mPresenter.onCreate(this)
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    override fun refreshYears(years: List<Year>) {
        multi_wheel_view.updateData(years)
    }

    override fun setSelectedTime(time: String) {
        tv_content.text = time
    }


    private class YearAdapter : MultiWheelView.MultiItemAdapter<WheelView.ItemHolder>() {
        override fun onCreateItemHolder(parent: ViewGroup, itemLayout: Int): WheelView.ItemHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_time_node, parent, false)
            return WheelView.ItemHolder(itemView)
        }

        override fun onBindItemHolder(holder: WheelView.ItemHolder, data: MultiWheelView.Node) {
            holder.textView.text = data.contentToString()
            holder.textView.isSelected = isLeapYear((data as Year).value)
        }
    }

    private class MonthAdapter : MultiWheelView.MultiItemAdapter<WheelView.ItemHolder>() {

        override fun onCreateItemHolder(parent: ViewGroup, itemLayout: Int): WheelView.ItemHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_time_node, parent, false)
            return WheelView.ItemHolder(itemView)
        }

        override fun onBindItemHolder(holder: WheelView.ItemHolder, data: MultiWheelView.Node) {
            val month = data as Month
            var value = month.value
            if (value > 7) {
                --value
            }
            holder.textView.text = data.contentToString()
            holder.textView.isSelected = (value and 1) != 0
        }
    }

    private class DayAdapter : MultiWheelView.MultiItemAdapter<DayItemHolder>() {
        override fun onCreateItemHolder(parent: ViewGroup, itemLayout: Int): DayItemHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
            return DayItemHolder(itemView)
        }

        override fun onBindItemHolder(holder: DayItemHolder, data: MultiWheelView.Node) {
            val day = data as Day
            holder.textView.text = data.contentToString()
            holder.text2.text = ((day.value + 6) / 7).toString()
        }
    }

    private class DayItemHolder internal constructor(itemView: View) : WheelView.ItemHolder(itemView) {
        val text2: TextView = itemView.findViewById(android.R.id.text2)
    }

    private companion object {
        private fun isLeapYear(year: Int): Boolean {
            return year % 400 == 0 || year % 4 == 0 && year % 100 != 0
        }
    }
}