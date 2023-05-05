package basic.controller;

import basic.model.CommunityEntity;
import basic.model.CommunitySettingEntity;
import basic.repository.CommunitySettingDaoRepository;
import basic.services.RedisServiceTmpl;
import basic.services.ResultResponse;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/community-setting/")
public class CommunitySettingController extends BaseController {
    final String communitySettingKey = "community_setting_";

    @Resource
    private RedisServiceTmpl redisServiceTmpl;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private CommunitySettingDaoRepository communitySettingDaoRepository;

    @GetMapping("/findBycommunityId")
    public ResultResponse communitySetting(@RequestParam Integer communityId) {
        CommunitySettingEntity communitySettingEntity =
                communitySettingDaoRepository.findOneByCommunityId(communityId);

        JSONObject data = new JSONObject();
        data.put("info", communitySettingEntity);
        return ResultResponse.success(data);
    }

    // This code block updates the community setting entity and updates the corresponding Redis cache
    @PostMapping("/update")
    public ResultResponse updateCommunitySetting(HttpServletRequest request, @RequestBody CommunitySettingEntity communitySettingEntity) throws JsonProcessingException {
        communitySettingDaoRepository.save(communitySettingEntity);
        redisServiceTmpl.set(communitySettingKey + communitySettingEntity.getCommunitySettingId(),
                objectMapper.writeValueAsString(communitySettingEntity));
        return ResultResponse.success();
    }

    // This code block retrieves the community setting entity from Redis cache if available, otherwise retrieves it from MySQL database
    @GetMapping("/get")
    public ResultResponse getCommunitySetting(@RequestParam Integer communitySettingId) throws JsonProcessingException {
        String communitySettingStr = redisServiceTmpl.get(communitySettingKey + communitySettingId);
        JSONObject data = new JSONObject();
        if (communitySettingStr == null) {
            CommunitySettingEntity communitySettingEntity =
                    communitySettingDaoRepository.findOneByCommunitySettingId(communitySettingId);
            redisServiceTmpl.set(communitySettingKey + communitySettingId,
                    objectMapper.writeValueAsString(communitySettingEntity));
            data.put("info", communitySettingEntity);
        } else {
            Object communitySettingEntity = JSONObject.parseObject(communitySettingStr);
            data.put("info", communitySettingEntity);
        }

        return ResultResponse.success(data);
    }

    // This code block deletes the community setting entity from MySQL database and removes the corresponding Redis cache
    @DeleteMapping("/delete")
    public ResultResponse deleteCommunitySetting(@RequestParam Integer communitySettingId) {
        CommunitySettingEntity communitySettingEntry =
                communitySettingDaoRepository.findOneByCommunitySettingId(communitySettingId);
        communitySettingDaoRepository.delete(communitySettingEntry);
        redisServiceTmpl.remove(communitySettingKey + communitySettingId);
        return ResultResponse.success();
    }
}
