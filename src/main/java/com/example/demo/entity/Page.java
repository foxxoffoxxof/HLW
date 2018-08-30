package com.example.demo.entity;

import java.util.List;
public class Page<T> {
    private int firstIndex;
    private int pageSize=5;
    private int pageCount=1;//当前页
    private int totalPageCount;//总页数
    private int totalDataCount;//总记录数
    private List<T> list;

    public int getFirstIndex() {
        return (this.getPageCount()-1)*this.getPageSize();
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getTotalPageCount() {
        return (this.getTotalDataCount()-1)/this.getPageSize()+1;
    }

    public int getTotalDataCount() {
        return totalDataCount;
    }
    public void setTotalDataCount(int totalDataCount) {
        this.totalDataCount = totalDataCount;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
}