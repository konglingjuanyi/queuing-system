package com.qunar.ops.oaengine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Copyright @ 2014 OPS, Qunar Inc. (qunar.com)
 * Author: zhenqing.wang <zhenqing.wang@qunar.com>
 * Date: 2014 12/12/14 4:29 PM
 */
public class OAControllerUtils {

    private static Logger logger = LoggerFactory.getLogger(OAControllerUtils.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 判断一个字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 将获取到的money转为人民币单位元
     * 除以100
     *
     * @param money
     * @return
     */
    public static String centMoneyToYuan(Long money) {
    	if(money == null) money = 0L;
        DecimalFormat ndf = new DecimalFormat("##0.00");
        double sMoney = (double) money / 100;
        return ndf.format(sMoney);
    }

    /**
     * 将页面传递过来的值转为人民币单位分
     * 乘以100
     *
     * @param money
     * @return
     */
    public static long yuanMoneyToCent(String money) {
        if (isNull(money)) {
            return 0l;
        }
        Float fMoney = Float.valueOf(money);
        int iMoney = (int) (fMoney * 100);
        String sMoney = String.valueOf(iMoney);
        return Long.valueOf(sMoney);
    }

    /**
     * 将获取到的workHour转为BigDecimal
     * @param workHour
     * @return
     */
    public static BigDecimal workHourToDec(String workHour){
        if (isNull(workHour)){
            return new BigDecimal(0);
        }
        return new BigDecimal(workHour);
    }

    /**
     * 字符串转为long
     * @param numStr
     * @return
     */
    public static long strToLong(String numStr){
        if (isNull(numStr)){
            return 0;
        }
        return Long.valueOf(numStr);
    }

    /**
     * 获取当前是第几页以及每页的size
     *
     * @param vars
     * @return
     */
    public static int[] getPageNoAndSize(Map<String, String> vars) {
        String sStart = vars.get("start");
        String sLen = vars.get("length");
        int start = sStart == null ? 0 : Integer.valueOf(sStart);
        int len = sLen == null ? 0 : Integer.valueOf(sLen);
        int pageSize = len;
        int pageNo = start / len + 1;
        return new int[]{pageSize, pageNo};
    }

    /**
     * 日期转为字符串
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        String dateStr = "";
        if (date != null) {
            dateStr = sdf.format(date);
        }
        return dateStr;
    }

    /**
     * 日期转为字符串
     *
     * @param timeStr
     * @return
     */
    public static Date strToDate(String timeStr) {
        Date date = null;
        if (!isNull(timeStr)) {
            try {
                date = sdf.parse(timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                return date;
            }
        }
        return date;
    }

    /**
     * 将审批过程中的英文转为中文.
     *
     * @param approveEn
     * @return
     */
    public static String transformApproveEnToCh(String approveEn) {
        if ("pass".equals(approveEn)) {
            return "同意";
        } else if ("back".equals(approveEn)) {
            return "退回";
        } else if ("endorse".equals(approveEn)) {
            return "加签";
        } else if ("refuse".equals(approveEn)) {
            return "拒绝";
        } else if ("start".equals(approveEn)) {
            return "申请";
        }
        return "";
    }
}