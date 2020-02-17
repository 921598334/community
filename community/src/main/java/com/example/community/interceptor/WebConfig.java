//package com.example.community.interceptor;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    LoginInterceptor loginInterceptor;
//
//    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        String[] excludes = new String[]{"/","/error/**","/question/**","/login/**","/static/**","/index/**","/css/**","/fonts/**","/images/**","/js/**","/Wopop_files/**","/callback/**"};
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
//
//    }
//
//}
