package xyz.yuelai.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.yuelai.util.RedisUtil;

import java.util.Collection;
import java.util.Set;

/**
 * @author 李泽众
 * @date 2019/7/28-12:33
 */
public class ShiroCache<K, V> implements Cache<String, String> {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String get(String s) throws CacheException {
        return redisUtil.get(s);
    }

    @Override
    public String put(String s, String o) throws CacheException {
        String val = redisUtil.get(s);
        redisUtil.set(s, o);
        return val;
    }

    @Override
    public String remove(String s) throws CacheException {
        String val = redisUtil.get(s);
        redisUtil.delete(s);
        return val;
    }

    @Override
    public void clear() throws CacheException {
    }

    @Override
    public int size() {
        return 20;
    }

    @Override
    public Set<String> keys() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return redisUtil.values();
    }
}
