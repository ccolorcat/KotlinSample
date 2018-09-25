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
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import cc.colorcat.adapter.RvAdapter
import cc.colorcat.mvp.R
import cc.colorcat.mvp.contract.IList
import cc.colorcat.mvp.extension.RvLoadMoreScrollListener
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
abstract class ListFragment<T> : BaseFragment(), IList.View<T> {
    override val mLayoutId: Int = R.layout.fragment_list

    abstract val mPresenter: IList.Presenter<T>
    protected open val mItemDecoration: RecyclerView.ItemDecoration?
        get() = null
    protected open val mLayoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(mContext)


    protected open val mRefreshEnabled = true
    protected open val mLoadMoreEnabled = false
    protected val mItems: MutableList<T> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_items.layoutManager = mLayoutManager
        rv_items.adapter = createAdapter(mItems)
        mItemDecoration?.also { rv_items.addItemDecoration(it) }

        if (mRefreshEnabled) {
            srl_root.setOnRefreshListener {
                mPresenter.toRefreshItems()
            }
        } else {
            srl_root.isEnabled = false
        }

        if (mLoadMoreEnabled) {
            rv_items.addOnScrollListener(object : RvLoadMoreScrollListener(1) {
                override fun onLoadMore() {
                    mPresenter.toGetMoreItems()
                }
            })
        }

        mPresenter.onCreate(this)
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    override fun refreshItems(items: List<T>) {
        mItems.clear()
        mItems.addAll(items)
        rv_items.adapter?.notifyDataSetChanged()
    }

    override fun addMoreItems(items: List<T>) {
        val start = mItems.size
        mItems.addAll(items)
        rv_items.adapter?.notifyItemRangeInserted(start, items.size)
    }

    override fun setRefreshing(refreshing: Boolean) {
        srl_root.isRefreshing = refreshing
    }

    abstract fun createAdapter(items: List<T>): RvAdapter
}