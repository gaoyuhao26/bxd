package com.softeem.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis工具类
 *
 * @author Uesugi
 */
public class RedisTemplateUtil {

    private RedisTemplate redisTemplate;

    public RedisTemplateUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplateUtil() {

    }

    //将多种类型的值存入缓存
    public void set(String key, Object value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    //获取指定的key值
    public  Object get(String key) {
            return redisTemplate.opsForValue().get(key);
    }

    /**
     * 将list存入缓存
     *
     * @param key
     * @param value
     */
    public void setList(String key, List value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    //获取指定key的list值
    public Object getList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    //将set存入缓存
    public void setSet(String key, Set value) {
        redisTemplate.opsForSet().add(key, value);
    }

    //获取set
    public Object getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    //将map存入缓存
    public void setHash(String key, Map value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    //获取map
    public Object getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    //删除某个key
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void flushAll(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);}

}