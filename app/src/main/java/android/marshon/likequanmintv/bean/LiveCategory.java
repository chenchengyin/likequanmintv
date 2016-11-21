package android.marshon.likequanmintv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by It.Marshon on 2016/11/21 0021 17:48
 */

@Entity
public class LiveCategory {

    @Id
    public String cid;

    public String name;

    @Generated(hash = 1689904058)
    public LiveCategory(String cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    @Generated(hash = 547755947)
    public LiveCategory() {
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
