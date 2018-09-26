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

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
public class Const {
    public static final long DEFAULT_MAX_AGE_CACHE = 5L * 60L * 1000L;

    public static class key {
        public static final String fragment_name_String = "fragment_name";
        public static final String title_String = "title";

        private key() {throw new AssertionError("no instance");}
    }

    public static class msg {
        private msg() {throw new AssertionError("no instance");}
    }

    private Const() {throw new AssertionError("no instance");}
}
