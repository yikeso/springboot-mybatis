package com.china.ciic.bookgenerate.common.util;


import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;
import org.apache.commons.lang3.StringEscapeUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.tags.*;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.NodeVisitor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlHandleNew {
		
	/**
	 * 按章得到文章的内容(两个参数)
	 * @param url
	 * @param encoding
	 * @throws Exception 
	 */
	public static ChapterEntity getArticleContentByChapter(final String url, String encoding) throws Exception{
		return getArticleContentByChapter(url, encoding, true);
	}
	
	/**
	 * 按章得到文章的内容(三个参数)
	 * @param url
	 * @param encoding
	 * @throws Exception 
	 */
	public static ChapterEntity getArticleContentByChapter(final String url, String encoding, boolean createHtmlContent) throws Exception{
		return getArticleContentByChapter(url, encoding, null, null,createHtmlContent);
	}
	
	/**
	 * 按章得到文章的内容(四个参数)
	 * @param url
	 * @param encoding
	 * @throws Exception 
	 */
	public static ChapterEntity getArticleContentByChapter(final String url, String encoding, final String anchor1, final String anchor2) throws Exception{
		return getArticleContentByChapter(url, encoding, anchor1, anchor2,true);
	}
	
	/**
	 * 按章得到书的内容(两个参数)
	 * @param url
	 * @param encoding
	 * @throws Exception 
	 */
	public static ChapterEntity getBookContentByChapter(final String url, String encoding) throws Exception{
		return getBookContentByChapter(url, encoding, true);
	}	   
	   
	/**
	 * 按章得到书的内容(三个参数)
	 * @param url
	 * @param encoding
	 * @throws Exception 
	 */
	public static ChapterEntity getBookContentByChapter(final String url, String encoding, boolean createHtmlContent) throws Exception{
		return getBookContentByChapter(url, encoding, null, null,createHtmlContent);
	}
	
	/**
	 * 按章得到书的内容(四个参数)
	 * @param url
	 * @param encoding
	 * @throws Exception 
	 */
	public static ChapterEntity getBookContentByChapter(final String url, String encoding, final String anchor1, final String anchor2) throws Exception{
		return getBookContentByChapter(url, encoding, anchor1, anchor2,true);
	}
	
	/**
	 * 按章得到文章的内容(五个参数)
	 * @param url
	 * @param encoding
	 * @param anchor1
	 * @param anchor2
	 * @return
	 * @throws Exception 
	 */
	public static ChapterEntity getArticleContentByChapter(final String url, String encoding, final String anchor1, final String anchor2, boolean createHtmlContent) throws Exception{
		System.out.println("url:"+url);
		final ChapterEntity chapter = new ChapterEntity();
		final StringBuilder content = new StringBuilder();
		final StringBuilder htmlContent = new StringBuilder();//2017年4月17日加
		final StringBuilder ids = new StringBuilder();//2017年4月17日加
		ids.append(",1");//2017年4月17日加
		try {
			String urlToString = urlToString(url, encoding);
			Document document = Jsoup.parse(urlToString);
			isOrNotNull(document, url);//判断url对应的页面是否为空，若为空抛一个异常
			//当锚点一在文件中找不到时，从文件的开始处拼接段落。如果没有下面的这个处理，这种情况下返回的ChapterEntity的content是空字符串
			addStartMarkWhileFileNoAnchor1(document, anchor1, content, htmlContent);
            Parser parser = new Parser();
            parser.setInputHTML(urlToString);
            NodeVisitor visitor = new NodeVisitor() {
                public void visitTag(Tag tag) {
                	addMarkOfStartAndEnd(tag, anchor1, anchor2, content, htmlContent);//添加开始和结束标记，实现截取两个锚点之间的段落
                    if(tag.getTagName().trim().equalsIgnoreCase("P") || tag.getTagName().trim().equalsIgnoreCase("H1") || tag.getTagName().trim().equalsIgnoreCase("H2") 
                    		|| tag.getTagName().trim().equalsIgnoreCase("H3") || tag.getTagName().trim().equalsIgnoreCase("H4")|| tag.getTagName().trim().equalsIgnoreCase("H5")
                    		|| tag.getTagName().trim().equalsIgnoreCase("H6") 
                    		|| tag.getTagName().trim().equalsIgnoreCase("DIV") || tag.getTagName().trim().equalsIgnoreCase("TABLE")){
                    	if(null != tag.getChildren()){
                    		String s = "";
                    		String htmlS = "";//2017年4月17日加
                    		String tagName = tag.getTagName();//测试用
                    		String inner = tag.getChildren().toHtml();
/*                    		Document tagDocument = Jsoup.parse(tag.toHtml());
                    		Element bodyAndDiv = tagDocument.body();
                    		List<TextNode> textNodeList = bodyAndDiv.child(0).textNodes();
                    		int textNodeNum = textNodeList.size();*/
                    		//判断P标签的父标签是不是<tr>
                    		boolean parentIsTr = false;
                    		boolean haveInnerLabel = false;//是否嵌套有标签
                    		if( tag.getTagName().trim().equalsIgnoreCase("P") ){
                    			Node tagParent = tag.getParent();
                    			String testP = tagParent.toHtml();
                    			if(tagParent != null){
                    				parentIsTr = tagParent.toHtml().matches("[^<>]*<\\s?[Tt][Dd].+<\\s?/\\s?[Tt][Dd].*>");
                    			}
                    		}else if( tag.getTagName().trim().equalsIgnoreCase("DIV") ){
                    			String innerString = tag.getChildren().toHtml();
    /*                			if(innerString.matches(".*<\\s?/?\\s?[Pp].*")){
                    				haveInnerLabel = true;
                    			}else if(innerString.matches(".*<\\s?/?\\s?[Dd][Ii][Vv].*")){
                    				haveInnerLabel = true;
                    			}else if(innerString.matches(".*<\\s?/?\\s?[Hh].*")){
                    				haveInnerLabel = true;
                    			}else if(innerString.matches(".*<\\s?/?\\s?[Tt][Aa][Bb][Ll][Ee].*")){
                    				haveInnerLabel = true;
                    			}*/
                    			if(innerString.toLowerCase().indexOf("<p") != -1){
                    				haveInnerLabel = true;
                    			}else if(innerString.toLowerCase().indexOf("<div") != -1){
                    				haveInnerLabel = true;
                    			}else if(innerString.toLowerCase().indexOf("<h") != -1){
                    				haveInnerLabel = true;
                    			}else if(innerString.toLowerCase().indexOf("<table") != -1){
                    				haveInnerLabel = true;
                    			}
                    		}
                    		if( tag.getTagName().trim().equalsIgnoreCase("TABLE") ){
                    			String id = ids.substring(ids.lastIndexOf(",")+1);//id
                    			String tableString = tag.toHtml();
                    			s = removeThisLabel(tableString,"span");
                    			s = pToBr(s);
                    			htmlS = addSpanAndNumReserveHtml(s, id);
                    			endDeal(chapter, content, s, htmlContent, htmlS, anchor1, anchor2, ids);
                    		}else if(tag.getTagName().trim().equalsIgnoreCase("DIV") && haveInnerLabel){
                    			//不做任何操作
                    		}else if((tag.getTagName().trim().equalsIgnoreCase("P") && parentIsTr)){
                    			//不做任何操作
                    		}else if((tag.getTagName().trim().equalsIgnoreCase("DIV") && ("pic".equalsIgnoreCase(tag.getAttribute("class")) || "title_pic".equalsIgnoreCase(tag.getAttribute("class"))))){
                    			String id = ids.substring(ids.lastIndexOf(",")+1);
                    			s = removeThisLabel(tag.getChildren().toHtml(),"span");//20170425
                    			s = removeThisLabel(s,"p");//20170425
                    			s = removeThisLabel(s,"div");//20170425
                    			htmlS = removeLabelAndSetHtmlS(tag, id);//2017年4月17日加 
                    			endDeal(chapter, content, s, htmlContent, htmlS, anchor1, anchor2, ids);
                    		}else if(tag.getTagName().trim().equals("H1") || tag.getTagName().trim().equals("H2") || tag.getTagName().trim().equals("H3")
                    				|| tag.getTagName().trim().equals("H4")|| tag.getTagName().trim().equals("H5") || tag.getTagName().trim().equals("H6")){
                    			String id = ids.substring(ids.lastIndexOf(",")+1);
                    			s = tag.toHtml();
                    			s = removeThisLabel(tag.toHtml(), "span");//20170425改
                    			s = removeThisLabel(s, "p");//20170425改
                    			s = removeThisLabel(s, "div");//20170425改
                    			htmlS = addSpanAndNumReserveHtml(s, id);//20170425改
                    			endDeal(chapter, content, s, htmlContent, htmlS, anchor1, anchor2, ids);
                    		}else if(tag.getTagName().trim().equalsIgnoreCase("P") || tag.getTagName().trim().equalsIgnoreCase("DIV")){//2017年4月22日 增加DIV
                    			String id = ids.substring(ids.lastIndexOf(",")+1);
                    			s = removeThisLabel(tag.getChildren().toHtml(), "span");//20170425改
                    			s = removeThisLabel(s, "p");//20170425改
                    			s = removeThisLabel(s, "div");//20170425改
                    			htmlS = removeLabelAndSetHtmlS(tag, id);//2017年4月17日加
                    			SimpleNodeIterator iterator = tag.getChildren().elements();
	                			while(iterator.hasMoreNodes()){
	                				Node n = iterator.nextNode();
	                				String nHtml = removeThisLabel(n.toHtml(), "span");//20170425改
	                				nHtml = removeThisLabel(nHtml, "p");//20170425改
	                				nHtml = removeThisLabel(nHtml, "div");//20170425改
		                			if(nHtml.toLowerCase().indexOf("<embed") > -1){//flash
		                				s = addPreForFlashSrc(nHtml, url);//将flash的相对路径改成绝对路径
		                				StringBuilder s3 = new StringBuilder();
		                				if(s != null && s != "" && s.length() > 6){		           
		                					s3.append("<span parentid=\""+id+"\" wordoffset=\"1\" class=\"wchar\" style=\"display:inline-block;text-indent:0em;\"><embed parentid=\""+id+"\" ").append(s.substring(6)).append("</span>");
		                				}
		                				htmlS = s3.toString();
		                			}else if(nHtml.length() > 0 && nHtml.toLowerCase().indexOf("<img") > -1){//img
		                				String[] sAndHtmlS = dealImg2(nHtml, url, id);
		                				s = sAndHtmlS[0];
		                				htmlS = sAndHtmlS[1];
	                				}/*else{
	                        			//s = removeHTMLLabel2(tag.getChildren().toHtml());	 
	                					s = nHtml;//20170425改
	                        			htmlS = removeLabelAndSetHtmlS(tag, id);//2017年4月17日加
	                				}*///20170425
	                			}
	                			endDeal(chapter, content, s, htmlContent, htmlS, anchor1, anchor2, ids);
                    		}else{
                    			String id = ids.substring(ids.lastIndexOf(",")+1);
                    			s = removeThisLabel(tag.getChildren().toHtml(),"span");//20170425加
                    			s = removeThisLabel(s,"p");//20170425加
                    			s = removeThisLabel(s,"div");//20170425加
                    			htmlS = removeLabelAndSetHtmlS(tag, id);//2017年4月17日加
                    			endDeal(chapter, content, s, htmlContent, htmlS, anchor1, anchor2, ids);
                    		}
                    		//
                    	}
                    }
                }
            };
            parser.visitAllNodesWith(visitor);
        } catch (Exception ex){
        	throw ex;
        }
		//去掉content中的[[[[[[和]]]]]]，并给chapter的content设值 
		chapter.setContent(cutMark(content, "[[[[[[", "]]]]]]"));//2017年4月15日改 改之前为chapter.setContent(content.toString());		
		if(createHtmlContent){//2017年4月18日加判断			
			chapter.setHtmlContent(cutMark(htmlContent, "[[[[[[", "]]]]]]"));//2017年4月17日加
		}
		return chapter;
	}
	
	/**
	 * 按章得到书的内容(五个参数)
	 * @param url
	 * @param encoding
	 * @param anchor1
	 * @param anchor2
	 * @return
	 * @throws Exception 
	 */
	public static ChapterEntity getBookContentByChapter(final String url, String encoding, final String anchor1, final String anchor2, boolean createHtmlContent) throws Exception{
		return getArticleContentByChapter(url, encoding, anchor1, anchor2, createHtmlContent);
	}

	/**
	 * 
	 * @param chapter
	 * @param content
	 * @param s
	 * @param htmlContent
	 * @param htmlS
	 * @param anchor1
	 * @param anchor2
	 * @param ids
	 */
	private static void endDeal(ChapterEntity chapter, StringBuilder content, String s, StringBuilder htmlContent, String htmlS, String anchor1, String anchor2, StringBuilder ids){
		while (s.startsWith("\t") || s.startsWith("　")) {
			if(s.length() > 1){				
				s = s.substring(1);
			}else{
				s = "";
				break;
			}
		}
		s = StringEscapeUtils.unescapeHtml4(s);//处理HTML转义字符
		//htmlS = StringEscapeUtils.unescapeHtml4(htmlS);//处理HTML转义字符
		//选取两个锚点之间的段落，拼接到content中；统计两个锚点之间的段落数，并改变chapter的paragraph值
		selectParagraphs(chapter, content, s, htmlContent, htmlS, anchor1, anchor2, ids);//2017年4月17日加两个参数
	}
	
	/**
	 * 除去span标签，返回一个用span标签包裹汉字的字符串
	 * @param tag
	 * @param id
	 * @return
	 */
	private static String removeLabelAndSetHtmlS(Tag tag,String id){//2017年4月17日加
		String removeSpan = removeThisLabel(tag.getChildren().toHtml(),"span");
		removeSpan = removeThisLabel(removeSpan,"p");
		removeSpan = removeThisLabel(removeSpan,"div");
		//removeSpan = filterHtmlTag2(removeSpan,"a");//去掉<a />
		//removeSpan = removeHTMLLabel2(removeSpan);//2017年4月19日加
/*		StringBuilder pHtmlS = new StringBuilder();
		int indexRight = removeSpan.indexOf(">");
		if(indexRight > -1){
			int indexLeft = removeSpan.lastIndexOf("<");
			if(indexLeft > indexRight+1){
				String headLabel = removeSpan.substring(0, indexRight+1);
				String text = removeSpan.substring(indexRight+1,indexLeft);
				String tailLabel = removeSpan.substring(indexLeft);
				pHtmlS.append(headLabel);
				for(int i=0; i<text.length(); i++){
					pHtmlS.append("<span parentid=\""+id+"\" class=\"wchar\" wordoffset=\""+(i+1)+"\">").append(text.charAt(i)).append("</span>");
				}
				pHtmlS.append(tailLabel);
			}else{
				for(int i=0; i<removeSpan.length(); i++){
					pHtmlS.append("<span parentid=\""+id+"\" class=\"wchar\" wordoffset=\""+(i+1)+"\">").append(removeSpan.charAt(i)).append("</span>");
				}
			}
			return pHtmlS.toString();
		}else{
			for(int i=0; i<removeSpan.length(); i++){
				pHtmlS.append("<span parentid=\""+id+"\" class=\"wchar\" wordoffset=\""+(i+1)+"\">").append(removeSpan.charAt(i)).append("</span>");
			}
			return pHtmlS.toString();
		}*/
		return addSpanAndNumReserveHtml(removeSpan,id);
	}
	
	/**
	 * 选取两个锚点之间的段落，拼接到content中；统计两个锚点之间的段落数，并改变chapter的paragraph值
	 * @param chapter
	 * @param content
	 * @param s
	 * @param anchor1
	 * @param anchor2
	 */
	private static void selectParagraphs(ChapterEntity chapter, StringBuilder content, String s, StringBuilder htmlContent, String htmlS, String anchor1, String anchor2, StringBuilder ids){
		if(anchor1 != null && anchor1 != ""){
			if(anchor2 != null && anchor2 != ""){
				if(content.indexOf("[[[[[[") >= 0 && content.indexOf("]]]]]]") < 0){
					appendAndAdd(chapter, content, s, htmlContent, htmlS, ids);
				}
			}else{
				if(content.indexOf("[[[[[[") >= 0 ){
					appendAndAdd(chapter, content, s, htmlContent, htmlS, ids);
				}
			}
		}else{
			if(anchor2 != null && anchor2 != ""){
				if(content.indexOf("]]]]]]") < 0){
					appendAndAdd(chapter, content, s, htmlContent, htmlS, ids);
				}
			}else{
				appendAndAdd(chapter, content, s, htmlContent, htmlS, ids);
			}
		}
	}//2017年4月15日改  改之前没有判断
	
	/**
	 * 追加段落内容到content，段落数加一
	 * @param chapter
	 * @param content
	 * @param s
	 * @param htmlContent
	 * @param htmlS
	 * @param ids
	 */
	private static void appendAndAdd(ChapterEntity chapter, StringBuilder content, String s, StringBuilder htmlContent, String htmlS,  StringBuilder ids){
		chapter.setParagraph(chapter.getParagraph()+1);
		content.append(s).append("~~");
		String id = ids.substring(ids.lastIndexOf(",")+1);//2017年4月17日加
		htmlContent.append("<div id=\""+id+"\" pid=\""+id+"\">").append(htmlS).append("</div>");
		int intId = Integer.parseInt(id);
		intId++;
		ids.append(","+intId);
	}//2017年4月17日加
	
	/**
	 * 除去所有html标签
	 * @param htmlStr
	 * @return
	 */
	private static String removeHTMLLabel2(String htmlStr){
		String regEx_html = "<.+?>";
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        return htmlStr.trim(); // 返回文本字符串
	}
	
	/**
	 * 除去给定的标签
	 * @param htmlStr
	 * @return
	 */
	private static String removeThisLabel(String htmlStr,String removeLabel){
		//String regEx_html = "</?"+ removeLabel +"*[^>]+>";
		String regEx_html = "</?"+ removeLabel +"[^>]*>";
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 将P标签换成</br>
	 * @param htmlStr
	 * @return
	 */
	private static String pToBr(String htmlStr){
		String regEx_html = "<p[^>]*>";
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        htmlStr = htmlStr.trim();
		String regEx_html2 = "</p[^>]*>";
		Pattern p_html2 = Pattern.compile(regEx_html2, Pattern.CASE_INSENSITIVE);
        Matcher m_html2 = p_html2.matcher(htmlStr);
        htmlStr = m_html2.replaceAll("</br>"); // 过滤html标签
        return htmlStr.trim(); // 返回文本字符串
	}
	
		/**
		 * 去掉字符串开始和结束处的标记内容
		 * @param content
		 * @param preMark 
		 * @param tailMark
		 * @return
		 */
	   private static String cutMark(StringBuilder content,String preMark,String tailMark){
		   	int cutNum1 = 0;	
		   	if(preMark != null && preMark != ""){
			   cutNum1 = preMark.length();
		   	}
		   	int cutNum2 = 0;	
		   	if(tailMark != null && tailMark != ""){
		   		cutNum2 = tailMark.length();
		   	}
			if(content != null && content.indexOf(preMark) >= 0){
				String contentCutPre = content.toString();
				if(content.length() >= cutNum1){
					contentCutPre = content.substring(cutNum1);
				}
				if(contentCutPre != null && contentCutPre.length()>0 && contentCutPre.indexOf(tailMark) >= 0){// 含preMark,也含tailMark
					int length = contentCutPre.length();
					String contentCut = contentCutPre;
					if(length >= cutNum2){						
						contentCut = contentCutPre.substring(0, length -cutNum2);
					}
					return contentCut;
				}else{// 含preMark,不含tailMark
					return contentCutPre;
				}
			}else{
				if(content != null  &&  content.length() > 0  &&  content.indexOf(tailMark) >= 0){ //不含preMark,含tailMark
					int length = content.length();
					String contentCutTail = content.toString();
					if(length >= cutNum2){						
						contentCutTail = content.substring(0, length -cutNum2);
					}
					return contentCutTail;
				}else{ //不含preMark,也不含tailMark
					return content.toString();
				}			
			}
	   }//2017年4月15日 增
		   

	
	/**
	 * 增加段落html
	 * @param s
	 * @param pid
	 * @return
	 */
	public static String addParagraphHtml(String s, int pid){
		StringBuilder content = new StringBuilder("<div class='");
		content.append(pid).append("' pid='").append(pid).append("'>");
		int i=0;
		char[] cary = s.trim().toCharArray();
		for (int j=0; j<cary.length; j++) {
			i++;
			content.append("<span parentid='").append(pid).append("' class='wchar' wordoffset='").append(i).append("'>");
			
			if(cary[j]=='<'){
				String htmlContent = parseHtml(s , j);
				j=j+htmlContent.length();
				content.append(htmlContent);
			}else{
				content.append(cary[j]);
			}
			content.append("</span>");
		}
		content.append("</div>");
		return content.toString();
	}
	
	/**
	 * 获得html标签中内容
	 * @param s	 待解析字符串
	 * @param j html标签开始的位置
	 * @return
	 */
	private static String parseHtml(String s , int j){
		String tempHtml = s;
		if(s != null  &&  s.length() >= j){			
			tempHtml = s.substring(j);
		}
		if(tempHtml != null && tempHtml.contains("<img") && !tempHtml.contains("</img>")){
			return tempHtml.substring(0,tempHtml.indexOf(">")+1);
		}
		//获得html标签名
		String htmlTag = s;
		if(s != null && s.length() >= j+1  && s.length() >= (tempHtml.indexOf(">")+j)  &&  tempHtml.indexOf(">") >= 1){
			htmlTag = s.substring(j+1, tempHtml.indexOf(">")+j);
		}
		//判断标签是否为：<p/>这种格式，如果是，返回标签
		if(htmlTag.indexOf("/")>-1){
			return new StringBuilder("<").append(htmlTag).append(">").toString();
		}
		//判断标签中是否有属性
		if(htmlTag != null && htmlTag.indexOf(" ")>-1){
			htmlTag = htmlTag.substring(0, htmlTag.indexOf(" "));
		}
		//得到标签内容
		String htmlContent = s;
		if(s != null  &&  s.length() >= j && tempHtml != null && htmlTag != null){			
			htmlContent = s.substring(j, tempHtml.indexOf("</"+htmlTag+">")+3+htmlTag.length()+j);
		}
		return htmlContent;
	}
	
    /**  
      * 过滤指定标签  
      * @param str  
      * @param tag   指定标签  
      * @return String  
      */  
    private static String filterHtmlTag(String str, String tag) {   
        String regxp = "</?\\s*" + tag + "\\s*([^>]*)\\s*>";   
        Pattern pattern = Pattern.compile(regxp);   
        Matcher matcher = pattern.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result1 = matcher.find();   
        while (result1) {   
           matcher.appendReplacement(sb, "");   
           result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }
    
    /**  
     * 过滤指定 自闭和标签 (如：<a />)
     * @param str  
     * @param tag   指定标签  
     * @return String  
     */  
   private static String filterHtmlTag2(String str, String tag) {   
       String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*/>";   
       Pattern pattern = Pattern.compile(regxp);   
       Matcher matcher = pattern.matcher(str);   
       StringBuffer sb = new StringBuffer();   
       boolean result1 = matcher.find();   
       while (result1) {   
          matcher.appendReplacement(sb, "");   
          result1 = matcher.find();   
       }   
       matcher.appendTail(sb);   
       return sb.toString();   
   }
   
   /**
    * 
    * @param parser
    * @param url
    * @param encoding
    * @throws IOException 
    * @throws ParserException 
    */
   private static void dealParser(Parser parser,String url, String encoding) throws IOException, ParserException{
	   String indexContent = urlToString(url, encoding);
       parser.setInputHTML(indexContent);
   }
   
   /**
    * 将指定url的页面解析成指定编码的字符串
    * @param url
    * @param encoding
    * @return
    * @throws IOException
    */
   private static String urlToString(String url, String encoding) throws IOException{
       InputStream inputStream = null;
       String indexContent;
       byte[] htmlByte = null;
       URL u = new URL(url); 
       try{
	       HttpURLConnection con = (HttpURLConnection) u.openConnection();
	       con.setConnectTimeout(10 * 1000);
	       inputStream = con.getInputStream();
	       htmlByte = FileUtil.getBytes(inputStream);
	       indexContent = new String(htmlByte, encoding);
       }finally{
       	if(inputStream != null){
       		inputStream.close();
       	}
       }
       return indexContent;
   }
   
   /**
    * 判断url对应的页面内容是否为空，为空的情况下抛一个异常
    * @param document
    * @param url
    */
   private static void isOrNotNull(Document document,String url){
		Element body = document.body();
		String bodyString = body.html();
		String bodytext = body.text();
		if(!bodytext.trim().matches(".*[(\u4e00-\u9fa5)a-zA-Z0-9]+.*")  &&  !existThisLabel(bodyString, "<img")  &&  !existThisLabel(bodyString, "<embed")){
			throw new RuntimeException("[url为:\"" + url + "\"的章节 内容为空!]");
		}
   }
   
   /**
    * 判断字符中是否包含指定内容
    * @param bodyString 要判断的字符串
    * @param labelName 是否包含该内容
    * @return
    */
   private static boolean existThisLabel(String bodyString, String labelName){
	   boolean exist = true;
	   if(bodyString.toLowerCase().indexOf(labelName.toLowerCase()) == -1){
		   exist = false;
	   }
	   return exist;
   }
   
   /**
    * 当锚点一不为空，但在文章中找不见时，添加开始标记，从文章开头处截取
    * @param document
    * @param anchor1
    * @param content
    * @param htmlContent
    */
   private static void addStartMarkWhileFileNoAnchor1(Document document, String anchor1, StringBuilder content, StringBuilder htmlContent){
		if(anchor1 != null && anchor1 != ""){				
			String html = document.html();
			if(html.toLowerCase().indexOf(("id=\""+anchor1+"\"").toLowerCase()) < 0  &&  html.toLowerCase().indexOf(("id=\'"+anchor1+"\'").toLowerCase()) < 0){
				content.append("[[[[[[");
				htmlContent.append("[[[[[[");//2017年4月17日加
			}
		}//2017年4月17日增	
   }
   
   /**
    * 这个方法添加开始和结束标记，实现截取两个锚点之间的段落
    * @param tag
    * @param anchor1
    * @param anchor2
    * @param content
    * @param htmlContent
    */
   private static void addMarkOfStartAndEnd(Tag tag, String anchor1, String anchor2, StringBuilder content, StringBuilder htmlContent){
   	if( anchor1 != null && anchor1 != "" && anchor1.equalsIgnoreCase(tag.getAttribute("id")) &&  content.indexOf("[[[[[[") < 0){
		content.append("[[[[[[");
		htmlContent.append("[[[[[[");//2017年4月17日加 
	}//2017年4月15日增  ，2017年4月17日增加条件 && content.indexOf("[[[[[[") < 0 ,2017年4月19日改位置（原来在下面的if判断里）
	if( anchor2 != null && anchor2 != "" && anchor2.equalsIgnoreCase(tag.getAttribute("id")) ){
		content.append("]]]]]]");
		htmlContent.append("]]]]]]");//2017年4月17日加
	}//2017年4月15日增  2017年4月19日改位置（原来在下面的if判断里）
   }
   
   /**
    * 将flash的相对路径改成绝对路径，返回字符串
    * @return
    */
   private static String addPreForFlashSrc(String nHtml, String url){
		StringBuilder s3 = new StringBuilder();
		if(nHtml != null){			
			int index = nHtml.indexOf("src=\"");
			if(index > 0){		                					
				s3.append(nHtml.substring(0, index+5)).append(url.substring(0, url.lastIndexOf("/")+1)).append(nHtml.substring(index+5));
			}
			int index2 = nHtml.indexOf("src=\'");
			if(index2 > 0){		                					
				s3.append(nHtml.substring(0, index2+5)).append(url.substring(0, url.lastIndexOf("/")+1)).append(nHtml.substring(index2+5));
			}
		}
		return s3.toString();
   }
   
   /**
    * 将img的相对路径改成绝对路径，设置宽和高，路径不对时抛异常
    * @return
    */
   private static String[] dealImg(Tag tag,String url,Node n,String id){
	   	String width = "";
	   	String height = "";
	   	String s = "";
	   	String htmlS = "";	   	
		ImageTag imgTag = (ImageTag) n;
		s=tag.getChildren().toHtml();
		String src = imgTag.getAttribute("src");
		if(null != src && "" != src && src.toLowerCase().indexOf("http://") < 0){
			if(url != null){				
				imgTag.setAttribute("src", url.substring(0, url.lastIndexOf("/")+1) + src);
			}
			String imgUrl = imgTag.getAttribute("src");
			try {
				Image img = Image.getInstance(new URL(imgUrl));
				width = String.valueOf((int)img.getWidth());
				height = String.valueOf((int)img.getHeight());
			} catch (FileNotFoundException e) {
				throw new RuntimeException("[url为：\"" + imgUrl +"\"的图片 找不到!]");
			} catch (BadElementException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			s = imgTag.toHtml();
			StringBuilder s2 = new StringBuilder();
			if(s.length() > 0 && s.indexOf("/>") > -1){				
				s2.append(s.substring(0, s.length()-2)).append(" style=\"width:"+width+"px;height:"+height+"px;\"/>");
			}else if(s.length() > 0 && s.indexOf(">") > -1){
				s2.append(s.substring(0, s.length()-1)).append(" style=\"width:"+width+"px;height:"+height+"px;\"/>");
			}
			s = s2.toString();
			//2017年4月17日加
			StringBuilder s3 = new StringBuilder();
			if(s.length() >= 4){				
				s3.append("<span parentid=\""+id+"\" wordoffset=\"1\" class=\"wchar\" style=\"display:inline-block;text-indent:0em;\"><img parentid=\""+id+"\" ").append(s.substring(4)).append("</span>");
			}
			htmlS = s3.toString();
		}
		String[] sAndHtmlS = new String[2];
		sAndHtmlS[0] = s;
		sAndHtmlS[1] = htmlS;
		return sAndHtmlS;
   }
   
   /**
    * 将img的相对路径改成绝对路径，设置宽和高，路径不对时抛异常
    * @param nHtml
    * @param url
    * @param id
    * @return
    */
   private static String[] dealImg2(String nHtml, String url, String id){
	   	String s = "";
	   	String htmlS = "";	
		StringBuilder s3 = new StringBuilder();
		int indexHttp = nHtml.indexOf("http://");
		int index = nHtml.indexOf("src=\"");
		if(index > 0 && indexHttp ==-1){		                					
			s3.append(nHtml.substring(0, index+5)).append(url.substring(0, url.lastIndexOf("/")+1)).append(nHtml.substring(index+5));
		}
		int index2 = nHtml.indexOf("src=\'");
		if(index2 > 0 && indexHttp ==-1){		                					
			s3.append(nHtml.substring(0, index2+5)).append(url.substring(0, url.lastIndexOf("/")+1)).append(nHtml.substring(index2+5));
		}
		s = s3.toString();
		String cutSrcPre = s.substring(s.indexOf("src=")+5);
		String width = "0";
		String height = "0";
		try {
			Image img = null;
			if(index > 0){                								
				img = Image.getInstance(new URL(cutSrcPre.substring(0, cutSrcPre.indexOf("\""))));
			}
			if(index2 > 0){                								
				img = Image.getInstance(new URL(cutSrcPre.substring(0, cutSrcPre.indexOf("\'"))));
			}
			width = String.valueOf((int)img.getWidth());
			height = String.valueOf((int)img.getHeight());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("[url为：\"" + cutSrcPre.substring(0, cutSrcPre.indexOf("\"")) +"\"的图片 找不到!]");
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}                 						
		StringBuilder s2 = new StringBuilder();
		if(s.length() > 0 && s.indexOf("/>") > -1){                							
			s2.append(s.substring(0, s.length()-2)).append(" style=\"width:"+width+"px;height:"+height+"px;\"/>");
		}else{
			s2.append(s.substring(0, s.length()-1)).append(" style=\"width:"+width+"px;height:"+height+"px;\"/>");
		}
		s = s2.toString();
		//2017年4月17日加
		StringBuilder s5 = new StringBuilder();
		s5.append("<span parentid=\""+id+"\" wordoffset=\"1\" class=\"wchar\" style=\"display:inline-block;text-indent:0em;\"><img parentid=\""+id+"\" ").append(s.substring(4)).append("</span>");
		htmlS = s5.toString();
		String[] sAndHtmlS = new String[2];
		sAndHtmlS[0] = s;
		sAndHtmlS[1] = htmlS;
		return sAndHtmlS;
   }
   
	/**
	 * 在字符串中除了HTML标签之外的字符外面套一个span标签，并加数字序号。保留HTML标签
	 * @param str
	 * @return
	 */
	private static String addSpanAndNumReserveHtml(String str,String id){
		List<String> list = new ArrayList<String>();
		cutStringToList(list,str);
		int num = 1;
		StringBuilder content = new StringBuilder();
		//遍历List集合中的元素。如果是普通字符串，在每一个字符外面套一个span标签；如果是HTML标签，直接拼接到content。
		if(list != null && list.size() > 0){			
			for(String s:list){
				int indexLeft = s.indexOf("<");
				int indexRight = s.indexOf(">");
				if(indexLeft > -1  &&  indexRight > -1  && indexRight > indexLeft){
					if(s.matches("<[^/(br)]+.*\\s*/\\s*>")){
						int index = s.indexOf("/");
						StringBuilder addParentId = new StringBuilder();
						addParentId.append(s.substring(0, index));
						addParentId.append(" parentid=\""+id+"\"/>");
						content.append(addParentId);
					}else if(s.matches("<[^/(br)].*")){
						int index = s.indexOf(">");
						StringBuilder addParentId = new StringBuilder();
						addParentId.append(s.substring(0, index));
						addParentId.append(" parentid=\""+id+"\">");
						content.append(addParentId);
					}else{						
						content.append(s);
					}
				}else{
					boolean appendStartLabel = true;//是否拼接span的开始标签
					boolean appendEndLabel = true;//是否拼接span的结束标签
					for(int i=0; i<s.length(); i++){
						if(appendStartLabel){	
							content.append("<span parentid=\""+id+"\" class=\"wchar\" wordoffset=\""+(num++)+"\">");
						}
						content.append(s.charAt(i));
						if(String.valueOf(s.charAt(i)).equalsIgnoreCase("&")){
							appendEndLabel = false;
						}else if(String.valueOf(s.charAt(i)).equalsIgnoreCase(";")){
							appendEndLabel = true;
						}
						if(appendEndLabel){							
							content.append("</span>");
						}
						if(String.valueOf(s.charAt(i)).equalsIgnoreCase("&")){
							appendStartLabel = false;
						}
					}
				}
			}
		}
		return content.toString();
	}
	
	/**
	 * 将字符串根据HTML标签分割，并将每个片段依次存入List中
	 * @param list
	 * @param str
	 * @return
	 */
	private static void cutStringToList(List<String> list,String str){
		char[] chars = str.toCharArray();
		StringBuffer sb = new StringBuffer(5000);
		String s;
		for(char c : chars){
			if(c == '<'){
				s = sb.toString();
				if(s.trim().length()>0){
					list.add(s);
				}
				sb.setLength(0);
			}
			sb.append(c);
			if(c == '>'){
				s = sb.toString();
				if(s.trim().length()>0){
					list.add(s);
				}
				sb.setLength(0);
			}
		}
		if(sb.length()>0){
			s = sb.toString();
			if(s.trim().length()>0){
				list.add(s);
			}
		}
	}
   
}
