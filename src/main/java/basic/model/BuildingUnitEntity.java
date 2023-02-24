package basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_building_unit", schema = "sych")
public class BuildingUnitEntity {
    private Long buildingUnitId;

    @Id
    @Column(name = "building_unit_id")
    public long getBuildingUnitId() {
        return buildingUnitId;
    }

    public void setBuildingUnitId(long buildingUnitId) {
        this.buildingUnitId = buildingUnitId;
    }
}
