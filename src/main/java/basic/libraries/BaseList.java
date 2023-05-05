package basic.libraries;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseList implements ListInterface {
    @Resource
    protected EntityManager entityManager;

    protected Integer page = 1;
    protected Integer pageSize = 20;
    protected String keyword = "";

    protected String select = "";

    protected String startDate = "";
    protected String endDate = "";
    protected String condition;
    protected Integer landlordId = 0;

    protected boolean distinct = false;

    protected String orderBy;
    protected String groupBy = "";

    protected AbstractQuery<?> query;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

}
