package com.xiaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author long.luo
 * @date 2018/8/6 14:48
 */
@Controller
public class UserController {


    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "info";
    }

    @GetMapping("/admin/info")
    @ResponseBody
    public String admin() {
        return "info";
    }

    @GetMapping("/customer/info")
    @ResponseBody
    public String customer() {
        return "info";
    }

    @GetMapping("/me")
    @ResponseBody
    public String me() {
        return "me";
    }
}
