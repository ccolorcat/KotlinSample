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

import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import cc.colorcat.adapter.RvAdapter
import cc.colorcat.adapter.RvHolder
import cc.colorcat.adapter.SimpleRvAdapter
import cc.colorcat.mvp.R
import cc.colorcat.mvp.contract.IList
import cc.colorcat.mvp.entity.Repo
import cc.colorcat.mvp.presenter.ReposPresenter
import cc.colorcat.tip.Tip
import cc.colorcat.vangogh.VanGogh
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Author: cxx
 * Date: 2018-10-22
 * GitHub: https://github.com/ccolorcat
 */
class ReposFragment : ListFragment<Repo>(), Tip.OnTipClickListener {
    override val mTip: Tip by lazy {
        Tip.from(srl_root, R.layout.network_error, this@ReposFragment)
    }
    override val mPresenter: IList.Presenter<Repo> = ReposPresenter()

    override fun createAdapter(items: List<Repo>): RvAdapter {
        return object : SimpleRvAdapter<Repo>(items, R.layout.item_repo) {
            override fun bindView(holder: RvHolder, data: Repo) {
                val helper = holder.helper
                val license = data.license
                helper.setText(R.id.tv_name, data.name)
                        .setText(R.id.tv_description, data.description)
                        .setVisibility(R.id.tv_description, if (TextUtils.isEmpty(data.description)) View.GONE else View.VISIBLE)
                        .setText(R.id.tv_license, license?.name)
                        .setVisibility(R.id.tv_license, if (TextUtils.isEmpty(license?.name)) View.GONE else View.VISIBLE)
                        .setText(R.id.tv_html_link, Html.fromHtml(data.htmlUrl))
                VanGogh.get().load(data.owner?.avatarUrl).into(helper.get<View>(R.id.iv_icon) as ImageView)
            }
        }
    }

    override fun onTipClick() {
        mPresenter.toRefreshItems()
    }
}
