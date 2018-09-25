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

import cc.colorcat.mvp.contract.IFlipper

/**
 * Author: cxx
 * Date: 2018-09-25
 * GitHub: https://github.com/ccolorcat
 */
class FlipperPresenter : BasePresenter<IFlipper.View>(), IFlipper.Presenter {
    private val mImages = listOf(
            "https://images.unsplash.com/photo-1531927557220-a9e23c1e4794?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bfa8eb750379683a9d29f33dc26ce55a&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1489340469066-8c79cdd36ecc?ixlib=rb-0.3.5&s=ab4057d94da5e144c11adcc65002b821&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1520958044692-adcfed6ce765?ixlib=rb-0.3.5&s=968cf508bf6718423d2f93e9c6cbe753&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1537540322818-ca28d751d821?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=ba5a23b953e2b726264e9ca72f024355&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1503838836648-89abadb7979e?ixlib=rb-0.3.5&s=f526f3f5401fddfd3707219a4e9587cc&auto=format&fit=crop&w=500&q=60"
    )

    override fun onCreate(view: IFlipper.View) {
        super.onCreate(view)
        doGetImageUrls()
    }

    override fun doGetImageUrls() {
        mView?.refresImages(mImages)
    }
}