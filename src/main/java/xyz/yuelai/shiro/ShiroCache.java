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
public class ShiroCache<K, V> implements Cache<K, V> {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Object get(Object s) throws CacheException {
        return redisUtil.get(s);
    }

    @Override
    public Object put(Object s, Object o) throws CacheException {
        Object val = redisUtil.get(s);
        redisUtil.set(s, o);
        return val;
    }

    @Override
    public Object remove(Object s) throws CacheException {
        Object val = redisUtil.get(s);
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
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
