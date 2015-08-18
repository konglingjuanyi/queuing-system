package com.qunar.ops.recruit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Copyright @ 2014 OPS, Qunar Inc. (qunar.com)
 * Author: zhenqing.wang <zhenqing.wang@qunar.com>
 * Date: 2014 12/12/14 4:29 PM
 */
public class OAControllerUtils {

    private static Logger logger = LoggerFactory.getLogger(OAControllerUtils.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    private static SimpleDateFormat sdfII = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    
    public static float centMoneyToYuanII(Long money) {
        if (money == null) {
            return 0;
        }
        try{
	        DecimalFormat ndf = new DecimalFormat("##0.00");
	        double sMoney = (double) money / 100;
	        //return Float.valueOf(ndf.format(sMoney));
	        BigDecimal b = new BigDecimal(Float.valueOf(ndf.format(sMoney))); 
	        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); 
	        
        }catch(Exception e){
        	return 0;
        }
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
        try{
        	Double fMoney = Double.valueOf(money);
	        long iMoney = (long) (fMoney * 100);
	        String sMoney = String.valueOf(iMoney);
	        return Long.valueOf(sMoney);
        }catch(Exception e){
        	throw new NumberFormatException("数字格式错误！");
        }
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
            dateStr = sdfII.format(date);
        }
        return dateStr;
    }
    
    public static String dateToStrII(Date date) {
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
        //if (!isNull(timeStr)) {
            try {
                date = sdf.parse(timeStr);
            } catch (ParseException e) {
                //e.printStackTrace();
                //logger.error(e.getMessage());
                //return date;
                throw new NumberFormatException("日期格式错误");
            }
        //}
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
        } else if ("cancel".equals(approveEn)) {
            return "取消";
        } else if ("recall".equals(approveEn)) {
            return "召回";
        } 
        return "";
    }
    
    public static boolean isDebug(){
    	try {
    		Resource resource = new ClassPathResource("/oaengine.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String debug = props.getProperty("debug", "false");
			return "true".equals(debug)?true:false;
		} catch (IOException e) {
			return false;
		}
    }
    
    
}
