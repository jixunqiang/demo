package basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_community", schema = "sych")
public class CommunityEntity {
    private long communityId;
    private String landlordId;
    private String communityName;
    private String communityIntro;
    private String provinceId;
    private String cityId;
    private String districtId;
    private String communityAddress;
    private String lng;
    private String lat;
    private String contactPhone;
    private String communityIcon;
    private String communityImages;
    private Double communityArea;
    private Double buildingArea;
    private Double publicPlaceArea;
    private Double greenArea;
    private Integer parkingNumber;
    private Double parkingArea;
    private String leader;
    private String leaderPhone;
    private String startDate;
    private String endDate;
    private String onLineTime;
    private Integer createAt;
    private Integer updateAt;

    @Id
    @Column(name = "community_id")
    public long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(long communityId) {
        this.communityId = communityId;
    }
}
