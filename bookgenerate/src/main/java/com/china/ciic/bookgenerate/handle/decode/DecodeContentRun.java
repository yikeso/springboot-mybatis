package com.china.ciic.bookgenerate.handle.decode;

import java.io.*;

public class DecodeContentRun {
	/**
	 * 测试文本解压方法
	 * @param agrs
	 */
	public static void main(String[] agrs) {
		File file = new File("f://test3313.txt");
		InputStream is = null;
		StringBuilder data = new StringBuilder(); 
		long l = System.currentTimeMillis();
		try {
			if (file.exists()) {
				is = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					DecodeComponent component = new DecodeContentComponent(line);
					component = new DecodeContentDecorator(component);
					component = new DeCompressContentDecorator(component); 
					data.append(component.operation());
				}
				System.out.println(System.currentTimeMillis()-l);
				is.close();
			} else {
				System.out.println(file.getAbsolutePath() + " is not exist!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(data.toString());
		getParagraphHtml(data.toString());
		//System.out.println();
		System.out.println(System.currentTimeMillis()-l);
	}
	
	private static String getParagraphHtml(String data){
		StringBuilder p = new StringBuilder();
		String[] ss = data.toString().split("~~");
		for(int i=0; i<ss.length; i++){
			System.out.println(ss[i]);
			//System.out.println(HtmlHandle.addParagraphHtml(ss[i], i));
		}
		return p.toString();
	}
}
