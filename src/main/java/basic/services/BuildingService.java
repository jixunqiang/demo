package basic.services;

import basic.listener.BuildingEntityListener;
import basic.model.BuildingEntity;
import basic.model.CommunityEntity;
import basic.repository.BuildingDaoRepository;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService extends BaseService {
    final String building_key = "building:key:";

    @Resource
    private BuildingDaoRepository buildingDaoRepository;

    @Resource
    private ObjectMapper objectMapper;

    public Object findOneByBuildingId(Long buildingId) throws JsonProcessingException {
        String rdsKey = building_key + buildingId;
        System.out.println("redis key " + rdsKey);
        String building = redisServiceTmpl.get(rdsKey);
        if (StringUtils.isNullOrEmpty(building)) {
            System.out.println("数据库 获取");
            BuildingEntity buildingEntity = buildingDaoRepository.findOneByBuildingId(buildingId);
            System.out.println("数据库 获取" + buildingEntity);
            String buildingEntityStr = objectMapper.writeValueAsString(buildingEntity);
            redisServiceTmpl.set(rdsKey, buildingEntityStr);
            return buildingEntity;
        } else {
            System.out.println("redis 获取");
            return JSONObject.parseObject(redisServiceTmpl.get(rdsKey));
        }
    }

    /**
     * 保存或更新Redis小区信息
     * @return boolean
     */
    public boolean updateBuildingRedis(BuildingEntity buildingEntity) throws JsonProcessingException {
        String rdsKey = building_key + buildingEntity.getBuilding_id();
        if (!StringUtils.isNullOrEmpty(redisServiceTmpl.get(rdsKey))) {
            redisServiceTmpl.remove(rdsKey);
        }
        return redisServiceTmpl.set(rdsKey, objectMapper.writeValueAsString(buildingEntity));
    }

    public boolean importExcel(File file) {
        // Create a new instance of the ExcelReader class
        List<BuildingEntity> buildingList;
        try (ExcelReader excelReader = EasyExcel.read(file).build()) {

            // Define a new instance of the BuildingEntity class to hold the data from the file
            buildingList = new ArrayList<>();

            // Define a new instance of the BuildingEntityListener class to handle the data from the file
            BuildingEntityListener listener = new BuildingEntityListener(buildingList);

            ReadSheet readSheet = EasyExcel.readSheet(0).build();

            // Use the excelReader to read the contents of the file and pass the listener to handle the data
            EasyExcel.read(file.getName(), BuildingEntity.class, listener).sheet().doRead();

            // Close the excelReader to release resources
            excelReader.finish();
        }

        // Save the data to the database
        buildingDaoRepository.saveAll(buildingList);

        // Return a success response
        return true;
    }
}
