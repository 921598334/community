package com.example.community.provider;


import com.alibaba.fastjson.JSON;
import com.example.community.dto.AccessDTO;

import com.example.community.model.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GitHubProvider {

    //通过github第一次返回的code去申请token(post请求)
    public String getAccessToken(AccessDTO accessDTO){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessDTO) );
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute())
        {
            String tmp = response.body().string().split("&")[0];

            System.out.println(tmp);

            return tmp;

        }catch (Exception e){
            return null;
        }

    }



    //根据token得到用户信息(GET请求)
    //        https://api.github.com/user?access_token=53877cec3fdaeee7374bd93fb9623c2b0ba3032c
    public GithubUser getUser(String accessToken){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
             String userJson  = response.body().string();
             return JSON.parseObject(userJson,GithubUser.class);
        }catch (Exception e){

            return  null;
        }


    }

}
