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

import android.graphics.Color
import android.support.annotation.ColorInt
import cc.colorcat.vangogh.CornerTransformation

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class CornerTransformer private constructor(
        cornerRadius: FloatArray?,
        borderWidth: Float,
        @ColorInt borderColor: Int,
        type: Int
) : CornerTransformation(cornerRadius, borderWidth, borderColor, type), Transformer {
    companion object {
        const val TYPE_TL = CornerTransformation.TYPE_TL
        const val TYPE_TR = CornerTransformation.TYPE_TR
        const val TYPE_BR = CornerTransformation.TYPE_BR
        const val TYPE_BL = CornerTransformation.TYPE_BL
        const val TYPE_ALL = TYPE_TL or TYPE_TR or TYPE_BR or TYPE_BL

        @JvmStatic
        fun create(type: Int): CornerTransformer {
            return create(type, 0.0f, Color.WHITE)
        }

        @JvmStatic
        fun create(type: Int, borderWidth: Float, @ColorInt borderColor: Int): CornerTransformer {
            return CornerTransformer(null, borderWidth, borderColor, type)
        }

        @JvmStatic
        fun create(cornerRadius: FloatArray): CornerTransformer {
            return create(cornerRadius, 0.0f, Color.WHITE)
        }

        @JvmStatic
        fun create(cornerRadius: FloatArray, borderWidth: Float, @ColorInt borderColor: Int): CornerTransformer {
            return if (cornerRadius.size != 8) {
                throw IllegalArgumentException("cornerRadius.length != 8")
            } else {
                CornerTransformer(cornerRadius, borderWidth, borderColor, 0)
            }
        }
    }
}
