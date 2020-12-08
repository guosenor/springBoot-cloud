package com.guosen.serviceuser.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserController {

    @RequestMapping(value = "/user/register", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String Create(@RequestBody String userName) {
        return "{\"status\":\"welcome "+userName+"\"}";
    }
}
