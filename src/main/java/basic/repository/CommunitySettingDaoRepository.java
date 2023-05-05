package basic.repository;

import basic.model.CommunitySettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunitySettingDaoRepository extends JpaRepository<CommunitySettingEntity, Long> {

    @Query("select a from CommunitySettingEntity a where a.communityId = ?1")
    CommunitySettingEntity findOneByCommunityId(Integer communityId);

    @Query("select a from CommunitySettingEntity a where a.communitySettingId = ?1")
    CommunitySettingEntity findOneByCommunitySettingId(Integer communitySettingId);
}