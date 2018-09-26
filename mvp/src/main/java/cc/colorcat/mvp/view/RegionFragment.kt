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
import android.view.View
import cc.colorcat.mvp.R
import cc.colorcat.mvp.contract.IRegion
import cc.colorcat.mvp.entity.Province
import cc.colorcat.mvp.presenter.RegionPresenter
import cc.colorcat.tip.Tip
import kotlinx.android.synthetic.main.fragment_multi_wheel_view.*

/**
 * Author: cxx
 * Date: 2018-09-26
 * GitHub: https://github.com/ccolorcat
 */
class RegionFragment : BaseFragment(), IRegion.View, Tip.OnTipClickListener {
    override val mTip: Tip by lazy {
        Tip.from(this@RegionFragment, R.layout.load_data_failed, this@RegionFragment)
    }
    override val mLayoutId: Int = R.layout.fragment_multi_wheel_view
    private val mPresenter: IRegion.Presenter = RegionPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        multi_wheel_view.addOnSelectedChangeListener {
            mPresenter.toSelected(*it)
        }
        mPresenter.onCreate(this)
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    override fun refreshProvinces(provinces: List<Province>) {
        multi_wheel_view.updateData(provinces)
    }

    override fun setAddress(address: String) {
        tv_content.text = address
    }

    override fun onTipClick() {
        mPresenter.toRefreshProvinces()
    }
}