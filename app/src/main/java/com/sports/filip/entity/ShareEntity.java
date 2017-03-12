package com.sports.filip.entity;

/**
 * Author：huafang2016
 * Date: 2016/8/26 15:29
 * Email：15291967179@163.com
 */
public class ShareEntity
{

    private String title;

    private String content;

    private String targetUri;

    private String imgUri;

    public ShareEntity() {
    }
    

    public ShareEntity(String title, String content, String targetUri, String imgUri) {
        this.title = title;
        this.content = content;
        this.targetUri = targetUri;
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetUri() {
        return targetUri;
    }

    public void setTargetUri(String targetUri) {
        this.targetUri = targetUri;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
