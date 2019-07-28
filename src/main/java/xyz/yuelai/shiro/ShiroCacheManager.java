package xyz.yuelai.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * @author 李泽众
 * @date 2019/7/28-12:32
 */

@Component
public class ShiroCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return (Cache<K, V>) new ShiroCache<K, V>();
    }
}
