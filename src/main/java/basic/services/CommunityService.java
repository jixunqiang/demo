package basic.services;

import basic.model.CommunityEntity;
import basic.repository.CommunityDaoRepository;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommunityService {
    final String community_key = "community:key:";

    @Resource
    private RedisServiceTmpl redisServiceTmpl;

    @Resource
    private CommunityDaoRepository communityDaoRepository;

    @Resource
    private ObjectMapper objectMapper;

    public Object findOneByCommunityId(Long communityId) throws JsonProcessingException {
        String rdsKey = community_key + communityId;
        System.out.println("redis key " + rdsKey);
        String community = redisServiceTmpl.get(rdsKey);
        if (StringUtils.isNullOrEmpty(community)) {
            System.out.println("数据库 获取");
            CommunityEntity communityEntity = communityDaoRepository.findOneByCommunityId(communityId);
            String communityEntityStr = objectMapper.writeValueAsString(communityEntity);
            redisServiceTmpl.set(rdsKey, communityEntityStr);
            return communityEntity;
        } else {
            System.out.println("redis 获取");
            return JSONObject.parseObject(redisServiceTmpl.get(rdsKey));
        }
    }

    /**
     * 保存或更新Redis小区信息
     * @return boolean
     */
    public boolean updateCommunityRedis(CommunityEntity communityEntity) throws JsonProcessingException {
        String rdsKey = community_key + communityEntity.getCommunity_id();
        if (!StringUtils.isNullOrEmpty(redisServiceTmpl.get(rdsKey))) {
            redisServiceTmpl.remove(rdsKey);
        }
        return redisServiceTmpl.set(rdsKey, objectMapper.writeValueAsString(communityEntity));
    }
}
