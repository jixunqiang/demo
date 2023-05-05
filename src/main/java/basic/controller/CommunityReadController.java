package basic.controller;

import basic.libraries.CommunityList;
import basic.model.CommunityEntity;
import basic.repository.CommunityDaoRepository;
import basic.services.CommunityService;
import basic.services.RedisServiceTmpl;
import basic.services.ResultResponse;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/community-read/")
public class CommunityReadController extends BaseController {
    @Resource
    private CommunityDaoRepository communityDaoRepository;

    @Resource
    private CommunityList communityList;

    @Resource
    private RedisServiceTmpl redisServiceTmpl;

    @Resource
    private CommunityService communityService;

    @RequestMapping("/hello")
    public String index() {
        System.out.println("Hello World");
        return "Hello World";
    }

    @RequestMapping("/list")
    public ResultResponse list(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "20") Integer pageSize,
                               @RequestParam(defaultValue = "1") Integer page ) throws JsonProcessingException {
        communityList.setKeyword(keyword);
        communityList.setPage(page);
        communityList.setPageSize(pageSize);
        communityList.setOrderBy("create_time desc");
        List<CommunityEntity> CommunityEntityList = communityList.get();
        List<Object> list = new ArrayList<Object>();
        for (CommunityEntity entity : CommunityEntityList) {
            list.add(communityService.findOneByCommunityId(entity.getCommunity_id()));
        }

        Integer count = communityList.count();
        JSONObject data = new JSONObject();
        data.put("list", list);
        data.put("count", count);
        data.put("page", page);
        data.put("pageSize", pageSize);
        data.put("pageCount", Math.ceil((double) count / pageSize));
        return ResultResponse.success(data);
    }

    @RequestMapping("/info")
    public ResultResponse remoteCallV1() throws JSONException {
        System.out.println("community info");
        ResponseEntity<String> responseEntity = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/api/community-read/hello", String.class);
            System.out.println("community info");
            System.out.print(responseEntity.getStatusCode());
            System.out.println("body" + responseEntity.getBody());
            TypeReference<ResultResponse<CommunityEntity>> pojoType =
                    new TypeReference<ResultResponse<CommunityEntity>>() {
                    };

            //JSONObject jsonObject = new JSONObject();
            //jsonObject.
            //ResultResponse<CommunityEntity> result = JsonUtils.jsonToPojo(responseEntity.getBody(),pojoType);
            System.out.println("body" + responseEntity.getBody());
        } catch (Exception e) {
            System.out.println("11111");
            System.out.println(e.getMessage());
        }
        JSONObject data = new JSONObject();
        assert responseEntity != null;
        data.put("uaa-data", responseEntity.getBody());
        return ResultResponse.success(data);
    }

    @GetMapping("/communityInfo")
    public ResultResponse communityInfo(@RequestParam Long communityId) throws JsonProcessingException {
        System.out.println("获取小区信息" + communityId);
        Object communityEntity = communityService.findOneByCommunityId(communityId);

        JSONObject data = new JSONObject();
        data.put("info", communityEntity);
        return ResultResponse.success(data);
    }
    

    /*@PostMapping("/create")
    public ResultResponse create(@RequestBody Object params) {
        System.out.println("请求参数"+params);
        CommunityEntity communityEntity = new CommunityEntity();
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        communityEntity.setCreate_time(ft.format(date));
        communityEntity.setUpdate_time(ft.format(date));
        System.out.println("community_entity" + communityEntity);
        CommunityEntity entity = communityDaoRepository.save(communityEntity);

        JSONObject data = new JSONObject();
        data.put("info", communityEntity);
        return ResultResponse.success(data);
    }*/
    

    @PostMapping("/create")
    public ResultResponse create(@RequestBody CommunityEntity communityEntity) throws JsonProcessingException {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        communityEntity.setCreate_time(ft.format(date));
        communityEntity.setUpdate_time(ft.format(date));
        CommunityEntity entity = communityDaoRepository.save(communityEntity);
        communityService.updateCommunityRedis(entity);

        JSONObject data = new JSONObject();
        data.put("info", entity);
        return ResultResponse.success(data);
    }


    @PostMapping("/update")
    public ResultResponse update(@RequestBody CommunityEntity communityEntity) throws JsonProcessingException {
        System.out.println("入参" + communityEntity);
        Long communityId = (communityEntity.getCommunity_id());
        if (StringUtils.isNullOrEmpty(String.valueOf((communityId)))) {
            return ResultResponse.fail("参数有误");
        }
        if (StringUtils.isNullOrEmpty(String.valueOf(communityEntity.getCommunity_name()))) {
            return ResultResponse.fail("小区名称不能为空");
        }
        String[] Field = {"community_name", "community_addr", "update_time"};
        CommunityEntity community = communityDaoRepository.findOneByCommunityId(communityId);
        BeanUtils.copyProperties(communityEntity, community, Field);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        community.setUpdate_time(ft.format(date));
        CommunityEntity repository = communityDaoRepository.save(community);
        communityService.updateCommunityRedis(repository);

        JSONObject data = new JSONObject();
        data.put("info", repository);
        return ResultResponse.success(data);
    }
}
