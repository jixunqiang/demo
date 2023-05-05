package basic.controller;

import basic.libraries.BuildingList;
import basic.listener.BuildingEntityListener;
import basic.model.BuildingEntity;
import basic.model.CommunityEntity;
import basic.repository.BuildingDaoRepository;
import basic.services.BuildingService;
import basic.services.ResultResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.cj.util.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/building/")
public class BuildingController extends BaseController {
    @Resource
    private BuildingDaoRepository buildingDaoRepository;

    @Resource
    private BuildingService buildingService;

    @Resource
    private BuildingList buildingList;

    @RequestMapping("/list")
    public ResultResponse list(@RequestParam Long communityId, @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "20") Integer pageSize,
                               @RequestParam(defaultValue = "1") Integer page ) throws JsonProcessingException {
        buildingList.setKeyword(keyword);
        buildingList.setPage(page);
        buildingList.setCommunityId(communityId);
        buildingList.setPageSize(pageSize);
        buildingList.setOrderBy("create_time desc");
        List<BuildingEntity> buildingEntityList = buildingList.get();
        List<Object> list = new ArrayList<Object>();
        for (BuildingEntity entity : buildingEntityList) {
            list.add(buildingService.findOneByBuildingId(entity.getBuilding_id()));
        }

        Integer count = buildingList.count();
        JSONObject data = new JSONObject();
        data.put("list", list);
        data.put("count", count);
        data.put("page", page);
        data.put("pageSize", pageSize);
        data.put("pageCount", Math.ceil((double) count / pageSize));
        return ResultResponse.success(data);
    }

    @GetMapping("/info")
    public ResultResponse info(@RequestParam Long buildingId) throws JsonProcessingException {
        System.out.println("获取楼栋信息" + buildingId);
        Object communityEntity = buildingService.findOneByBuildingId(buildingId);

        JSONObject data = new JSONObject();
        data.put("info", communityEntity);
        return ResultResponse.success(data);
    }

    @PostMapping("/create")
    public ResultResponse create(@RequestBody BuildingEntity buildingEntity) throws JsonProcessingException {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        buildingEntity.setCreate_time(ft.format(date));
        buildingEntity.setUpdate_time(ft.format(date));
        BuildingEntity entity = buildingDaoRepository.save(buildingEntity);
        buildingService.updateBuildingRedis(entity);

        JSONObject data = new JSONObject();
        data.put("info", entity);
        return ResultResponse.success(data);
    }


    @PostMapping("/update")
    public ResultResponse update(@RequestBody BuildingEntity buildingEntity) throws JsonProcessingException {
        System.out.println("入参" + buildingEntity);
        Long buildingId = (buildingEntity.getCommunity_id());
        if (StringUtils.isNullOrEmpty(String.valueOf((buildingId)))) {
            return ResultResponse.fail("参数有误");
        }
        if (StringUtils.isNullOrEmpty(String.valueOf(buildingEntity.getBuilding_name()))) {
            return ResultResponse.fail("楼栋名称不能为空");
        }
        String[] Field = {"building_name", "building_no", "update_time"};
        BuildingEntity buildingData = buildingDaoRepository.findOneByBuildingId(buildingId);
        BeanUtils.copyProperties(buildingEntity, buildingData, Field);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        buildingData.setUpdate_time(ft.format(date));
        BuildingEntity repository = buildingDaoRepository.save(buildingData);
        buildingService.updateBuildingRedis(repository);

        JSONObject data = new JSONObject();
        data.put("info", repository);
        return ResultResponse.success(data);
    }


    @PostMapping("/import")
    public ResultResponse importExcel(@RequestParam String file) throws IOException {
        if (!buildingService.importExcel(new File(file))) {
            return ResultResponse.fail("导入失败");
        }
        return ResultResponse.success();
    }
}
