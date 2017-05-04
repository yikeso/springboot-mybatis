package com.china.ciic.bookgenerate.common.constants;

import java.util.HashMap;
import java.util.Map;

public class ParserConstants {
	
	/**默认文件名*/
	public static final String DEFAULTFILENAME = "1.txt";
	/**目录文件名*/
	public static final String DIRECTORYFILENAME = "directory.txt";
	/**默认文件后缀*/
	public static final String DEFAULTFILESUFFIX = ".txt";
	/**默认转换位数*/
	public static final int CUTSIZE = 6;
	/**默认文件保存位置*/
	public static final String DEFAULTPATH = "/usr/local/tomcat/webapps/studyreaderapi/WEB-INF/classes/resource/";
	//long型id乘以这个系数，将id转换为时间毫秒数，进行日期操作
	public static final long ID_TO_TIME = 10000L;
	//没有压缩的电子书存放路径
    public static final String bookDirWithOutCompress = "k:/bookDirWithOutCompress/";
    //没有编码的电子书存放路径
    public static final String bookDirWithOutEncode = "k:/bookDirWithOutEncode/";

	public static Map<String, String> MAP1 = new HashMap<String, String>();
	public static Map<String, String> REVERSEMAP1 = new HashMap<String, String>();
	public static Map<String, String> MAP = new HashMap<String, String>();
	public static Map<String, String> REVERSEMAP = new HashMap<String, String>();

