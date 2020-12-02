package com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 判断是否存在key
    public boolean exists(String key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false);
    }

    // 删除对应的value
    public void remove(String key) {
        if (this.exists(key)) {
            redisTemplate.delete(key);
        }
    }

    // 批量删除key
    public void remove(String... keys) {
        for (String key : keys) {
            this.remove(key);
        }
    }

    // 批量删除正则key
    public void removePattern(String pattern) {
        Optional.ofNullable(redisTemplate.keys(pattern)).ifPresent((p) -> {
            if (p.size() > 0)
                redisTemplate.delete(p);
        });
    }

    // 指定key对应value加上数值
    public Long increment(String key, long num) {
        return redisTemplate.opsForValue().increment(key, num);
    }

    // 指定key对应value减去数值
    public Long decrement(String key, long num) {
        return redisTemplate.opsForValue().decrement(key, num);
    }

    // 字符串写入缓存并设置失效时间
    public void set(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireTime, timeUnit);
    }

    // 字符串写入缓存
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 字符串读取缓存
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 哈希写入缓存
    public void hmSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    // 哈希读取缓存
    public Object hmGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public void hmSet(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    // 哈希删除缓存
    public Object hmDel(String key, Object hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

}
