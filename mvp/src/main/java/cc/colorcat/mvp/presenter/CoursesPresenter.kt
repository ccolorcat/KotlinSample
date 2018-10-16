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

import cc.colorcat.mvp.api.ApiListener
import cc.colorcat.mvp.api.WeakListener
import cc.colorcat.mvp.contract.IList
import cc.colorcat.mvp.entity.Course
import cc.colorcat.netbird.HttpStatus

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class CoursesPresenter : ListPresenter<Course>() {
    override fun loadItems(refresh: Boolean, more: Boolean, listener: ApiListener<List<Course>>) {
//        ApiService.listCourses(4, 30).enqueue(listener)
        mService.listCourses(4, 30).enqueue(listener)
    }

    override fun getApiListener(more: Boolean): ApiListener<List<Course>> {
        return object : WeakListener<IList.View<Course>, List<Course>>(mView) {
            override fun onStart(view: IList.View<Course>) {
                super.onStart(view)
                view.setRefreshing(true)
            }

            override fun onSuccess(view: IList.View<Course>, data: List<Course>) {
                view.hideTip()
                if (more) {
                    view.addMoreItems(data)
                } else {
                    view.refreshItems(data)
                }
                mHasMore = data.isNotEmpty()
            }

            override fun onFailure(view: IList.View<Course>, code: Int, msg: String) {
                if (code == HttpStatus.CODE_CONNECT_ERROR) {
                    view.showTip()
                }
            }

            override fun onFinish(view: IList.View<Course>) {
                super.onFinish(view)
                view.setRefreshing(false)
            }
        }
    }
}
