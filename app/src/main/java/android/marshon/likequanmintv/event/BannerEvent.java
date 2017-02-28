package android.marshon.likequanmintv.event;

import org.json.JSONArray;

/**
 * Created by It.Marshon on 2016/11/25 0025 16:59
 */

public class BannerEvent {
    private  JSONArray mAppfocusArray;

    public BannerEvent(JSONArray appfocusArray) {
        mAppfocusArray = appfocusArray;
    }
}
