package com.huang.wangpan.model;

import org.springframework.stereotype.Component;

@Component
public class UserFile {
    private String uid;
    private String virtualpath;
    private String realpath;
    private String filesize;
    private String mtime;
    private String iconSign;
    private int survival;

    public int getSurvival() {
        return survival;
    }

    public void setSurvival(int survival) {
        this.survival = survival;
    }

    public String getIconSign() {
        return iconSign;
    }

    public void setIconSign(String iconSign) {
        this.iconSign = iconSign;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVirtualpath() {
        return virtualpath;
    }

    public void setVirtualpath(String virtualpath) {
        this.virtualpath = virtualpath;
    }

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

}
