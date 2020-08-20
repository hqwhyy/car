package com.example.car.dto;

public class QueryDTO {
    private int offset;//分页的起始位置，不是第几页
    private int limit;//分页大小
    private String search;// 可选：模糊查询条件
    private String order;//升序或降序
    private String sort;//可选：排序字段名 menuId

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
