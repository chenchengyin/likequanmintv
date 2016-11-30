package android.marshon.likequanmintv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/21 0021 17:48
 */

@Entity
public class LiveCategory {
    /**
     * id : 0
     * name : 精彩推荐
     * is_default : 0
     * slug :
     * type : 1
     * screen : 0
     */

    @Id
    private int id;
    private String name;
    private int is_default;
    private String slug;
    private int type;
    private int screen;

    @Transient
    public List<PlayBean> list;

    @Generated(hash = 206731314)
    public LiveCategory(int id, String name, int is_default, String slug, int type,
            int screen) {
        this.id = id;
        this.name = name;
        this.is_default = is_default;
        this.slug = slug;
        this.type = type;
        this.screen = screen;
    }
    @Generated(hash = 547755947)
    public LiveCategory() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIs_default() {
        return this.is_default;
    }
    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
    public String getSlug() {
        return this.slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getScreen() {
        return this.screen;
    }
    public void setScreen(int screen) {
        this.screen = screen;
    }
}
