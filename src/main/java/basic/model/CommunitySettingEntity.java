package basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tbl_community_settting", schema = "sych")
public class CommunitySettingEntity {
    private long communitySettingId;

    @Id
    @Column(name = "community_setting_id")
    public long getCommunitySettingId() {
        return communitySettingId;
    }

    public void setCommunitySettingId(long communitySettingId) {
        this.communitySettingId = communitySettingId;
    }
}
