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
import cc.colorcat.mvp.R
import cc.colorcat.mvp.extension.Const

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class ContainerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        @Suppress("UNCHECKED_CAST")
        val clazz = Class.forName(getExtra(Const.key.fragment_name_String)!!) as Class<BaseFragment>
        mManager.beginTransaction()
                .replace(R.id.fl_container, newFragment(clazz, extra!!), clazz.name)
                .commit()
    }
}