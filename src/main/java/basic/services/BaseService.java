package basic.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;

public class BaseService {
    @Resource
    protected RedisServiceTmpl redisServiceTmpl;
}
