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

import android.content.Intent;

/**
 * Author: cxx
 * Date: 2018-07-10
 * GitHub: https://github.com/ccolorcat
 */
public class Requester {
    private static int generalRequestCode = 0x200;

    private final int mRequestCode = generalRequestCode++;
    private Intent mIntent;
    private Handler mHandler;

    public final Requester intent(Intent intent) {
        mIntent = intent;
        return this;
    }

    public final Requester handler(Handler handler) {
        mHandler = handler;
        return this;
    }

    public final int requestCode() {
        return mRequestCode;
    }

    public final Intent intent() {
        if (mIntent == null) {
            mIntent = lazyIntent();
        }
        if (mIntent == null) {
            throw new IllegalStateException("intent must be set or lazyIntent return nonnull.");
        }
        return mIntent;
    }

    public void handleResult(int resultCode, Intent data) {
        if (mHandler != null) {
            mHandler.handleResult(resultCode, data);
        }
    }

    protected Intent lazyIntent() {
        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '{' +
                "requestCode=" + mRequestCode +
                ", intent=" + mIntent +
                ", handler=" + mHandler +
                '}';
    }


    public interface Handler {
        void handleResult(int resultCode, Intent data);
    }
}
