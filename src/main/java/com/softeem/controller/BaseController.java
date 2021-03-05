package com.softeem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 */
public class BaseController {
    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected HttpServletRequest request;
}
