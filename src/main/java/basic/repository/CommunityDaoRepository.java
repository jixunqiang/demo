package basic.repository;

import basic.model.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityDaoRepository extends JpaRepository<CommunityEntity, Long> {

    @Query("select a from CommunityEntity a where a.community_id = ?1")
    CommunityEntity findOneByCommunityId(Long communityId);

    @Query("select a from CommunityEntity a where a.community_name like '%?1%'")
    CommunityEntity findOneByCommunityName(Long communityName);
}
