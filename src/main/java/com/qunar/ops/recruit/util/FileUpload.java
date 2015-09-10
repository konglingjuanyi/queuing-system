package com.qunar.ops.recruit.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Maps;

/**
 * Created by hongyanggao on 15-4-29.
 */
public class FileUpload {
    private static Logger logger = LoggerFactory.getLogger(FileUpload.class);

    /**
     * 文件上传功能
     * allowedExt:允许上传的文件类型。
     * maxSize：允许上传的文件大小
     * savaPath:上传后存储的位置
     */
    public static String upload(HttpServletRequest request, ArrayList<String> allowedExt, String savePath) {
        Map<String, String> map = Maps.newHashMap();
        String fileName ="";
        try {
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        	/**构建图片保存的目录**/    
            String logoPathDir = savePath;
            /**得到图片保存目录的真实路径**/    
            String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir); 
            /**根据真实路径创建目录**/    
            File logoSaveFile = new File(logoRealPathDir);     
            if(!logoSaveFile.exists()){
            	logoSaveFile.mkdirs();
            }   
            /**页面控件的文件流**/    
            MultipartFile multipartFile = multipartRequest.getFile("file"); 
            /**获取文件的后缀**/    
            String suffix = multipartFile.getOriginalFilename().substring  
                            (multipartFile.getOriginalFilename().lastIndexOf(".")); 
            String logImageName = RecruitControllerUtils.toMd5(UUID.randomUUID().toString());  
            /**拼成完整的文件保存路径加文件**/    
            fileName = logoRealPathDir + File.separator   + logImageName + "" + suffix;
            File file = new File(fileName);
            multipartFile.transferTo(file);
            return logImageName + "" + suffix;
        }catch (Exception e) {     
            e.printStackTrace();     
        } 
        return "";
    }

}