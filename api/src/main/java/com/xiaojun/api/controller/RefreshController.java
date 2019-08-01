package com.xiaojun.api.controller;

import com.xiaojun.api.service.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷新接口
 *
 * @author long.luo
 * @date 2018/12/13 15:22
 */
@RestController
public class RefreshController {

    @Autowired
    RefreshRouteService refreshRouteService;

    @GetMapping("/permit/route/refresh")
    public String refresh() {
        refreshRouteService.routeRefresh();
        return "refresh success";
    }
}
