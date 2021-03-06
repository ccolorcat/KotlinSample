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

import android.Manifest
import android.os.Bundle
import android.view.View
import cc.colorcat.mvp.R
import cc.colorcat.mvp.extension.*
import cc.colorcat.mvp.extension.image.ImageLoader
import kotlinx.android.synthetic.main.activity_launcher.*
import java.util.*

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class LaunchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        setTitle(R.string.demo)

        batchClick(mClick,
                btn_show_courses,
                btn_show_repos,
                btn_show_flipper,
                btn_show_time,
                btn_show_region,
                btn_request_permission,
                btn_pick_image
        )
    }

    private val mClick = View.OnClickListener {
        when (it.id) {
            R.id.btn_show_courses -> {
                navigateToFragment(CoursesFragment::class.java,
                        Const.key.title_String to getString(R.string.netbird_vangogh_rvadapter))
            }
            R.id.btn_show_repos -> {
                navigateToFragment(ReposFragment::class.java,
                        Const.key.title_String to getString(R.string.kingfisher_vangogh_rvadapter))
            }
            R.id.btn_show_flipper -> {
                navigateToFragment(FlipperFragment::class.java,
                        Const.key.title_String to getString(R.string.flip_view_vangogh))
            }
            R.id.btn_show_time -> {
                navigateToFragment(TimeFragment::class.java,
                        Const.key.title_String to getString(R.string.custom_multi_wheel_view))
            }
            R.id.btn_show_region -> {
                navigateToFragment(RegionFragment::class.java,
                        Const.key.title_String to getString(R.string.simple_multi_wheel_view))
            }
            R.id.btn_request_permission -> requestWriteExternalStoragePermission()
            R.id.btn_pick_image -> pickImage()
        }
    }

    private fun requestWriteExternalStoragePermission() {
        requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                object : PermissionListener {
                    override fun onAllGranted() {
                        toast("onAllGranted")
                    }

                    override fun onDenied(permissions: Array<String>) {
                        toast("onDenied, permissions=${Arrays.toString(permissions)}")
                    }
                }
        )
    }

    private fun pickImage() {
        ImagePickerRequester()
                .onResultOk {
                    ImageLoader.load(it!!.data).into(iv_image)
                }
                .also { startForResult(it) }
    }
}