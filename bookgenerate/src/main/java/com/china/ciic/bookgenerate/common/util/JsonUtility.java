package com.china.ciic.bookgenerate.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtility {

	private static ObjectMapper objectMapper;

	/**
	 * 初始化，包括设置为空不报错及属性存在不报错
	 */
	private static void initObjectMapper() {
		// TODO Auto-generated method stub
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
					false);
			objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);//	objectMapper.setSerializationInclusion(Include.NON_NULL);
		}
	}

	/**
	 * 将bean转为json
	 * 
	 * @param bean
	 *            要转换的对象
	 * @return String 转换后的json字符串
	 * @throws JsonProcessingException
	 */
	public static String getJsonfromBean(Object bean)
			throws JsonProcessingException {

		if (bean != null) {
			initObjectMapper();
			return objectMapper.writeValueAsString(bean);
		} else
			return null;
	}

	/**
	 * 将json转为bean
	 * 
	 * @param json
	 *            要转的json
	 * @param beanclass
	 *            转完得到的bean class
	 * @return 
	 * @return Object 转完的bean
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T getBeanformJson(String json, Class<T> beanclass)
			 {
		if (beanclass != null) {
			if (StringUtils.isOk(json)) {
				initObjectMapper();
				try {
					return objectMapper.readValue(json, beanclass);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}