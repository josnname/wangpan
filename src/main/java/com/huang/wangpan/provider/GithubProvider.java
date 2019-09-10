package com.huang.wangpan.provider;

import com.alibaba.fastjson.JSON;
import com.huang.wangpan.dto.AccessTokenDTO;
import com.huang.wangpan.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO aTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        String json =JSON.toJSONString(aTokenDTO);
        System.out.println(json);

        RequestBody body2 = RequestBody.create(json,mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token?code="+aTokenDTO.getCode()+"&state="+aTokenDTO.getState()
               +"&client_id="+aTokenDTO.getClinet_id()+"&client_secret="+aTokenDTO.getClient_secret()+"&redirect_uri="+aTokenDTO.getRedirect_uri())
                .build();
        System.out.println(request.toString());
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            System.out.println(string);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try (Response reponse = client.newCall(request).execute()) {
            String string = reponse.body().string();
            GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
