package com.qunar.ops.workflow.engine.service;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostJdbcCon {
	private static Logger logger = LoggerFactory.getLogger(PostJdbcCon.class);
	 /* 获取数据库连接的函数*/  
    public static Connection getConnection() { 
    	ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		DataSource dataSource = (DataSource)ctx.getBean("dataSource");
        System.out.println(dataSource == null);
        Connection connection = null;
        try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connection; //返回所建立的数据库连接  
    }
    
    /* 查询数据库，输出符合要求的记录的情况*/  
    public static List<ProcessDefine> queryProcessList() {  
    	List<ProcessDefine> infos = new ArrayList<ProcessDefine>();
        Connection connection = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "select proc_key,proc_url,proc_type,proc_name from act_process_list;";     // 查询数据的sql语句  
            System.out.println(sql);
            Statement statement = connection.createStatement();
            ResultSet rs =  statement.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
            while (rs.next()) { // 判断是否还有下一个数据  
                  
                // 根据字段名获取相应的值  
                String name = rs.getString("proc_name");  
                String key = rs.getString("proc_key");
                String type = rs.getString("proc_type");
                String url = rs.getString("proc_url");
                //输出查到的记录的各个字段的值  
                infos.add(new ProcessDefine(key, type, url, name));
            }  
            if (connection != null) {
            	connection.close();   //关闭数据库连接 
			}
        } catch (SQLException e) {  
        	e.printStackTrace();
            System.out.println("查询数据失败");  
        }  
        return infos;
    }  
    
    /* 插入数据记录，并输出插入的数据记录数*/  
    public static String insertProcessList(String key, String type, String url, String name, String user_name) {
    	String returnMsg = "";
        if (judgeContainsSelect(key) ||judgeContainsSelect(type) ||judgeIsNull(url) ||judgeIsNull(name)) {
			returnMsg = new String("key.type,url,name其中有值输入为空，请检查".getBytes(),Charset.forName("UTF-8"));
			logger.error(returnMsg);
			return returnMsg;
		}
    	Connection connection = getConnection(); //同样先要获取连接，即连接到数据库   
        try {
        	String sql = "";
        	sql = "select proc_key from act_process_list where proc_key = '"+ key +"';";
        	Statement statement = connection.createStatement();
        	System.out.println(sql);
            ResultSet rs =  statement.executeQuery(sql); 
            if(rs.next()){
            	returnMsg = "act_process_list已存在key值为："+key+"的数据，不能添加，请知晓";
            	return returnMsg;
            }
        	sql = "INSERT INTO act_process_list (proc_key, proc_type, proc_url, proc_name, user_name)"  
                        + " VALUES ('"+key+"', '"+type+"', '"+url+"', '"+name+"', '"+user_name+"');";  // 插入数据的sql语句  
            int count = statement.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数  
            returnMsg = "向act_process_list表中插入 " + count + " 条数据";
            System.out.println(returnMsg); //输出插入操作的处理结果  
            returnMsg = "添加成功";
            if (connection != null) {
            	connection.close();   //关闭数据库连接 
			}
              
        } catch (SQLException e) {  
        	returnMsg = "插入数据失败" + e.getMessage();
            System.out.println(returnMsg);  
            logger.error("插入数据失败",e);
        }  
        return returnMsg;
    }  
    
    public static String deleteByKey(String key){
    	Connection connection = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "delete from act_process_list where proc_key = '" + key +"';";     // 查询数据的sql语句  
            System.out.println(sql);
            Statement statement = connection.createStatement();
            int rs =  statement.executeUpdate(sql);    //执行sql查询语句，返回查询数据的结果集  
            if (connection != null) {
            	connection.close();   //关闭数据库连接 
			}
        } catch (SQLException e) {  
            System.out.println("删除数据失败");  
        }  
        return "删除成功";
    }
    
    private static boolean judgeIsNull(String input){
    	if(input == null || input.length() == 0){
    		return true;
    	}
    	return false;
    }
    
    private static boolean judgeContainsSelect(String input){
    	if(input.indexOf("请选择") > 0 ){
    		return true;
    	}
    	return false;
    }
}
