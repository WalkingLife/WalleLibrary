package org.wall.mo.http;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Copyright (C), 2018-2020
 * Author: ziqimo
 * Date: 2020-01-21 17:03
 * Description:
 * History: 管理 Call
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class CallXLife implements LifecycleObserver {

    private List<Call> callLists = new ArrayList<>();

    private Lifecycle lifecycle;

    private CallXLife(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
        if (this.lifecycle != null) {
            this.lifecycle.addObserver(this);
        }
    }

    /**
     * 每个P或者VM都独立一个
     *
     * @return
     */
    public static CallXLife getCallLife(Lifecycle lifecycle) {
        return new CallXLife(lifecycle);
    }

    public void add(Call call) {
        callLists.add(call);
    }

    private void remove() {
        for (Call call : callLists) {
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
        callLists.clear();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        remove();
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
    }

}
