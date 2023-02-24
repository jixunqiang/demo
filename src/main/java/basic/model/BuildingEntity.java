package basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_building", schema = "sych")
public class BuildingEntity {
    private Long buildingId;

    @Id
    @Column(name = "building_id")
    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }
}
