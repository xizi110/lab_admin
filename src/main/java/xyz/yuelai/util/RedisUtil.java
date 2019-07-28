package xyz.yuelai.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 李泽众
 * @date 2019/7/28-12:23
 */

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

   public void set(String key, String value){
       redisTemplate.opsForValue().set(key, value);
   }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    public Set<String> keys(){
        return redisTemplate.keys("");
    }

    public Set<String> values(){
        Set<String> keys = keys();
        Iterator<String> iterator = keys.iterator();
        Set<String> values = new HashSet<>();
        while (iterator.hasNext()){
            String value = iterator.next();
            values.add(value);
        }
        return values;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }


}