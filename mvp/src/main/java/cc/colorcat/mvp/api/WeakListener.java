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

package cc.colorcat.mvp.api;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import cc.colorcat.mvp.contract.IBase;
import cc.colorcat.netbird.HttpStatus;


/**
 * Author: cxx
 * Date: 2018-09-21
 * GitHub: https://github.com/ccolorcat
 */
public abstract class WeakListener<V extends IBase.View, T> extends SimpleListener<T> {
    private final Reference<V> ref;

    protected WeakListener(V v) {
        this.ref = new WeakReference<>(v);
    }

    @Override
    public final void onStart() {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onStart(v);
        }
    }

    @Override
    public final void onSuccess(@NonNull T data) {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onSuccess(v, data);
        }
    }

    @Override
    public final void onFailure(int code, String msg) {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onFailure(v, code, msg);
        }
    }

    @Override
    public final void onFinish() {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onFinish(v);
            ref.clear();
        }
    }

    public void onStart(@NonNull V view) {

    }

    public abstract void onSuccess(@NonNull V view, @NonNull T data);

    @CallSuper
    public void onFailure(@NonNull V view, int code, @NonNull String msg) {
        switch (code) {
            case HttpStatus.CODE_CONNECT_ERROR:
                view.toast("网络错误");
                break;
            default:
                Log.e("NetBird", "api failure, code=" + code + ", msg=" + msg);
                break;
        }
    }

    public void onFinish(@NonNull V view) {
    }
}

