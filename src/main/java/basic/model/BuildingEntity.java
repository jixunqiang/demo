package basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_building", schema = "sych")
public class BuildingEntity {
    @Id
    @GeneratedValue(generator="uuidGenerator")
    @GenericGenerator(name="uuidGenerator",strategy="uuid")
    @Column(name = "building_id")
    private Long building_id;

    @Column(name = "landlord_id")
    private long landlord_id;

    @Column(name = "community_id")
    private long community_id;

    @Column(name = "building_name")
    private String building_name;

    @Column(name = "total_floor")
    private long total_floor;

    @Column(name = "building_desc")
    private String building_desc;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "service_manager")
    private long service_manager;

    @Column(name = "building_orientation")
    private String building_orientation;

    @Column(name = "elevator_fee_total_area")
    private double elevator_fee_total_area;

    @Column(name = "enable_flag")
    private long enable_flag;

    @Column(name = "deleted_flag")
    private long deleted_flag;

    @Column(name = "create_time")
    private String create_time;

    @Column(name = "update_time")
    private String update_time;

    @Column(name = "special_use_type")
    private long special_use_type;

    @Column(name = "building_no")
    private long building_no;

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public long getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(long landlord_id) {
        this.landlord_id = landlord_id;
    }

    public long getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(long community_id) {
        this.community_id = community_id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public long getTotal_floor() {
        return total_floor;
    }

    public void setTotal_floor(long total_floor) {
        this.total_floor = total_floor;
    }

    public String getBuilding_desc() {
        return building_desc;
    }

    public void setBuilding_desc(String building_desc) {
        this.building_desc = building_desc;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getService_manager() {
        return service_manager;
    }

    public void setService_manager(long service_manager) {
        this.service_manager = service_manager;
    }

    public String getBuilding_orientation() {
        return building_orientation;
    }

    public void setBuilding_orientation(String building_orientation) {
        this.building_orientation = building_orientation;
    }

    public double getElevator_fee_total_area() {
        return elevator_fee_total_area;
    }

    public void setElevator_fee_total_area(double elevator_fee_total_area) {
        this.elevator_fee_total_area = elevator_fee_total_area;
    }

    public long getEnable_flag() {
        return enable_flag;
    }

    public void setEnable_flag(long enable_flag) {
        this.enable_flag = enable_flag;
    }

    public long getDeleted_flag() {
        return deleted_flag;
    }

    public void setDeleted_flag(long deleted_flag) {
        this.deleted_flag = deleted_flag;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public long getSpecial_use_type() {
        return special_use_type;
    }

    public void setSpecial_use_type(long special_use_type) {
        this.special_use_type = special_use_type;
    }

    public long getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(long building_no) {
        this.building_no = building_no;
    }

    @Override
    public String toString() {
        return "BuildingEntity{" +
                "building_id=" + building_id +
                ", landlord_id=" + landlord_id +
                ", community_id=" + community_id +
                ", building_name='" + building_name + '\'' +
                ", total_floor=" + total_floor +
                ", building_desc='" + building_desc + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", service_manager=" + service_manager +
                ", building_orientation='" + building_orientation + '\'' +
                ", elevator_fee_total_area=" + elevator_fee_total_area +
                ", enable_flag=" + enable_flag +
                ", deleted_flag=" + deleted_flag +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", special_use_type=" + special_use_type +
                ", building_no=" + building_no +
                '}';
    }
}
