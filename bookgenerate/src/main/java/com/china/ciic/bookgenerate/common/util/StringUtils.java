package com.china.ciic.bookgenerate.common.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StringUtils {
	
	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            ：字母串
	 * @return
	 */
	public static String UpFistChar(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
				.toUpperCase());
	}

	/**
	 * 统计字符在字符串中出现的次数
	 * @param str 字符串
	 * @param c 字符
	 * @param startIndex 统计的起始位置
	 * @author kakasun
	 * @return
	 */
	public static int countFromIndex(String str,char c,int startIndex){
		int n = 0;
		char[] chars = str.toCharArray();
		for(int i = startIndex;i < chars.length;i++){
			if(chars[i] == c){
				n++;
			}
		}
		return n;
	}
	
	public static boolean isOk(String input) {
		return input != null && input.trim().length() > 0;
	}

	public static String formatIds(String ids) {
		// TODO Auto-generated method stub
		if (isOk(ids)) {
			Integer[] result = getIDs(ids);
			ids = "";
			if (result.length > 0) {
				for (int i = 0; i < result.length; i++) {

					ids = ids + result[i] + ",";
				}
				ids = ids.substring(0, ids.length() - 1);
			}
		}
		return ids;
	}

	public static String cleanSpace(String input) {
		if (isOk(input))
			return input.replaceAll("\\s*", "");
		else
			return input;
	}

	public static Integer[] getIDs(String ids, String splittag) {

		if (isOk(ids)) {
			ids = cleanSpace(ids);
			String[] rolestring = ids.split(splittag);
			ArrayList<Integer> part = new ArrayList<Integer>();
			for (String string : rolestring) {
				if (isOk(string)) {
					try {
						Integer value = Integer.parseInt(string.trim());
						if (value != null) {
							part.add(value);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			if (part.size() > 0) {
				return part.toArray(new Integer[part.size()]);
			}
		}
		return new Integer[0];
	}

	public static Integer[] getIDs(String ids) {
		return getIDs(ids, ",");
	}

	public static boolean isContainedID(int needid, String allid) {

		if (isOk(allid)) {
			allid = cleanSpace(allid);
			allid = "," + allid + ",";
			return allid.contains("," + needid + ",");
		}
		return false;
	}

	private static final String yearmonthinter = "-";

	public static String getYearMonth(String year, String month) {
		if (isOk(year) && isOk(month) && year.length() >= 4) {

			return year.substring(0, 4) + yearmonthinter + month;
		}
		return null;
	}

	public static String[] getYearAndMonth(String time) {
		if (isOk(time)) {
			String result[] = time.split(yearmonthinter);
			if (result != null & result.length == 2)
				return result;
		}
		return null;
	}

	public static String getFileType(String filename) {
		int l = filename.lastIndexOf(".");
		if (l > 0) {
			return filename.substring(l + 1).toLowerCase(Locale.ENGLISH);
		} else
			return filename.toLowerCase(Locale.ENGLISH);
	}

	public static String getFileName(String filename) {
		int l = filename.lastIndexOf(".");
		if (l > 0) {
			return filename.substring(0, l);
		} else
			return filename;
	}

	public static String getFileNameWithType(String filename) {
		int l = filename.lastIndexOf("/");
		if (l > 0) {
			return filename.substring(l + 1);
		} else
			return filename;
	}

	public static String EncoderByMd5(String str) {
		return EncoderByMd5(str, false);
	}

	public static String getRandomFileName(String str) {
		return EncoderByMd5(str, true);
	}

	private static String EncoderByMd5(String str, boolean is16) {

		String s = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (is16)
				s = buf.toString().substring(8, 24).toLowerCase(Locale.ENGLISH);
			else
				s = buf.toString().toUpperCase(Locale.ENGLISH);

		} catch (Exception e) {

		}
		return s;
	}

	// public static String getDatefromtime(String year, String month) {
	// String result = null;
	// if (StringUtils.isOk(year) && year.length() >= 4)
	// result = year.substring(0, 4);
	// else
	// return null;
	// if (StringUtils.isOk(month))
	// result = result + CONSTANTS.DATESPACE + month;
	// else
	// return null;
	// return result;
	// }

	public static Date getDatefromStandString(String date) {
		return getDarefromString(date, "yyyy-MM-dd");
	}

	public static Date getDatefromDetailString(String date) {
		return getDarefromString(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getDarefromString(String date, String format) {
		Date result = null;
		if (isOk(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				result = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
		/*
		 * if (result == null) { result = Calendar.getInstance().getTime(); }
		 */
		return result;
	}

	public static String getDetailDate(Date date) {
		// TODO Auto-generated method stub
		return getDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getStandDate(Date date) {
		// TODO Auto-generated method stub
		return getDate(date, "yyyy-MM-dd");
	}

	public static String getDate(Date date, String format) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null)
			return sdf.format(date);
		else {
			return null;
		}
	}

	public static String generateIDs(String appendIds, String id) {
		// TODO Auto-generated method stub
		if (isOk(id)) {
			id = cleanSpace(id);
			if (isOk(appendIds)) {
				appendIds = cleanSpace(appendIds) + "," + id;
			} else {

				appendIds = id;
			}
		}

		return appendIds;
	}

	public static String addaDayStand(String startDate) {
		// TODO Auto-generated method stub
		return moveday(startDate, 1, "yyyy-MM-dd");
	}

	public static String subaDayStand(String startDate) {
		// TODO Auto-generated method stub
		return moveday(startDate, -1, "yyyy-MM-dd");
	}

	public static String moveday(String sdate, int i, String format) {
		// TODO Auto-generated method stub
		if (isOk(sdate)) {
			Date date;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Calendar c = Calendar.getInstance();
				date = sdf.parse(sdate);
				c.setTime(date);
				c.add(Calendar.DATE, i);
				return sdf.format(c.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sdate;
	}

	public static String getNowTime() {
		// TODO Auto-generated method stub
		Date date = new Date(System.currentTimeMillis());
		return getDetailDate(date);
	}

	public static String getNowDate() {
		// TODO Auto-generated method stub
		Date date = new Date(System.currentTimeMillis());
		return getStandDate(date);
	}
	// public static String getPlainTxt(String info) {
	// if (isOk(info)) {
	//
	// String escape = info.replaceAll("<p>", "");
	// escape = escape.replaceAll("</p>", IAppConstants.TXT_ANOTHER_LINE);
	// escape = escape.replaceAll("<br>", IAppConstants.TXT_ANOTHER_LINE);
	// escape = escape.replaceAll("<br/>", IAppConstants.TXT_ANOTHER_LINE);
	// escape = escape.replaceAll("<[^>]*?>", "");
	// escape = StringEscapeUtils.unescapeHtml4(escape);
	// //String text=Jsoup.parse(info).text();
	// return escape;
	// }
	// return info;
	// }
	// /**
	// * 加密
	// *
	// * @param info
	// * @return
	// */
	// public static String getEncyptinfo(String info) {
	// if (StringUtils.isOk(info)) {
	// return AES.getInstance().encrypt(info);
	// }
	// return info;
	// }
	//
	// /**
	// * 解密
	// *
	// * @param info
	// * @return
	// */
	// public static String getDecyptinfo(String info) {
	// if (StringUtils.isOk(info)) {
	// return AES.getInstance().decrypt(info);
	// }
	// return info;
	// }

}
