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

import android.util.Log;

/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
public class LogUtils {
    private static final int MAX_LENGTH = 2 * 1024;

    private static String tag = "Client";
    private static int threshold = Log.ASSERT + 1;

    public static void setGlobalTag(String tag) {
        LogUtils.tag = tag;
    }

    public static void setThreshold(int threshold) {
        LogUtils.threshold = threshold;
    }

    public static void allowAll() {
        LogUtils.threshold = Log.VERBOSE;
    }

    public static void disallowAny() {
        LogUtils.threshold = Log.ASSERT + 1;
    }

    public static void v(String msg) {
        v(tag, msg);
    }

    public static void v(String tag, String msg) {
        log(tag, msg, Log.VERBOSE);
    }

    public static void d(String msg) {
        d(tag, msg);
    }

    public static void d(String tag, String msg) {
        log(tag, msg, Log.DEBUG);
    }

    public static void i(String msg) {
        i(tag, msg);
    }

    public static void i(String tag, String msg) {
        log(tag, msg, Log.INFO);
    }

    public static void w(String msg) {
        w(tag, msg);
    }

    public static void w(String tag, String msg) {
        log(tag, msg, Log.WARN);
    }

    public static void e(String msg) {
        e(tag, msg);
    }

    public static void e(String tag, String msg) {
        log(tag, msg, Log.ERROR);
    }

    public static void e(Throwable throwable) {
        if (Log.ERROR >= LogUtils.threshold) {
            throwable.printStackTrace();
        }
    }

    public static void wtf(String msg) {
        wtf(tag, msg);
    }

    public static void wtf(String tag, String msg) {
        log(tag, msg, LogUtils.threshold);
    }

    private static void log(String tag, String msg, int priority) {
        if (priority < LogUtils.threshold) return;
        final int length = msg.length();
        if (length <= MAX_LENGTH) {
            Log.println(priority, tag, msg);
            return;
        }
        for (int start = 0, end; start < length; start = end) {
            end = friendlyEnd(msg, start, Math.min(start + MAX_LENGTH, length));
            Log.println(priority, tag, msg.substring(start, end));
        }
    }

    private static int friendlyEnd(String msg, int start, int end) {
        if (msg.length() == end || msg.charAt(end) == '\n') {
            return end;
        }
        for (int last = end - 1; last > start; --last) {
            if (msg.charAt(last) == '\n') {
                return last + 1;
            }
        }
        return end;
    }

    private LogUtils() { throw new AssertionError("no instance");}
}
