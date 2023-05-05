package basic.listener;

import basic.model.BuildingEntity;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.List;

public class BuildingEntityListener  extends AnalysisEventListener<BuildingEntity> {
    private List<BuildingEntity> buildingList;

    public BuildingEntityListener(List<BuildingEntity> buildingList) {
        this.buildingList = buildingList;
    }

    @Override
    public void invoke(BuildingEntity buildingEntity, AnalysisContext analysisContext) {
        buildingList.add(buildingEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // Do nothing
    }
}
