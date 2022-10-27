package geektime.spring.springbucks.config;

import com.alibaba.fastjson.support.spring.*;
import org.springframework.cache.*;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean("customRedisTemplate")
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 使用GenericToStringSerializer来序列化和反序列化redis的key
        GenericToStringSerializer<String> stringGenericToStringSerializer = new GenericToStringSerializer<>(String.class);
        redisTemplate.setKeySerializer(stringGenericToStringSerializer);
        redisTemplate.setHashKeySerializer(stringGenericToStringSerializer);
        redisTemplate.setStringSerializer(stringGenericToStringSerializer);

        // 使用FastJsonRedisSerializer来序列化和反序列化redis的值
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        return redisTemplate;
    }

    /**
     * 选择redis作为默认缓存工具
     */
    @Bean("redisCacheManager")
    @Primary
    @DependsOn("customRedisTemplate")
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisTemplate.getConnectionFactory())
                // 设置缓存默认永不过期
                .cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig()
                                // 不缓存null(需要与unless = "#result == null"共同使用)
                                .disableCachingNullValues()
                                .serializeKeysWith(
                                        RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getStringSerializer()))
                                .serializeValuesWith(
                                        RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer())))
                // 配置同步修改或删除 put/evict
                .transactionAware()
                .build();
    }
}
