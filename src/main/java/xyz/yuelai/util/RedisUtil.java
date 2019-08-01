package xyz.yuelai.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate redisTemplate;

   public void set(Object key, Object value){
       redisTemplate.opsForValue().set(key, value);
   }

    public void delete(Object key){
        redisTemplate.delete(key);
    }

    public Object get(Object key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean exists(Object key){
        return redisTemplate.hasKey(key);
    }

    public Set<Object> keys(){
        return redisTemplate.keys("");
    }

    public Set<Object> values(){
        Set<Object> keys = keys();
        Iterator<Object> iterator = keys.iterator();
        Set<Object> values = new HashSet<>();
        while (iterator.hasNext()){
            Object value = iterator.next();
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