package com.sunstar.controller;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import redis.clients.jedis.Jedis;


@Service("redisTemplateUtil")
public class RedisTemplateUtil {

	@Resource  
    private RedisTemplate<String, Object> redisTemplate;  

	
    public void set(String key, Object value) {  
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();  
        valueOperations.set(key, value);  
    }  

    public Object get(String key) {  
        return redisTemplate.opsForValue().get(key);  
    }  

    
    public void setList(String key, List<?> value) {  
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();  
        listOperations.leftPush(key, value);                 
    }  

    public Object getPopList(String key) {  
        return redisTemplate.opsForList().leftPop(key);   //leftPop：出栈     出栈就把原数据删除了，所以第二次就没得了
    }  
    
    //LRANGE key start stop    客户端命令获取列表指定范围内的元素    需重写方法  就可以获取了    
    public Object getRangeList(String key,long start,long stop){
    	return redisTemplate.opsForList().range(key, start, stop);
    }
    //LLEN key  获取列表长度
    public long getListLength(String key){
    	return redisTemplate.opsForList().size(key);
    }
    
    

    public void setSet(String key, Set<?> value) {  
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();  
        setOperations.add(key, value);  
    }  

    public Object getSet(String key) {  
        return redisTemplate.opsForSet().members(key);  
    }  

    public void setHash(String key, Map<String, ?> value) {  
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();  
        hashOperations.putAll(key, value);  
    }  

    public Object getHash(String key) {  
        return redisTemplate.opsForHash().entries(key);  
    }  

    public void delete(String key) {  
        redisTemplate.delete(key);  
    }  
    
    
    
    
    /** 
     * 给一个指定的 key值附加过期时间 
     * @param key 
     * @param time 
     * @param type 
     * @return 
     */  
    public boolean expire(String key,long time,TimeUnit type){  
        return redisTemplate.boundValueOps(key).expire(time, type);  
    }
    
 
    
}
