package cn.flydrm.fm.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/13.
 */
public class Blog implements Serializable{
    private Long id;
    private String title;
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
