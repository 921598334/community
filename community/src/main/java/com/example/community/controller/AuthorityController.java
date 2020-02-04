package com.example.community.controller;


import com.example.community.dto.AccessDTO;
import com.example.community.dto.GitHubUser;
import com.example.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class AuthorityController {

    @Autowired
    private GitHubProvider gitHubProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state, Model model) {


//        Client ID
//        71723b2931d149bfcd48
//        Client Secret
//        0681098767c10521c9be7d8db883451817328090
//
//        http://localhost:8080/callback



        AccessDTO accessDTO = new AccessDTO("71723b2931d149bfcd48","0681098767c10521c9be7d8db883451817328090",code,"http://localhost:8080/callback",state);
        GitHubUser gitHubUser = gitHubProvider.getUser(gitHubProvider.getAccessToken(accessDTO)) ;

        System.out.println(gitHubUser.getName());

        return "index";
    }
}
