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
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import cc.colorcat.adapter.AutoChoiceRvAdapter
import cc.colorcat.adapter.ChoiceRvAdapter
import cc.colorcat.adapter.RvAdapter
import cc.colorcat.adapter.RvHolder
import cc.colorcat.mvp.R
import cc.colorcat.mvp.contract.IList
import cc.colorcat.mvp.entity.Course
import cc.colorcat.mvp.extension.image.CircleTransformer
import cc.colorcat.mvp.extension.image.ImageLoader
import cc.colorcat.mvp.presenter.CoursesPresenter

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class CoursesFragment : ListFragment<Course>() {
    override val mPresenter: IList.Presenter<Course> = CoursesPresenter()
    private lateinit var mAdapter: ChoiceRvAdapter

    override fun createAdapter(items: List<Course>): RvAdapter {
        mAdapter = object : AutoChoiceRvAdapter() {
            private val transformer = CircleTransformer()

            override fun bindView(holder: RvHolder, position: Int) {
                val data = items[position]
                val helper = holder.helper
                helper.setText(R.id.tv_serial_number, helper.position.toString())
                        .setText(R.id.tv_name, data.name)
                        .setText(R.id.tv_description, data.description)
                ImageLoader.load(data.picSmall)
                        .addTransformer(transformer)
                        .into(helper.get(R.id.iv_icon))
            }

            override fun getLayoutResId(viewType: Int): Int = R.layout.item_course

            override fun getItemCount(): Int = items.size
        }
        return mAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.choice_mode, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.single -> setChoiceMode(ChoiceRvAdapter.ChoiceMode.SINGLE)
            R.id.multiple -> setChoiceMode(ChoiceRvAdapter.ChoiceMode.MULTIPLE)
            R.id.none -> setChoiceMode(ChoiceRvAdapter.ChoiceMode.NONE)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setChoiceMode(@ChoiceRvAdapter.ChoiceMode mode: Int) {
        mAdapter.choiceMode = mode
        mAdapter.notifyDataSetChanged()
    }
}