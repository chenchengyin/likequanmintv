package android.marshon.likequanmintv.bean.livehouse;

import java.io.Serializable;

/**
 * Created by It.Marshon on 2016/11/28 0028 15:30
 */

public class RoomLine  implements Serializable{

    public String def_mobile;//	flv
    public String def_pc;//	flv
    public FlvBean flv;//	Object
    public HlsBean hls;//	Object
    public String name;//	tx
    public int v;//	1

    public static class FlvBean {
        public SubFlvBean _0;	//Object
        public int main_mobile;//	0
        public int main_pc;//	0

        public static class SubFlvBean{
            public String name;//	超清
            public String src;//	http://flv3.quanmin.tv/live/3766_8263112.flv
        }

    }
    public static class HlsBean {
        public SubFlvBean _0;	//Object
        public int main_mobile;//	0
        public int main_pc;//	0

        public static class SubFlvBean{
            public String name;//	超清
            public String src;//	http://flv3.quanmin.tv/live/3766_8263112.flv
        }
    }




}
