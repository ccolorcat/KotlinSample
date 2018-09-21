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

import cc.colorcat.adapter.RvAdapter
import cc.colorcat.adapter.RvHolder
import cc.colorcat.adapter.SimpleRvAdapter
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

    override fun createAdapter(items: List<Course>): RvAdapter {
        return object : SimpleRvAdapter<Course>(items, R.layout.item_course) {
            private val transformer = CircleTransformer()

            override fun bindView(holder: RvHolder, data: Course) {
                val helper = holder.helper
                helper.setText(R.id.tv_serial_number, helper.position.toString())
                        .setText(R.id.tv_name, data.name)
                        .setText(R.id.tv_description, data.description)
                ImageLoader.load(data.picSmall)
                        .addTransformer(transformer)
                        .into(helper.get(R.id.iv_icon))
            }
        }
    }
}