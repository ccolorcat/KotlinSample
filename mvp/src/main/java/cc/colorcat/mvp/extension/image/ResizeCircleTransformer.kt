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

import android.graphics.Bitmap
import android.graphics.Matrix
import android.support.annotation.ColorInt

import cc.colorcat.vangogh.CircleTransformation

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
class ResizeCircleTransformer : CircleTransformation, Transformer {
    private val width: Int
    private val height: Int

    private constructor(width: Int, height: Int) : super() {
        this.width = width
        this.height = height
    }

    private constructor(width: Int, height: Int, borderWidth: Float, @ColorInt borderColor: Int) : super(borderWidth, borderColor) {
        this.width = width
        this.height = height
    }

    override fun transform(source: Bitmap): Bitmap {
        val bitmap = super.transform(source)
        val reqWidth = this.width
        val reqHeight = this.height
        val width = bitmap.width
        val height = bitmap.height
        if (reqWidth != width || reqHeight != height) {
            val matrix = Matrix()
            val scaleX = reqWidth.toFloat() / width
            val scaleY = reqHeight.toFloat() / height
            val scale = Math.min(scaleX, scaleY)
            matrix.postScale(scale, scale)
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        }
        return bitmap
    }

    companion object {
        @JvmStatic
        fun create(width: Int, height: Int): ResizeCircleTransformer {
            checkSize(width, height)
            return ResizeCircleTransformer(width, height)
        }

        @JvmStatic
        fun create(width: Int, height: Int, borderWidth: Float, @ColorInt borderColor: Int): ResizeCircleTransformer {
            checkSize(width, height)
            return ResizeCircleTransformer(width, height, borderWidth, borderColor)
        }

        @JvmStatic
        private fun checkSize(width: Int, height: Int) {
            if (width <= 0 || height <= 0) {
                throw IllegalArgumentException("width and height must be positive")
            }
        }
    }
}
