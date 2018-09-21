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

package cc.colorcat.mvp.api

import cc.colorcat.netbird.MRequest
import cc.colorcat.netbird.cache.CacheControl
import java.io.File
import java.io.IOException
import java.lang.reflect.ParameterizedType

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
abstract class BaseService<T> protected constructor() : ApiSender<T> {
    private val mBuilder: MRequest.Builder<T> by lazy { createBuilder() }
    private lateinit var mTag: Any

    protected open fun createBuilder(): MRequest.Builder<T> {
        val pt = javaClass.genericSuperclass as ParameterizedType
        val types = pt.actualTypeArguments
        return MRequest.Builder<T>(ResultParser.create(types))
                .setHeader(CacheControl.HEADER_NAME_MAX_AGE, CacheControl.MAX_AGE_NO_CACHE.toString())
    }

    fun url(url: String): BaseService<T> {
        mBuilder.url(url)
        return this
    }

    fun path(path: String): BaseService<T> {
        mBuilder.path(path)
        return this
    }

    fun add(name: String, value: String): BaseService<T> {
        mBuilder.add(name, value)
        return this
    }

    fun add(name: String, value: Int): BaseService<T> {
        mBuilder.add(name, value.toString())
        return this
    }

    fun add(namesAndValues: Map<String, Any>): BaseService<T> {
        namesAndValues.forEach { name, value -> mBuilder.add(name, value.toString()) }
        return this
    }

    fun addFile(name: String, contentType: String, file: File): BaseService<T> {
        mBuilder.addFile(name, contentType, file)
        return this
    }

    fun get(): BaseService<T> {
        mBuilder.get()
        return this
    }

    fun head(): BaseService<T> {
        mBuilder.head()
        return this
    }

    fun trace(): BaseService<T> {
        mBuilder.trace()
        return this
    }

    fun options(): BaseService<T> {
        mBuilder.options()
        return this
    }

    fun post(): BaseService<T> {
        mBuilder.post()
        return this
    }

    fun put(): BaseService<T> {
        mBuilder.put()
        return this
    }

    fun delete(): BaseService<T> {
        mBuilder.delete()
        return this
    }

    final override fun setCacheControl(maxAge: Long): ApiSender<T> {
        mBuilder.setHeader(CacheControl.HEADER_NAME_MAX_AGE, maxAge.toString())
        return this
    }

    @Throws(IOException::class)
    final override fun execute(): T? {
        val request = mBuilder.build()
        mTag = request.tag()
        return ApiEngine.execute(request)
    }

    final override fun enqueue(listener: ApiListener<T>?) {
        mTag = ApiEngine.sendRequest(mBuilder.listener(listener).build())
    }

    final override fun enqueue(creator: () -> ApiListener<T>) {
        enqueue(creator())
    }

    override fun onSuccess(onSuccess: (T) -> Unit) {
        enqueue(object : SimpleListener<T>() {
            override fun onSuccess(result: T) {
                super.onSuccess(result)
                onSuccess(result)
            }
        })
    }

    final override fun cancel() {
        ApiEngine.cancel(mTag)
    }
}
