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

package cc.colorcat.mvp.service;

import java.util.List;

import cc.colorcat.kingfisher.annotation.Api;
import cc.colorcat.kingfisher.annotation.GET;
import cc.colorcat.kingfisher.annotation.Param;
import cc.colorcat.kingfisher.annotation.Path;
import cc.colorcat.kingfisher.annotation.Url;
import cc.colorcat.kingfisher.core.Call;
import cc.colorcat.mvp.entity.Course;
import cc.colorcat.mvp.entity.Province;
import cc.colorcat.mvp.entity.Repo;

/**
 * Author: cxx
 * Date: 2018-10-16
 * GitHub: https://github.com/ccolorcat
 */
@Api
public interface SampleApi {
    @GET("api/teacher")
    Call<List<Course>> listCourses(@Param("type") int type, @Param("num") int num);

    @Url("https://raw.githubusercontent.com/ccolorcat/city/master/lib/citydata.json")
    @GET
    Call<List<Province>> listProvinces();

    @Url("https://api.github.com/")
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
