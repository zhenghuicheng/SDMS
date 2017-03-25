package sdms.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017/1/4.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1238749498860238250L;
    public static int FIRST_PAGE_NO = 1;
    private long total;
    private List<T> rows;
    private int pageSize = 10;
    private int curPageNo;

    public Page() {
        this.curPageNo = FIRST_PAGE_NO;
    }

    public Page(long total, List<T> results) {
        this.curPageNo = FIRST_PAGE_NO;
        this.total = total;
        this.rows = results;
    }

    public Page(long total, List<T> results, int pageSize) {
        this.curPageNo = FIRST_PAGE_NO;
        this.total = total;
        this.rows = results;
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setResults(List<T> results) {
        this.rows = results;
    }

    public void addrows(T result) {
        if(this.rows == null) {
            this.rows = new ArrayList();
        }

        this.rows.add(result);
    }

    public boolean isEmpty() {
        return this.rows == null || this.rows.isEmpty();
    }

    public int getReturnSize() {
        return this.isEmpty()?0:this.rows.size();
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageCount() {
        return this.pageSize == 0?0:(this.total % (long)this.pageSize == 0L?(int)(this.total / (long)this.pageSize):(int)(this.total / (long)this.pageSize + 1L));
    }

    public int getCurPageNo() {
        return this.curPageNo;
    }

    public void setCurPageNo(int curPageNo) {
        this.curPageNo = curPageNo;
    }

    public boolean hasPre() {
        return this.curPageNo > FIRST_PAGE_NO;
    }

    public boolean hasNext() {
        return this.curPageNo < this.getPageCount();
    }
}

