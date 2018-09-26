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

import cc.colorcat.mvp.api.ApiService
import cc.colorcat.mvp.api.WeakListener
import cc.colorcat.mvp.contract.IRegion
import cc.colorcat.mvp.entity.Province
import cc.colorcat.mvp.entity.RegionNode

/**
 * Author: cxx
 * Date: 2018-09-26
 * GitHub: https://github.com/ccolorcat
 */
class RegionPresenter : BasePresenter<IRegion.View>(), IRegion.Presenter {
    private val mProvinces: MutableList<Province> = ArrayList(34)
    private val mCountry: RegionNode = object : RegionNode() {
        override fun children(): List<RegionNode> {
            return mProvinces
        }
    }

    override fun onCreate(view: IRegion.View) {
        super.onCreate(view)
        doGetProvinces()
    }

    override fun doGetProvinces() {
        toRefreshProvinces()
    }

    override fun toRefreshProvinces() {
        ApiService.listProvinces().enqueue(object : WeakListener<IRegion.View, List<Province>>(mView) {
            override fun onSuccess(view: IRegion.View, data: List<Province>) {
                mProvinces.clear()
                mProvinces.addAll(data)
                view.refreshProvinces(data)
                view.hideTip()
            }

            override fun onFailure(view: IRegion.View, code: Int, msg: String) {
                super.onFailure(view, code, msg)
                view.showTip()
            }
        })
    }

    override fun toSelected(vararg positions: Int) {
        var node = mCountry
        var address = ""
        for (position in positions) {
            if (position != -1) {
                val next = node.children()[position]
                address += next.name
                node = next
            } else {
                break
            }
        }
        mView?.setAddress(address)
    }
}
