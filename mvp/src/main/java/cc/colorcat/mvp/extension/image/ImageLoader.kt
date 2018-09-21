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

package cc.colorcat.mvp.extension.image

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.annotation.DrawableRes
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import cc.colorcat.mvp.IClient
import cc.colorcat.mvp.extension.getService
import cc.colorcat.vangogh.Creator
import cc.colorcat.vangogh.From
import cc.colorcat.vangogh.Task
import cc.colorcat.vangogh.VanGogh
import java.io.File

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class ImageLoader private constructor(private val creator: Creator) {

    init {
        creator.tag(ImageLoader.TAG_SCROLL_IMAGE)
    }

    fun config(config: Bitmap.Config): ImageLoader {
        creator.config(config)
        return this
    }

    fun addTransformer(transformer: Transformer): ImageLoader {
        creator.addTransformation(transformer)
        return this
    }

    fun placeholder(@DrawableRes resId: Int): ImageLoader {
        creator.placeholder(resId)
        return this
    }

    fun placeholder(placeholder: Drawable): ImageLoader {
        creator.placeholder(placeholder)
        return this
    }

    fun error(@DrawableRes resId: Int): ImageLoader {
        creator.error(resId)
        return this
    }

    fun error(error: Drawable): ImageLoader {
        creator.error(error)
        return this
    }

    fun maxSize(maxWidth: Int, maxHeight: Int): ImageLoader {
        creator.maxSize(maxWidth, maxHeight)
        return this
    }

    fun maxSizeWithDp(maxWidth: Int, maxHeight: Int): ImageLoader {
        creator.maxSizeWithDP(maxWidth, maxHeight)
        return this
    }

    fun resize(width: Int, height: Int): ImageLoader {
        creator.resize(width, height)
        return this
    }

    fun resizeWithDp(width: Int, height: Int): ImageLoader {
        creator.resizeWithDP(width, height)
        return this
    }

    fun from(from: Int): ImageLoader {
        creator.from(from)
        return this
    }

    fun centerCrop(): ImageLoader {
        creator.centerCrop()
        return this
    }

    fun centerInside(): ImageLoader {
        creator.centerInside()
        return this
    }

    fun fitXY(): ImageLoader {
        creator.fitXY()
        return this
    }

    fun listener(listener: ImageListener?): ImageLoader {
        creator.callback(listener)
        return this
    }

    fun tag(tag: Any): ImageLoader {
        creator.tag(tag)
        return this
    }

    fun fetch() {
        creator.fetch()
    }

    fun <V : View> into(target: ImageTarget<V>) {
        creator.into(target)
    }

    fun into(view: ImageView) {
        creator.into(view)
    }


    companion object {
        const val TAG_SCROLL_IMAGE = "ScrollImage"

        @JvmStatic
        val from_memory = From.MEMORY.policy
        val from_disk = From.DISK.policy
        val from_network = From.NETWORK.policy
        val from_any = From.ANY.policy

        @JvmStatic
        fun init(client: IClient) {
            val ctx = client.context
            val debug = client.debug
            val usableSpace = ctx.getService<ActivityManager>(Context.ACTIVITY_SERVICE).memoryClass
            val memoryCacheSize = Math.min((usableSpace * 1024 * 1024 / 7).toLong(), 1024L * 1024L * 20L)

            val size = Point()
            ctx.getService<WindowManager>(Context.WINDOW_SERVICE).defaultDisplay.getSize(size)
            val maxSize = Math.max(size.x, size.y)
            val global = Task.Options()
            global.maxSize(maxSize, maxSize)
            global.config(Bitmap.Config.RGB_565)

            val builder = VanGogh.Builder(ctx)
                    .connectTimeOut(10000)
                    .readTimeOut(10000)
                    .fade(true)
                    .memoryCacheSize(memoryCacheSize)
                    .options(global)
                    .indicator(debug)
                    .log(debug)
            val cacheDir = ctx.externalCacheDir
            if (cacheDir != null) {
                val maxCacheSize = Math.min(300L * 1024L * 1024L, (cacheDir.usableSpace * 0.1).toLong())
                builder.diskCache(cacheDir).diskCacheSize(maxCacheSize)
            }
            VanGogh.setSingleton(builder.build())
        }

        @JvmStatic
        fun releaseMemory() {
            VanGogh.get().clearMemoryCache()
        }

        @JvmStatic
        fun createUri(context: Context, @DrawableRes resId: Int): Uri {
            return VanGogh.toUri(context, resId)
        }

        @JvmStatic
        fun load(url: String?) = ImageLoader(VanGogh.get().load(url))

        @JvmStatic
        fun load(@DrawableRes resId: Int) = ImageLoader(VanGogh.get().load(resId))

        @JvmStatic
        fun load(file: File?) = ImageLoader(VanGogh.get().load(file))

        @JvmStatic
        fun load(uri: Uri?) = ImageLoader(VanGogh.get().load(uri))

        @JvmStatic
        fun resume(tag: Any) {
            VanGogh.get().resumeTag(tag)
        }

        @JvmStatic
        fun pause(tag: Any) {
            VanGogh.get().pauseTag(tag)
        }
    }
}
