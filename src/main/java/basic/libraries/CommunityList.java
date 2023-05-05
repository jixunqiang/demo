package basic.libraries;

import basic.model.CommunityEntity;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityList extends BaseList{

    protected CriteriaBuilder builder;

    private CriteriaBuilder getCriteriaBuilder() {
        if (this.builder != null) {
            System.out.println("默认builder");
            return this.builder;
        }
        this.builder = entityManager.getCriteriaBuilder();
        return this.builder;
    }

    public CriteriaQuery<CommunityEntity> getQuery() {
        //CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommunityEntity> criteria = this.getCriteriaBuilder().createQuery( CommunityEntity.class );
        Root<CommunityEntity> root = criteria.from( CommunityEntity.class );

        List<Predicate> predicatesList = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(this.getKeyword())) {
            System.out.println("关键字查询");
            predicatesList.add(this.getCriteriaBuilder().equal(root.get("community_name"), this.getKeyword()));
        }

        //字段查询
        /*if (!StringUtils.isNullOrEmpty(this.getSelect())) {
            String[] selectList = this.getSelect().split(",");
            List<Selection<?>> selections = new ArrayList<>();
            for (String s : selectList) {
                selections.add(root.get(s.trim()));
            }
            criteria.select(this.getCriteriaBuilder().construct(CommunityEntity.class, (Selection<?>) selections));
        } else {*/
            criteria.select(root);
        //}
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
    public List<CommunityEntity> get() {
        return entityManager.createQuery(this.getQuery()).setMaxResults(this.getPageSize())
                .setFirstResult((this.getPage() - 1) * this.getPageSize()).getResultList();
    }

    public Integer count() {
        CriteriaQuery<Long> criteriaQuery = this.getCriteriaBuilder().createQuery(Long.class);
        Root<CommunityEntity> root = criteriaQuery.from(CommunityEntity.class);
        criteriaQuery.select(this.getCriteriaBuilder().count(root));

        Long result = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Math.toIntExact(result);
    }
}
