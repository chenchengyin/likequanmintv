package android.marshon.likequanmintv.controller;

import org.json.JSONObject;

/**
 * Created by It.Marshon on 2016/11/28 0028 15:55
 */

public class RoomDataController {

    private JSONObject mRoomJson;

    public RoomDataController(JSONObject roomJson) {
        mRoomJson = roomJson;
    }

    public String getPlayerPath(int type){
        JSONObject hlsJson = mRoomJson.optJSONObject("live").optJSONObject("ws").optJSONObject("hls");
//        int i = hlsJson.optInt("main_mobile");
//        if (i>0){
//
//        }
        JSONObject object0 = hlsJson.optJSONObject("0");
        if (object0!=null){
            String src = object0.optString("src");
            return src;

        }
        JSONObject object1 = hlsJson.optJSONObject("1");
        if (object1!=null){
            String src = object1.optString("src");
            return src;

        }
        JSONObject object2 = hlsJson.optJSONObject("2");
        if (object2!=null){
            String src = object2.optString("src");
            return src;

        }
        JSONObject object3 = hlsJson.optJSONObject("3");
        if (object3!=null){
            String src = object3.optString("src");
            return src;

        }
        JSONObject object4 = hlsJson.optJSONObject("4");
        if (object4!=null){
            String src = object4.optString("src");
            return src;

        }
        JSONObject object5 = hlsJson.optJSONObject("5");
        if (object4!=null){
            String src = object5.optString("src");
            return src;

        }

        return null;

    }
}
