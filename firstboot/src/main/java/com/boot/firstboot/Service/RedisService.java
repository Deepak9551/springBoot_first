package com.boot.firstboot.Service;

import com.boot.firstboot.entity.WeatherEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    public <T> T get(String key, Class<T> valueType) {
        try {
            String json = (String) redisTemplate.opsForValue().get(key);
            log.info("Retrieved from Redis for key: " + key + " is: " + json);
            if (json != null) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(json, valueType);
            }
        } catch (Exception e) {
            log.error("Error message: " + e);
        }
        return null;
    }

    public void set(String key, Object value, Long ttl) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
            log.info("Data set in Redis for key: " + key + " with value: " + jsonValue);
        } catch (Exception e) {
            log.error("Error message: " + e);
        }
    }


}
