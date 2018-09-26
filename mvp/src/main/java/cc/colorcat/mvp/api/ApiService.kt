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

import cc.colorcat.mvp.entity.Course
import cc.colorcat.mvp.entity.Province
import cc.colorcat.netbird.MRequest
import cc.colorcat.netbird.cache.CacheControl
import cc.colorcat.parser.gson.GsonParser

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
object ApiService : Api {
    override fun listCourses(type: Int, num: Int): ApiSender<List<Course>> {
        return object : BaseService<List<Course>>() {}
                .path("api/teacher")
                .add("type", type)
                .add("num", num)
                .get()
    }

    override fun listProvinces(): ApiSender<List<Province>> {
        return object : BaseService<List<Province>>() {
            override fun createBuilder(): MRequest.Builder<List<Province>> {
                return MRequest.Builder(object : GsonParser<List<Province>>() {})
                        .setHeader(CacheControl.HEADER_NAME_MAX_AGE, CacheControl.MAX_AGE_FOREVER.toString())
            }
        }.url("https://raw.githubusercontent.com/ccolorcat/city/master/lib/citydata.json").get()
    }
}