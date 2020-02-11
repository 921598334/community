package com.example.community.interceptor;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//不加Service的话，Autowired无法识别
@Service
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    UserMapper userMapper;

    //post请求时会进行了时候有登陆的判断
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通过cookie得到在数据库中得到用户
        Cookie[] cookies = request.getCookies();

        if(cookies==null || cookies.length==0)
        {
            System.out.println("token不存在");
            System.out.println("被拦截");
            return false;
        }

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                //在数据库中看看有没有token

                User user = userMapper.findByToken(cookie.getValue()) ;

                if(user == null){

                    System.out.println("被拦截");
                    return false;
                }

                //把用户信息存储到seesion中，然后在前段判断这个session中有没有这个用户，如果没有就显示登陆
                request.getSession().setAttribute("user",user);
                System.out.println("在数据库中找到token");
                return  true;
            }

        }

        System.out.println("被拦截");
        return false;

        //return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
