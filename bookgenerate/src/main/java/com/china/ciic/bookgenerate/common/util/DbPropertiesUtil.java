package com.china.ciic.bookgenerate.common.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 解析method。properties文件
 * @author mayannan
 *
 */
public class DbPropertiesUtil {
	private DbPropertiesUtil(){
		
	}
	
	private static Properties props = null;

	/**
	 * 得到属性内容
	 * @param propertiesFileName
	 * @param key
	 * @return
	 */
	public static synchronized String getPropertyValue(String key) {  
		if(props == null){ 
	        try {  
	        	props = new Properties();
	            props.load(DbPropertiesUtil.class.getResourceAsStream("/SETTINGS/studymanager.properties"));
	        }catch (IOException e) {  
	            e.printStackTrace();
	        }
		}
		return props.getProperty(key);
    }  
}
