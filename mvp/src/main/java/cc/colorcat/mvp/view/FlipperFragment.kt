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
import android.widget.ImageView
import cc.colorcat.flipview.FlipHolder
import cc.colorcat.flipview.SimpleFlipAdapter
import cc.colorcat.mvp.R
import cc.colorcat.mvp.contract.IFlipper
import cc.colorcat.mvp.extension.image.ImageLoader
import cc.colorcat.mvp.presenter.FlipperPresenter
import kotlinx.android.synthetic.main.fragment_flipper.*

/**
 * Author: cxx
 * Date: 2018-09-25
 * GitHub: https://github.com/ccolorcat
 */
class FlipperFragment : BaseFragment(), IFlipper.View {
    override val mLayoutId: Int = R.layout.fragment_flipper
    private val mUrls: MutableList<String> = ArrayList(5)
    private val mPresenter: IFlipper.Presenter = FlipperPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fv_flipper.adapter = object : SimpleFlipAdapter<String>(mUrls, R.layout.item_image_view) {
            override fun onBindView(holder: FlipHolder, data: String) {
                val imageView = holder.get<ImageView>(R.id.iv_image)
                ImageLoader.load(data).into(imageView)
            }

            override fun getItemPageTitle(position: Int): CharSequence {
                return "title $position"
            }
        }
        fv_flipper.addOnItemSelectedListener {
            tv_url.text = mUrls[it]
        }
        mPresenter.onCreate(this)
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    override fun refresImages(urls: List<String>) {
        mUrls.clear()
        mUrls.addAll(urls)
        fv_flipper.adapter?.notifyDataSetChanged()
    }
}