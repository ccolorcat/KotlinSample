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

import android.graphics.drawable.Drawable
import android.widget.TextView
import cc.colorcat.vangogh.CompoundViewTarget

/**
 * Author: cxx
 * Date: 2018-07-24
 * GitHub: https://github.com/ccolorcat
 */
class CompoundTarget<V : TextView> private constructor(view: V, private val compound: Int) : ImageTarget<V>(view) {
    companion object {
        const val COMPOUND_START = CompoundViewTarget.COMPOUND_START
        const val COMPOUND_TOP = CompoundViewTarget.COMPOUND_TOP
        const val COMPOUND_END = CompoundViewTarget.COMPOUND_END
        const val COMPOUND_BOTTOM = CompoundViewTarget.COMPOUND_BOTTOM
        const val COMPOUND_ALL = CompoundViewTarget.COMPOUND_ALL

        @JvmStatic
        fun <V : TextView> createDrawStart(v: V): CompoundTarget<V> {
            return create(v, COMPOUND_START)
        }

        @JvmStatic
        fun <V : TextView> createDrawTop(v: V): CompoundTarget<V> {
            return create(v, COMPOUND_TOP)
        }

        @JvmStatic
        fun <V : TextView> createDrawEnd(v: V): CompoundTarget<V> {
            return create(v, COMPOUND_END)
        }

        @JvmStatic
        fun <V : TextView> createDrawBottom(v: V): CompoundTarget<V> {
            return create(v, COMPOUND_BOTTOM)
        }

        @JvmStatic
        fun <V : TextView> create(v: V, compound: Int): CompoundTarget<V> {
            if (compound and COMPOUND_ALL == 0) {
                throw IllegalArgumentException("illegal compound == $compound")
            }
            return CompoundTarget(v, compound)
        }
    }

    override fun setDrawable(view: V, drawable: Drawable?) {
        val drawables = view.compoundDrawables
        for (i in 0..3) {
            if (compound shr i and 1 != 0) {
                drawables[i] = drawable
            }
        }
        view.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
    }
}