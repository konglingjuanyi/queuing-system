package com.qunar.ops.recruit.result;

import java.util.List;

/**
 * 各个表格信息获取
 */
public class DataResult {
    private List<String[]> tableInfos;
    private int count;

    public List<String[]> getTableInfos() {
        return tableInfos;
    }

    public void setTableInfos(List<String[]> tableInfos) {
        this.tableInfos = tableInfos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
