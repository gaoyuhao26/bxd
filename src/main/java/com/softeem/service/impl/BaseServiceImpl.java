package com.softeem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 高玉好
 * @ClassName BaseServiceImpl
 * @since 2021/2/26 1:36
 */
public class BaseServiceImpl {
    @Autowired
    protected RedisTemplate redisTemplate;
}
