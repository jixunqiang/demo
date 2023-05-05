package basic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tbl_building_unit", schema = "sych")
public class BuildingUnitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "building_unit_id")
    private int buildingUnitId;
    @Basic
    @Column(name = "landlord_id")
    private int landlordId;
    @Basic
    @Column(name = "community_id")
    private int communityId;
    @Basic
    @Column(name = "building_id")
    private int buildingId;
    @Basic
    @Column(name = "building_unit_name")
    private String buildingUnitName;
    @Basic
    @Column(name = "delete_flag")
    private Byte deleteFlag;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Basic
    @Column(name = "building_no")
    private Integer buildingNo;
    @Basic
    @Column(name = "building_unit_no")
    private Integer buildingUnitNo;

    public int getBuildingUnitId() {
        return buildingUnitId;
    }

    public void setBuildingUnitId(int buildingUnitId) {
        this.buildingUnitId = buildingUnitId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingUnitName() {
        return buildingUnitName;
    }

    public void setBuildingUnitName(String buildingUnitName) {
        this.buildingUnitName = buildingUnitName;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getBuildingUnitNo() {
        return buildingUnitNo;
    }

    public void setBuildingUnitNo(Integer buildingUnitNo) {
        this.buildingUnitNo = buildingUnitNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingUnitEntity that = (BuildingUnitEntity) o;
        return buildingUnitId == that.buildingUnitId && landlordId == that.landlordId && communityId == that.communityId && buildingId == that.buildingId && Objects.equals(buildingUnitName, that.buildingUnitName) && Objects.equals(deleteFlag, that.deleteFlag) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(buildingNo, that.buildingNo) && Objects.equals(buildingUnitNo, that.buildingUnitNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingUnitId, landlordId, communityId, buildingId, buildingUnitName, deleteFlag, createTime, updateTime, buildingNo, buildingUnitNo);
    }
}
