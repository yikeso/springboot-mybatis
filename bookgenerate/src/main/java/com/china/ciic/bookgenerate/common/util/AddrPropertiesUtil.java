package com.china.ciic.bookgenerate.common.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * 解析method。properties文件
 * @author mayannan
 *
 */
public class AddrPropertiesUtil {
	
	private static Logger logger = Logger
			.getLogger(AddrPropertiesUtil.class);
	
	private AddrPropertiesUtil(){
		
	}
	
	private static Properties props = null;

	/**
	 * 得到属性内容
	 * @param key
	 * @param key
	 * @return
	 */
	public static synchronized String getPropertyValue(String key) {  
		if(props == null){ 
	        try {  
	        	props = new Properties();
	            props.load(AddrPropertiesUtil.class.getResourceAsStream("/SETTINGS/addr.properties"));
	        }catch (IOException e) {  
	            e.printStackTrace();  
	            logger.error(e.getMessage());
	        }
		}
		return props.getProperty(key);
    }  
}
