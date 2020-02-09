package com.example.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


//程序发现异常时的处理，这里的异常需要用户自己用throw进行抛出
@ControllerAdvice
public class CostomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){

        HttpStatus status = getStatus(request);

        if(ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else{

            model.addAttribute("message","服务器冒烟了，要不然你稍后再试试！！！");
        }

        //跳转到error页面
        return new ModelAndView("error");
    }

    private  HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }



}
