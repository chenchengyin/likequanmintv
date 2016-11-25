package android.marshon.likequanmintv.bean;

/**
 * Created by It.Marshon on 2016/11/25 0025 16:32
 * desc:app启动页
 */

public class AppStart {

    /**
     * content :
     * create_at : 2016-09-09 18:02:43
     * ext : {"end_time":1480492800,"start_time":1473383349,"time":"3","type":"html"}
     * fk :
     * id : 1567
     * link : http://m.quanmin.tv/static/v2/m/boot/special/qmsd/join.html
     * priority : 1
     * slot_id : 111
     * status : 1
     * subtitle :
     * thumb : http://image.quanmin.tv/f54bab317f35eb12b69e86b2130a1e5fjpg
     * title : 全民星盛典2016
     */

    public String content;
    public String create_at;
    /**
     * end_time : 1480492800
     * start_time : 1473383349
     * time : 3
     * type : html
     */

    public ExtBean ext;
    public String fk;
    public int id;
    public String link;
    public int priority;
    public int slot_id;
    public int status;
    public String subtitle;
    public String thumb;
    public String title;

    public static class ExtBean {
        public int end_time;
        public int start_time;
        public String time;
        public String type;
    }

}
