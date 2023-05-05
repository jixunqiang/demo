package basic.libraries;

import basic.model.BuildingEntity;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingList extends BaseList{
    protected Long communityId = 1L;

    protected CriteriaBuilder builder;

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    private CriteriaBuilder getCriteriaBuilder() {
        if (this.builder != null) {
            System.out.println("默认builder");
            return this.builder;
        }
        this.builder = entityManager.getCriteriaBuilder();
        return this.builder;
    }

    public CriteriaQuery<BuildingEntity> getQuery() {
        System.out.println("building list 查询");
        CriteriaQuery<BuildingEntity> criteria = this.getCriteriaBuilder().createQuery( BuildingEntity.class );
        Root<BuildingEntity> root = criteria.from( BuildingEntity.class );

        List<Predicate> predicatesList = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(this.getKeyword())) {
            System.out.println("关键字查询");
            predicatesList.add(this.getCriteriaBuilder().equal(root.get("building_name"), this.getKeyword()));
        }

        if (this.getCommunityId() != 1L && this.getCommunityId() > 0) {
            System.out.println("小区筛选");
            predicatesList.add(this.getCriteriaBuilder().equal(root.get("community_id"), this.getCommunityId()));
        }
        criteria.select(root);
        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);

        if (this.getDistinct()) {
            criteria.distinct(true);
        }

        criteria.where(finalPredicates);
        if (!StringUtils.isNullOrEmpty(this.getOrderBy())) {
            System.out.println("排序");
            String[] orderList = this.getOrderBy().split(",");
            for (String s : orderList) {
                String[] order = s.split(" ");
                if (order[1] == null || order[1].equals("asc")) {
                    criteria.orderBy(this.getCriteriaBuilder().asc(root.get(order[0])));
                } else {
                    criteria.orderBy(this.getCriteriaBuilder().desc(root.get(order[0])));
                }
            }
        }
        if (!StringUtils.isNullOrEmpty(this.getGroupBy())) {
            System.out.println("分组");
            String[] groupList = this.getGroupBy().split(",");
            for (int i = 0; i < groupList.length; i++) {
                criteria.groupBy(root.get(groupList[i]));
            }
        }
        return criteria;
    }

    @Override
    public List<BuildingEntity> get() {
        return entityManager.createQuery(this.getQuery()).setMaxResults(this.getPageSize())
                .setFirstResult((this.getPage() - 1) * this.getPageSize()).getResultList();
    }

    public Integer count() {
        CriteriaQuery<Long> criteriaQuery = this.getCriteriaBuilder().createQuery(Long.class);
        Root<BuildingEntity> root = criteriaQuery.from(BuildingEntity.class);
        criteriaQuery.select(this.getCriteriaBuilder().count(root));

        Long result = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Math.toIntExact(result);
    }
}
