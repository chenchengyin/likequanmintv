package android.marshon.likequanmintv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */
@Entity
public class ItemColumn implements Serializable{

    static final long serialVersionUID=536871008L;


    /**
     * first_letter : L
     * id : 1
     * image : http://image.quanmin.tv/4518e1f7c347c3e78fc5fd9bba6cb6b2png
     * name : 英雄联盟
     * priority : 1
     * prompt : 1
     * screen : 0
     * slug : lol
     * status : 0
     * thumb : http://image.quanmin.tv/ca3d8b85f3b19ef7171f4435ce03ebcapng
     */

    public String first_letter;
    @Id
    public Long id;
    public String image;
    public String name;
    public int priority;
    public int prompt;
    public int screen;
    public String slug;
    public int status;
    public String thumb;
    @Generated(hash = 1838605758)
    public ItemColumn(String first_letter, Long id, String image, String name,
            int priority, int prompt, int screen, String slug, int status,
            String thumb) {
        this.first_letter = first_letter;
        this.id = id;
        this.image = image;
        this.name = name;
        this.priority = priority;
        this.prompt = prompt;
        this.screen = screen;
        this.slug = slug;
        this.status = status;
        this.thumb = thumb;
    }
    @Generated(hash = 83991236)
    public ItemColumn() {
    }
    public String getFirst_letter() {
        return this.first_letter;
    }
    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getPrompt() {
        return this.prompt;
    }
    public void setPrompt(int prompt) {
        this.prompt = prompt;
    }
    public int getScreen() {
        return this.screen;
    }
    public void setScreen(int screen) {
        this.screen = screen;
    }
    public String getSlug() {
        return this.slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getThumb() {
        return this.thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