	static{

		MAP.put("000000", "0");
		MAP.put("000001", "1");
		MAP.put("000010", "2");
		MAP.put("000011", "3");
		MAP.put("000100", "4");
		MAP.put("000101", "5");
		MAP.put("000110", "6");
		MAP.put("000111", "7");
		MAP.put("001000", "8");
		MAP.put("001001", "9");
		
		MAP.put("001010", "a");
		MAP.put("001011", "b");
		MAP.put("001100", "c");
		MAP.put("001101", "d");
		MAP.put("001110", "e");
		MAP.put("001111", "f");
		MAP.put("010000", "g");
		MAP.put("010001", "h");
		MAP.put("010010", "i");
		MAP.put("010011", "j");
		MAP.put("010100", "k");
		MAP.put("010101", "l");
		MAP.put("010110", "m");
		MAP.put("010111", "n");
		MAP.put("011000", "o");
		MAP.put("011001", "p");
		MAP.put("011010", "q");
		MAP.put("011011", "r");
		MAP.put("011100", "s");
		MAP.put("011101", "t");
		MAP.put("011110", "u");
		MAP.put("011111", "v");
		MAP.put("100000", "w");
		MAP.put("100001", "x");
		MAP.put("100010", "y");
		MAP.put("100011", "z");
		
		MAP.put("100100", "A");
		MAP.put("100101", "B");
		MAP.put("100110", "C");
		MAP.put("100111", "D");
		MAP.put("101000", "E");
		MAP.put("101001", "F");
		MAP.put("101010", "G");
		MAP.put("101011", "H");
		MAP.put("101100", "I");
		MAP.put("101101", "J");
		MAP.put("101110", "K");
		MAP.put("101111", "L");
		MAP.put("110000", "M");
		MAP.put("110001", "N");
		MAP.put("110010", "O");
		MAP.put("110011", "P");
		MAP.put("110100", "Q");
		MAP.put("110101", "R");
		MAP.put("110110", "S");
		MAP.put("110111", "T");
		MAP.put("111000", "U");
		MAP.put("111001", "V");
		MAP.put("111010", "W");
		MAP.put("111011", "X");
		MAP.put("111100", "Y");
		MAP.put("111101", "Z");
		MAP.put("111110", "_");
		MAP.put("111111", "$");
		
		REVERSEMAP.put("0", "000000");
		REVERSEMAP.put("1", "000001");
		REVERSEMAP.put("2", "000010");
		REVERSEMAP.put("3", "000011");
		REVERSEMAP.put("4", "000100");
		REVERSEMAP.put("5", "000101");
		REVERSEMAP.put("6", "000110");
		REVERSEMAP.put("7", "000111");
		REVERSEMAP.put("8", "001000");
		REVERSEMAP.put("9", "001001");
		
		REVERSEMAP.put("a", "001010");
		REVERSEMAP.put("b", "001011");
		REVERSEMAP.put("c", "001100");
		REVERSEMAP.put("d", "001101");
		REVERSEMAP.put("e", "001110");
		REVERSEMAP.put("f", "001111");
		REVERSEMAP.put("g", "010000");
		REVERSEMAP.put("h", "010001");
		REVERSEMAP.put("i", "010010");
		REVERSEMAP.put("j", "010011");
		REVERSEMAP.put("k", "010100");
		REVERSEMAP.put("l", "010101");
		REVERSEMAP.put("m", "010110");
		REVERSEMAP.put("n", "010111");
		REVERSEMAP.put("o", "011000");
		REVERSEMAP.put("p", "011001");
		REVERSEMAP.put("q", "011010");
		REVERSEMAP.put("r", "011011");
		REVERSEMAP.put("s", "011100");
		REVERSEMAP.put("t", "011101");
		REVERSEMAP.put("u", "011110");
		REVERSEMAP.put("v", "011111");
		REVERSEMAP.put("w", "100000");
		REVERSEMAP.put("x", "100001");
		REVERSEMAP.put("y", "100010");
		REVERSEMAP.put("z", "100011");
		
		REVERSEMAP.put("A", "100100");
		REVERSEMAP.put("B", "100101");
		REVERSEMAP.put("C", "100110");
		REVERSEMAP.put("D", "100111");
		REVERSEMAP.put("E", "101000");
		REVERSEMAP.put("F", "101001");
		REVERSEMAP.put("G", "101010");
		REVERSEMAP.put("H", "101011");
		REVERSEMAP.put("I", "101100");
		REVERSEMAP.put("J", "101101");
		REVERSEMAP.put("K", "101110");
		REVERSEMAP.put("L", "101111");
		REVERSEMAP.put("M", "110000");
		REVERSEMAP.put("N", "110001");
		REVERSEMAP.put("O", "110010");
		REVERSEMAP.put("P", "110011");
		REVERSEMAP.put("Q", "110100");
		REVERSEMAP.put("R", "110101");
		REVERSEMAP.put("S", "110110");
		REVERSEMAP.put("T", "110111");
		REVERSEMAP.put("U", "111000");
		REVERSEMAP.put("V", "111001");
		REVERSEMAP.put("W", "111010");
		REVERSEMAP.put("X", "111011");
		REVERSEMAP.put("Y", "111100");
		REVERSEMAP.put("Z", "111101");
		
		REVERSEMAP.put("_", "111110");
		REVERSEMAP.put("$", "111111");
		
		MAP1.put("0", "0");
		MAP1.put("1", "1");
		MAP1.put("2", "2");
		MAP1.put("3", "3");
		MAP1.put("4", "4");
		MAP1.put("5", "5");
		MAP1.put("6", "6");
		MAP1.put("7", "7");
		MAP1.put("8", "8");
		MAP1.put("9", "9");
		
		MAP1.put("10", "a");
		MAP1.put("11", "b");
		MAP1.put("12", "c");
		MAP1.put("13", "d");
		MAP1.put("14", "e");
		MAP1.put("15", "f");
		MAP1.put("16", "g");
		MAP1.put("17", "h");
		MAP1.put("18", "i");
		MAP1.put("19", "j");
		MAP1.put("20", "k");
		MAP1.put("21", "l");
		MAP1.put("22", "m");
		MAP1.put("23", "n");
		MAP1.put("24", "o");
		MAP1.put("25", "p");
		MAP1.put("26", "q");
		MAP1.put("27", "r");
		MAP1.put("28", "s");
		MAP1.put("29", "t");
		MAP1.put("30", "u");
		MAP1.put("31", "v");
		MAP1.put("32", "w");
		MAP1.put("33", "x");
		MAP1.put("34", "y");
		MAP1.put("35", "z");
		
		MAP1.put("36", "A");
		MAP1.put("37", "B");
		MAP1.put("38", "C");
		MAP1.put("39", "D");
		MAP1.put("40", "E");
		MAP1.put("41", "F");
		MAP1.put("42", "G");
		MAP1.put("43", "H");
		MAP1.put("44", "I");
		MAP1.put("45", "J");
		MAP1.put("46", "K");
		MAP1.put("47", "L");
		MAP1.put("48", "M");
		MAP1.put("49", "N");
		MAP1.put("50", "O");
		MAP1.put("51", "P");
		MAP1.put("52", "Q");
		MAP1.put("53", "R");
		MAP1.put("54", "S");
		MAP1.put("55", "T");
		MAP1.put("56", "U");
		MAP1.put("57", "V");
		MAP1.put("58", "W");
		MAP1.put("59", "X");
		MAP1.put("60", "Y");
		MAP1.put("61", "Z");
		MAP1.put("62", "_");
		MAP1.put("63", "$");
		
		REVERSEMAP1.put("0", "0");
		REVERSEMAP1.put("1", "1");
		REVERSEMAP1.put("2", "2");
		REVERSEMAP1.put("3", "3");
		REVERSEMAP1.put("4", "4");
		REVERSEMAP1.put("5", "5");
		REVERSEMAP1.put("6", "6");
		REVERSEMAP1.put("7", "7");
		REVERSEMAP1.put("8", "8");
		REVERSEMAP1.put("9", "9");
		
		REVERSEMAP1.put("a", "10");
		REVERSEMAP1.put("b", "11");
		REVERSEMAP1.put("c", "12");
		REVERSEMAP1.put("d", "13");
		REVERSEMAP1.put("e", "14");
		REVERSEMAP1.put("f", "15");
		REVERSEMAP1.put("g", "16");
		REVERSEMAP1.put("h", "17");
		REVERSEMAP1.put("i", "18");
		REVERSEMAP1.put("j", "19");
		REVERSEMAP1.put("k", "20");
		REVERSEMAP1.put("l", "21");
		REVERSEMAP1.put("m", "22");
		REVERSEMAP1.put("n", "23");
		REVERSEMAP1.put("o", "24");
		REVERSEMAP1.put("p", "25");
		REVERSEMAP1.put("q", "26");
		REVERSEMAP1.put("r", "27");
		REVERSEMAP1.put("s", "28");
		REVERSEMAP1.put("t", "29");
		REVERSEMAP1.put("u", "30");
		REVERSEMAP1.put("v", "31");
		REVERSEMAP1.put("w", "32");
		REVERSEMAP1.put("x", "33");
		REVERSEMAP1.put("y", "34");
		REVERSEMAP1.put("z", "35");
		
		REVERSEMAP1.put("A", "36");
		REVERSEMAP1.put("B", "37");
		REVERSEMAP1.put("C", "38");
		REVERSEMAP1.put("D", "39");
		REVERSEMAP1.put("E", "40");
		REVERSEMAP1.put("F", "41");
		REVERSEMAP1.put("G", "42");
		REVERSEMAP1.put("H", "43");
		REVERSEMAP1.put("I", "44");
		REVERSEMAP1.put("J", "45");
		REVERSEMAP1.put("K", "46");
		REVERSEMAP1.put("L", "47");
		REVERSEMAP1.put("M", "48");
		REVERSEMAP1.put("N", "49");
		REVERSEMAP1.put("O", "50");
		REVERSEMAP1.put("P", "51");
		REVERSEMAP1.put("Q", "52");
		REVERSEMAP1.put("R", "53");
		REVERSEMAP1.put("S", "54");
		REVERSEMAP1.put("T", "55");
		REVERSEMAP1.put("U", "56");
		REVERSEMAP1.put("V", "57");
		REVERSEMAP1.put("W", "58");
		REVERSEMAP1.put("X", "59");
		REVERSEMAP1.put("Y", "60");
		REVERSEMAP1.put("Z", "61");
		
		REVERSEMAP1.put("_", "62");
		REVERSEMAP1.put("$", "63");
	}


}
