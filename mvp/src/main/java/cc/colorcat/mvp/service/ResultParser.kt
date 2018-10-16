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

package cc.colorcat.mvp.service

import cc.colorcat.mvp.entity.Result
import cc.colorcat.netbird.NetworkData
import cc.colorcat.netbird.Parser
import cc.colorcat.netbird.Response
import cc.colorcat.netbird.StateIOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Author: cxx
 * Date: 2018-10-16
 * GitHub: https://github.com/ccolorcat
 */
class ResultParser<T>(private val gson: Gson, private val typeOfT: Type) : Parser<T> {
    override fun parse(response: Response): NetworkData<out T> {
        try {
            val resultType = TypeToken.getParameterized(Result::class.java, typeOfT).type
            val result: Result<T> = gson.fromJson(response.responseBody().reader(), resultType)
            val data = result.data
            return if (result.status == Result.STATUS_OK && data != null) {
                NetworkData.newSuccess(data)
            } else {
                NetworkData.newFailure(response.responseCode(), response.responseMsg())
            }
        } catch (e: Exception) {
            throw StateIOException(response.responseCode(), response.responseMsg(), e)
        }
    }
}
