package com.qunar.ops.oaengine.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenqingwang on 11/28/14.
 */
public class ApproveResult implements Serializable{
    private static final long serialVersionUID = 1081620771368389382L;
    private long count;
    private List<String[]> infos;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<String[]> getInfos() {
        return infos;
    }

    public void setInfos(List<String[]> infos) {
        this.infos = infos;
    }
}
