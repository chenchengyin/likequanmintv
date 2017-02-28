package android.marshon.likequanmintv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by It.Marshon on 2016/11/22 0022 10:40
 */

@Entity
public class PlayBean implements Serializable{

    static final long serialVersionUID=536871008L;


    @Id
    public Long id;

    public Long livecategory_id;  //引用id

    public String recommend_image;
    public String announcement;
    public String title;
    public String create_at;
    public String intro;
    public String video;
    public int screen;
    public String push_ip;
    public String love_cover;
    public String category_id;
    public String video_quality;
    public String like;
    public String default_image;
    public String slug;
    public String weight;
    public String status;
    public String level;
    public String avatar;
    public String uid;
    public String play_at;
    public String view;
    public String category_slug;
    public String nick;
    public String beauty_cover;
    public String app_shuffling_image;
    public String start_time;
    public int follow;
    public String category_name;
    public String thumb;
    public String grade;
    public boolean hidden;
    public String icontext;
    @Generated(hash = 50883379)
    public PlayBean(Long id, Long livecategory_id, String recommend_image,
            String announcement, String title, String create_at, String intro,
            String video, int screen, String push_ip, String love_cover,
            String category_id, String video_quality, String like,
            String default_image, String slug, String weight, String status,
            String level, String avatar, String uid, String play_at, String view,
            String category_slug, String nick, String beauty_cover,
            String app_shuffling_image, String start_time, int follow,
            String category_name, String thumb, String grade, boolean hidden,
            String icontext) {
        this.id = id;
        this.livecategory_id = livecategory_id;
        this.recommend_image = recommend_image;
        this.announcement = announcement;
        this.title = title;
        this.create_at = create_at;
        this.intro = intro;
        this.video = video;
        this.screen = screen;
        this.push_ip = push_ip;
        this.love_cover = love_cover;
        this.category_id = category_id;
        this.video_quality = video_quality;
        this.like = like;
        this.default_image = default_image;
        this.slug = slug;
        this.weight = weight;
        this.status = status;
        this.level = level;
        this.avatar = avatar;
        this.uid = uid;
        this.play_at = play_at;
        this.view = view;
        this.category_slug = category_slug;
        this.nick = nick;
        this.beauty_cover = beauty_cover;
        this.app_shuffling_image = app_shuffling_image;
        this.start_time = start_time;
        this.follow = follow;
        this.category_name = category_name;
        this.thumb = thumb;
        this.grade = grade;
        this.hidden = hidden;
        this.icontext = icontext;
    }
    @Generated(hash = 1251142461)
    public PlayBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRecommend_image() {
        return this.recommend_image;
    }
    public void setRecommend_image(String recommend_image) {
        this.recommend_image = recommend_image;
    }
    public String getAnnouncement() {
        return this.announcement;
    }
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreate_at() {
        return this.create_at;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
    public String getIntro() {
        return this.intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getVideo() {
        return this.video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public int getScreen() {
        return this.screen;
    }
    public void setScreen(int screen) {
        this.screen = screen;
    }
    public String getPush_ip() {
        return this.push_ip;
    }
    public void setPush_ip(String push_ip) {
        this.push_ip = push_ip;
    }
    public String getLove_cover() {
        return this.love_cover;
    }
    public void setLove_cover(String love_cover) {
        this.love_cover = love_cover;
    }
    public String getCategory_id() {
        return this.category_id;
    }
    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
    public String getVideo_quality() {
        return this.video_quality;
    }
    public void setVideo_quality(String video_quality) {
        this.video_quality = video_quality;
    }
    public String getLike() {
        return this.like;
    }
    public void setLike(String like) {
        this.like = like;
    }
    public String getDefault_image() {
        return this.default_image;
    }
    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }
    public String getSlug() {
        return this.slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getLevel() {
        return this.level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getPlay_at() {
        return this.play_at;
    }
    public void setPlay_at(String play_at) {
        this.play_at = play_at;
    }
    public String getView() {
        return this.view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public String getCategory_slug() {
        return this.category_slug;
    }
    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }
    public String getNick() {
        return this.nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getBeauty_cover() {
        return this.beauty_cover;
    }
    public void setBeauty_cover(String beauty_cover) {
        this.beauty_cover = beauty_cover;
    }
    public String getApp_shuffling_image() {
        return this.app_shuffling_image;
    }
    public void setApp_shuffling_image(String app_shuffling_image) {
        this.app_shuffling_image = app_shuffling_image;
    }
    public String getStart_time() {
        return this.start_time;
    }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    public int getFollow() {
        return this.follow;
    }
    public void setFollow(int follow) {
        this.follow = follow;
    }
    public String getCategory_name() {
        return this.category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public String getThumb() {
        return this.thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getGrade() {
        return this.grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public boolean getHidden() {
        return this.hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public String getIcontext() {
        return this.icontext;
    }
    public void setIcontext(String icontext) {
        this.icontext = icontext;
    }


    @Override
    public String toString() {
        return "PlayBean{" +
                "id=" + id +
                ", recommend_image='" + recommend_image + '\'' +
                ", announcement='" + announcement + '\'' +
                ", title='" + title + '\'' +
                ", create_at='" + create_at + '\'' +
                ", intro='" + intro + '\'' +
                ", video='" + video + '\'' +
                ", screen=" + screen +
                ", push_ip='" + push_ip + '\'' +
                ", love_cover='" + love_cover + '\'' +
                ", category_id='" + category_id + '\'' +
                ", video_quality='" + video_quality + '\'' +
                ", like='" + like + '\'' +
                ", default_image='" + default_image + '\'' +
                ", slug='" + slug + '\'' +
                ", weight='" + weight + '\'' +
                ", status='" + status + '\'' +
                ", level='" + level + '\'' +
                ", avatar='" + avatar + '\'' +
                ", uid='" + uid + '\'' +
                ", play_at='" + play_at + '\'' +
                ", view='" + view + '\'' +
                ", category_slug='" + category_slug + '\'' +
                ", nick='" + nick + '\'' +
                ", beauty_cover='" + beauty_cover + '\'' +
                ", app_shuffling_image='" + app_shuffling_image + '\'' +
                ", start_time='" + start_time + '\'' +
                ", follow=" + follow +
                ", category_name='" + category_name + '\'' +
                ", thumb='" + thumb + '\'' +
                ", grade='" + grade + '\'' +
                ", hidden=" + hidden +
                ", icontext='" + icontext + '\'' +
                '}';
    }
    public Long getLivecategory_id() {
        return this.livecategory_id;
    }
    public void setLivecategory_id(Long livecategory_id) {
        this.livecategory_id = livecategory_id;
    }
}
