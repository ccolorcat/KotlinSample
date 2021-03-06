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

package cc.colorcat.mvp.extension;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;

import cc.colorcat.gsonutil.GsonUtils;

/**
 * Author: cxx
 * Date: 2018-09-27
 * GitHub: https://github.com/ccolorcat
 */
public class JsonUtils {
    public static final Gson GSON = GsonUtils.getGson();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(Reader reader, Type typeOfT) {
        return GSON.fromJson(reader, typeOfT);
    }

    private JsonUtils() {throw new AssertionError("no instance");}
}
