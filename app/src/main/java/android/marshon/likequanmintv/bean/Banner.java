package android.marshon.likequanmintv.bean;

import java.io.Serializable;

/**
 * Created by It.Marshon on 2016/11/25 0025 16:37
 * desc：app 首页Banner图
 */

public class Banner  implements Serializable{

    /**
     * content :
     * create_at : 2016-04-29 12:41:37
     * ext : {"type":"play"}
     * fk : 2498193
     * id : 616
     * link :
     * link_object : {"announcement":"","app_shuffling_image":"","avatar":"http://image.quanmin.tv/avatar/a84e775a933a18539b9c0d9f48b2bd28jpg?imageView2/2/w/300/","beauty_cover":"","category_id":"13","category_name":"全民户外","category_slug":"huwai","create_at":"2016-11-25 13:55:42","default_image":"","follow":"46547","grade":"","hidden":false,"intro":"","level":"0","like":"0","love_cover":"","nick":"皂滑丶弄人","play_at":"2016-11-25 13:55:45","recommend_image":"","screen":0,"slug":"","start_time":"2016-11-25 13:55:45","status":"1","thumb":"http://image.quanmin.tv/28e8cbdba1d65aaf857853785a15002fjpg","title":"韩国 太阳的后裔拍摄地探访","uid":"2498193","video":"http://thumb.quanmin.tv/2498193.mp4?t=1480060800","video_quality":"","view":"19196","weight":"3109460"}
     * priority : 1
     * slot_id : 113
     * status : 1
     * subtitle :
     * thumb : http://image.quanmin.tv/28e8cbdba1d65aaf857853785a15002fjpg
     * title : 韩国吃喝拿三天两夜又来啦
     */

    public String content;
    public String create_at;
    /**
     * type : play
     */

    public ExtBean ext;
    public String fk;
    public int id;
    public String link;
    /**
     * announcement :
     * app_shuffling_image :
     * avatar : http://image.quanmin.tv/avatar/a84e775a933a18539b9c0d9f48b2bd28jpg?imageView2/2/w/300/
     * beauty_cover :
     * category_id : 13
     * category_name : 全民户外
     * category_slug : huwai
     * create_at : 2016-11-25 13:55:42
     * default_image :
     * follow : 46547
     * grade :
     * hidden : false
     * intro :
     * level : 0
     * like : 0
     * love_cover :
     * nick : 皂滑丶弄人
     * play_at : 2016-11-25 13:55:45
     * recommend_image :
     * screen : 0
     * slug :
     * start_time : 2016-11-25 13:55:45
     * status : 1
     * thumb : http://image.quanmin.tv/28e8cbdba1d65aaf857853785a15002fjpg
     * title : 韩国 太阳的后裔拍摄地探访
     * uid : 2498193
     * video : http://thumb.quanmin.tv/2498193.mp4?t=1480060800
     * video_quality :
     * view : 19196
     * weight : 3109460
     */

    public PlayBean link_object;
    public int priority;
    public int slot_id;
    public int status;
    public String subtitle;
    public String thumb;
    public String title;

    public static class ExtBean {

        public String type;
    }

}
