package com.example.community.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        //当在浏览器上访问localhost：//upload/xxx时会自动去访问后面配置的路径
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/denghanbo/Documents/docment/邓瀚波2012217072/commnuity/community/src/main/resources/static/upload/"); //添加这一行

    }


    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] excludes = new String[]{"/","/upload/**","/login/**","/sign/**","/error/**","/question/**","/login/**","/static/**","/index/**","/css/**","/fonts/**","/images/**","/js/**","/Wopop_files/**","/callback/**","/ckeditor/**","/logout/**"};
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);

    }

}
