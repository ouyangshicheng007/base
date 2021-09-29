package com.yimi.oysc.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yimi.oysc.security.LoginUser;
import com.yimi.oysc.entity.CarInquiryEntity;
import com.yimi.oysc.service.ICarInquiryService;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 询价单保司询价结果表 前端控制器
 * </p>
 *
 * @author Davy
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/test")
public class CarInquiryController {

    @Autowired
    private ICarInquiryService carInquiryService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/search")
    public List<CarInquiryEntity> search() {

        return carInquiryService.list();
    }

    @GetMapping("/page/list")
    public Page<CarInquiryEntity> pageList(@RequestParam Long current,@RequestParam Long size) {
        Page<CarInquiryEntity> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);

        return carInquiryService.page(page);
    }

    @GetMapping("/hello")
    public String sayHi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser) authentication.getPrincipal();
        System.out.println(user);
        System.out.println(user);


        return "你好";
    }

    @GetMapping("/foo")
    public LoginUser foo(Principal principal) {
        Authentication authentication = (Authentication) principal;
        LoginUser user = (LoginUser) authentication.getPrincipal();

        return user;
    }

    @GetMapping("/test1")
    public String test1() {
        RBucket<String> oysc = redissonClient.getBucket("oysc");
        String s = oysc.get();
        if (s == null) {
            oysc.set("success", 2, TimeUnit.MINUTES);
        }
        return s;
    }


    @GetMapping("/test2")
    public String test2() {
        RBucket<String> oysc = redissonClient.getBucket("oysc");

        RLock lock = redissonClient.getLock("test_lock");
        boolean b = lock.tryLock();
        if (b) {
            try {
                lock.lock();
                System.out.println("加锁成功了·············");
            } finally {
                lock.unlock();
            }

        }


        return oysc.get();
    }

    @GetMapping("/test3")
    public String test3(HttpServletRequest request) {
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        System.out.println(request.getRequestURI());
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String head = names.nextElement();
            System.out.printf("%s : %s%n", head, request.getHeader(head));
        }


        return "success";
    }

}
