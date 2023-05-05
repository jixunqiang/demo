package basic.controller;

import basic.services.ResultResponse;
import basic.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    Integer landlordId;

    HttpServletRequest request;

   /* public BaseController(HttpServletRequest request) {
        this.request = request;
        this.landlordId = Integer.valueOf(request.getParameter("landlordId"));
        Object user = this.getUser();
        System.out.println("当前登录用户信息" + user);
        *//*if (!this.landlordId.equals(user)) {
            return ResultResponse.fail("参数有误");
        }*//*
        //return null;
    }*/


    public String getAuthorization() {
        String authorization = request.getHeader("Authorization");
        return authorization.substring(7);
    }

    protected JwtUtil jwtUtil;
    public Object getUser() {
        return jwtUtil.getUserFromToken(this.getAuthorization());
    }
}
