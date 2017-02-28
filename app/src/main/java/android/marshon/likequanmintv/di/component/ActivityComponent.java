/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
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
 *
 */
package android.marshon.likequanmintv.di.component;

import android.app.Activity;
import android.content.Context;
import android.marshon.likequanmintv.di.module.ActivityModule;
import android.marshon.likequanmintv.di.scope.ContextLife;
import android.marshon.likequanmintv.di.scope.PerActivity;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.marshon.likequanmintv.mvp.live.ui.VerFullLiveUI;


import dagger.Component;


/**
 * @author Marshon
 * @version 1.0 2016/6/23
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(CommonLiveUI commonLiveUI);
    void inject(VerFullLiveUI verFullLiveUI);

//    void inject(NewsDetailActivity newsDetailActivity);
//
//    void inject(NewsChannelActivity newsChannelActivity);
//
//    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);
//
//    void inject(PhotoActivity photoActivity);
//
//    void inject(PhotoDetailActivity photoDetailActivity);
}
