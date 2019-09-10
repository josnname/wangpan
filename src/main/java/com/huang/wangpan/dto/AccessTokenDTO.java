package com.huang.wangpan.dto;

public class AccessTokenDTO {
    private String clinet_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public String getClinet_id() {
        return clinet_id;
    }

    public void setClinet_id(String clinet_id) {
        this.clinet_id = clinet_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

//    @Override
//    public String toString() {
//        return "AccessTokenDTO{" +
//                "clinet_id='" + clinet_id + '\'' +
//                ", client_secret='" + client_secret + '\'' +
//                ", code='" + code + '\'' +
//                ", redirect_uri='" + redirect_uri + '\'' +
//                ", state='" + state + '\'' +
//                '}';
//    }
}
