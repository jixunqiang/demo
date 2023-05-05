package basic.repository;

import basic.model.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuildingDaoRepository extends JpaRepository<BuildingEntity, Long> {
    @Query("select a from BuildingEntity a where a.building_id = ?1")
    BuildingEntity findOneByBuildingId(Long buildingId);

    @Query("select a from BuildingEntity a where a.deleted_flag = 0 AND a.community_id = ?1 AND a.building_name like " +
            "'%?2%'")
    BuildingEntity findOneByBuildingName(Long communityId, Long communityName);

    @Query("select a from BuildingEntity a where a.deleted_flag = ?2 AND a.community_id = ?1")
    BuildingEntity findOneByBuildingName(Long communityId, Integer deletedFlag);
}
