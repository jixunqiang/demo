package basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tbl_community_setting", schema = "sych")
public class CommunitySettingEntity {
    @Column(name = "community_setting_id")
    private long communitySettingId;

    @Column(name = "landlord_id")
    private Integer landlordId;

    @Column(name = "community_id")
    private Integer communityId;

    @Id
    @Column(name = "community_setting_id")
    public long getCommunitySettingId() {
        return communitySettingId;
    }

    public void setCommunitySettingId(long communitySettingId) {
        this.communitySettingId = communitySettingId;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}
