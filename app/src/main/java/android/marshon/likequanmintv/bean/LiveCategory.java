package android.marshon.likequanmintv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import android.marshon.likequanmintv.db.DaoSession;
import android.marshon.likequanmintv.db.PlayBeanDao;
import android.marshon.likequanmintv.db.LiveCategoryDao;

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
    private Long id;
    private String name;
    private int is_default;
    private String slug;
    private int type;
    private int screen;

    @ToMany(referencedJoinProperty = "livecategory_id")
    public List<PlayBean> list;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2031056786)
    private transient LiveCategoryDao myDao;

    @Generated(hash = 1515830500)
    public LiveCategory(Long id, String name, int is_default, String slug, int type,
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1392654100)
    public List<PlayBean> getList() {
        if (list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayBeanDao targetDao = daoSession.getPlayBeanDao();
            List<PlayBean> listNew = targetDao._queryLiveCategory_List(id);
            synchronized (this) {
                if (list == null) {
                    list = listNew;
                }
            }
        }
        return list;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 589833612)
    public synchronized void resetList() {
        list = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1052888058)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLiveCategoryDao() : null;
    }

}
