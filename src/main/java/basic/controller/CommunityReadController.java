package basic.controller;

import basic.model.CommunityEntity;
import basic.services.ResultResponse;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/community-read/")
public class CommunityReadController extends BaseController{

    @RequestMapping("/hello")
    public String index() {
        System.out.println("Hello World");
        return "Hello World";
    }

    @GetMapping("/community/info")
    public ResultResponse remoteCallV1() throws JSONException {
        String url = "https://www.baidu.com/";
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(url, String.class);
        TypeReference<ResultResponse<CommunityEntity>> pojoType =
                new TypeReference<ResultResponse<CommunityEntity>>(){};

        //JSONObject jsonObject = new JSONObject();
        //jsonObject.
        //ResultResponse<CommunityEntity> result = JsonUtils.jsonToPojo(responseEntity.getBody(),pojoType);

        JSONObject data = new JSONObject();
        data.put("uaa-data", responseEntity.getBody());
        //Map<String, Object> map = new HashMap<>(3);
        //map.put("uaa-data", result);
        return ResultResponse.success(data);

    }
}
