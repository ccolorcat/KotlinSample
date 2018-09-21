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

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
abstract class ListPresenter<T> : BasePresenter<IList.View<T>>(), IList.Presenter<T> {
    protected var mHasMore = true

    override fun onCreate(view: IList.View<T>) {
        super.onCreate(view)
        doGetItems()
    }

    override fun doGetItems() {
        loadItems(false, false, getApiListener(false))
    }

    override fun toRefreshItems() {
        loadItems(true, false, getApiListener(false))
    }

    override fun toGetMoreItems() {
        if (mHasMore) {
            loadItems(false, true, getApiListener(true))
        }
    }

    abstract fun loadItems(refresh: Boolean, more: Boolean, listener: ApiListener<List<T>>)

    protected open fun getApiListener(more: Boolean): ApiListener<List<T>> {
        return object : WeakListener<IList.View<T>, List<T>>(mView) {
            override fun onStart(view: IList.View<T>) {
                super.onStart(view)
                view.setRefreshing(true)
            }

            override fun onSuccess(view: IList.View<T>, data: List<T>) {
                if (more) {
                    view.addMoreItems(data)
                } else {
                    view.refreshItems(data)
                }
                mHasMore = data.isNotEmpty()
            }

            override fun onFinish(view: IList.View<T>) {
                super.onFinish(view)
                view.setRefreshing(false)
            }
        }
    }
}