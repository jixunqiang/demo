package basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "tbl_community", schema = "sych")
public class CommunityEntity extends ModelEntity{
    @Id
    @GeneratedValue(generator="uuidGenerator")
    @GenericGenerator(name="uuidGenerator",strategy="uuid")
    @Column(name = "community_id")
    private Long community_id;

    @Column(name = "landlord_id")
    private Integer landlord_id;

    @Column(name = "community_name")
    private String community_name;

    @Column(name = "community_desc")
    private String community_desc;

    @Column(name = "province_id")
    private String province_id;

    @Column(name = "city_id")
    private String city_id;

    @Column(name = "district_id")
    private String district_id;

    @Column(name = "community_addr")
    private String community_addr;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;

    @Column(name = "contact_phone")
    private String contact_phone;

    @Column(name = "icon")
    private String icon;

    @Column(name = "banner_images")
    private String banner_images;

    @Column(name = "community_area")
    private Double community_area;

    @Column(name = "building_area")
    private Double building_area;

    @Column(name = "public_place_area")
    private Double public_place_area;

    @Column(name = "green_area")
    private Double green_area;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "create_time")
    private String create_time;

    @Column(name = "update_time")
    private String update_time;

    public Long getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Long community_id) {
        this.community_id = community_id;
    }

    public Integer getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(Integer landlord_id) {
        this.landlord_id = landlord_id;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getCommunity_desc() {
        return community_desc;
    }

    public void setCommunity_desc(String community_desc) {
        this.community_desc = community_desc;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getCommunity_addr() {
        return community_addr;
    }

    public void setCommunity_addr(String community_addr) {
        this.community_addr = community_addr;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBanner_images() {
        return banner_images;
    }

    public void setBanner_images(String banner_images) {
        this.banner_images = banner_images;
    }

    public Double getCommunity_area() {
        return community_area;
    }

    public void setCommunity_area(Double community_area) {
        this.community_area = community_area;
    }

    public Double getBuilding_area() {
        return building_area;
    }

    public void setBuilding_area(Double building_area) {
        this.building_area = building_area;
    }

    public Double getPublic_place_area() {
        return public_place_area;
    }

    public void setPublic_place_area(Double public_place_area) {
        this.public_place_area = public_place_area;
    }

    public Double getGreen_area() {
        return green_area;
    }

    public void setGreen_area(Double green_area) {
        this.green_area = green_area;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

    public int hashCode() {
        return Objects.hash(community_id, landlord_id);
    }

    @Override
    public String toString() {
        return "CommunityEntity{" +
                "community_id=" + community_id +
                ", landlord_id=" + landlord_id +
                ", community_name='" + community_name + '\'' +
                ", community_desc='" + community_desc + '\'' +
                ", province_id='" + province_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", district_id='" + district_id + '\'' +
                ", community_addr='" + community_addr + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", contact_phone='" + contact_phone + '\'' +
                ", icon='" + icon + '\'' +
                ", banner_images='" + banner_images + '\'' +
                ", community_area=" + community_area +
                ", building_area=" + building_area +
                ", public_place_area=" + public_place_area +
                ", green_area=" + green_area +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
