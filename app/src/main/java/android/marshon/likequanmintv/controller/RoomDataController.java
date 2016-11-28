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
        JSONObject object = hlsJson.optJSONObject("0");
        if (object!=null){
            String src = object.optString("src");
            return src;

        }else {
            JSONObject object1 = hlsJson.optJSONObject("4");
            String src = object1.optString("src");
            return src;
        }

    }
}
