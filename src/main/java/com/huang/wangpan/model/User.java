package com.huang.wangpan.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String token;
    private String name;
    private String accountid;
    private String gmtcreate;
    private String gmtmodified;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(String gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public String getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(String gmtmodified) {
        this.gmtmodified = gmtmodified;
    }
}
