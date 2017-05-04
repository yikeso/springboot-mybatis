package com.china.ciic.bookgenerate.common.util;

import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import org.apache.commons.lang3.StringEscapeUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kakasun
 * 2017/4/19
 */
public class HtmlHandle {
    public static final List<String> elementName = new ArrayList<String>();
    private static final Logger log = LoggerFactory.getLogger(HtmlHandle.class);
/*

    public static final Map<String,String> escapeCharacter;
*/
   static {
   /*
       escapeCharacter = new HashMap<String, String>();
       escapeCharacter.put("&nbsp;","\\s");
       escapeCharacter.put("&lt;","<");
       escapeCharacter.put("&gt;",">");
       escapeCharacter.put("&amp;","&");
       escapeCharacter.put("&quot;","\"");
       escapeCharacter.put("&apos;","'");
       escapeCharacter.put("&cent;","￠");
       escapeCharacter.put("&pound;","£");
       escapeCharacter.put("&yen;","¥");
       escapeCharacter.put("&sect;","§");
       escapeCharacter.put("&copy;","©");
       escapeCharacter.put("&reg;","®");

       escapeCharacter.put("&times;","×");
       escapeCharacter.put("&divide;","÷");
*/

        elementName.add("address");//定义地址
        elementName.add("caption");//定义表格标题
        elementName.add("dd");//定义列表中定义条目
        elementName.add("div");//定义文档中的分区或节
        elementName.add("dl");//定义列表
        elementName.add("dt");//定义列表中的项目
        elementName.add("fieldset");//定义一个框架集
        elementName.add("form");//创建 HTML 表单
        elementName.add("h1");//定义最大的标题
        elementName.add("h2");//定义副标题
        elementName.add("h3");//定义副标题
        elementName.add("h4");//定义副标题
        elementName.add("h5");//定义副标题
        elementName.add("h6");//定义副标题
        elementName.add("hr");//定义水平线
        elementName.add("fieldset");//元素定义标题
        elementName.add("noframes");//为那些不支持框架的浏览器显示文本，于 frameset 元素内部
        elementName.add("noscript");//定义在脚本未被执行时的替代内容
        elementName.add("ol");//定义有序列表
        elementName.add("ul");//定义无序列表
        elementName.add("li");
        elementName.add("pre");//定义预格式化的文本
        elementName.add("table");//标签定义 HTML 表格
        elementName.add("thead");
        elementName.add("tbody");
        elementName.add("tr");
        elementName.add("td");
    }

    /**
     * 解析目录html获取其中的封面图片资源路径
     * @param fileUrl
     * @return
     */
    public static String getCoverImageUrl(String fileUrl) {
        try {
            Document html = Jsoup.parse(new URL(fileUrl),10000);
            Element bodyBg = html.getElementById("BodyBg");
            Elements imgs = bodyBg == null?html.getElementsByTag("img"):bodyBg.getElementsByTag("img");
            String src = imgs.get(0).attr("src");
            File dir = new File(fileUrl).getParentFile();
            if(src.startsWith("../")){
                Pattern pattern = Pattern.compile("\\.\\./");
                Matcher matcher = pattern.matcher(src);
                int num = 0;
                while (matcher.find()){
                    num++;
                }
                for(;num>0;num--){
                    dir = dir.getParentFile();
                }
                src = matcher.replaceAll("");
            }
            return dir.getPath() + "/" + src;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对章节内容进行处理
     * 过滤部分标签压缩
     * 以及给每个文章字符添加标签
     * @param url 章节资源路径
     * @param encoding 文件字符集
     * @param anchor1 当前章节锚点
     * @param anchor2 下一章节锚点
     * @return
     * @throws Exception
     */
    public static ChapterEntity getArticleContentByChapter(String url, String encoding,
                                                           String anchor1, String anchor2,
                                                           boolean createHtmlContent) throws Exception {
        return makeContentByUrl(url,encoding,anchor1,anchor2,createHtmlContent);
    }

    /**
     * 对章节内容进行处理
     * 过滤部分标签压缩
     * 以及给每个文章字符添加标签
     * @param url
     * @param encoding
     * @param anchor1
     * @param anchor2
     * @return
     * @throws Exception
     */
    public static ChapterEntity getBookContentByChapter(String url, String encoding,
                                                        String anchor1, String anchor2,
                                                        boolean createHtmlContent) throws Exception{

        return makeContentByUrl(url,encoding,anchor1,anchor2,createHtmlContent);
    }

    /**
     * 对html进行处理
     * @param url
     * @param encoding
     * @param anchor1
     * @param anchor2
     * @param createHtmlContent
     * @return
     * @throws Exception
     */
    public static ChapterEntity makeContentByUrl(String url, String encoding,
                                                 String anchor1, String anchor2,
                                                 boolean createHtmlContent) throws Exception{
        String html = getStringByUrl(new URL(url), encoding);//获取页面
        if(html == null || html.trim().length() == 0){
            throw new RuntimeException(url + " 页面内容为空");
        }
        //通过锚点获取两个锚点之间的节点
        html = filterTagByAnchor(html,anchor1,anchor2);
        if(html.length() == 0){
            throw new RuntimeException("url为：" + url + " 的页面无章节内容");
        }
        //通过标签拆分字符串，只保留，文本、h标签和多媒体标签
        List<String> list = cutStringByTag(html);
        ChapterEntity chapter = new ChapterEntity();
        chapter.setContent(tagListToTxt(list));
		chapter.setParagraph(list.size());
		if(createHtmlContent){
			list = addTagToText(list,url);
            chapter.setHtmlContent(tagListToHtml(list));
		}
        return chapter;
    }

    /**
     * @param url
     * @param encoding 该页面的字符集
     * @return
     */
    public static String getStringByUrl(URL url, String encoding) throws IOException {
        InputStream inputStream = null;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(10 * 1000);
            inputStream = con.getInputStream();
            byte[] htmlByte = FileUtil.getBytes(inputStream);
            return new String(htmlByte, encoding);
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
    }

    /**
     * 根据标签分解字符串并过滤部分标签
     * @param html
     * @return
     */
    private static List<String> cutStringByTag(String html) {
        List<String> list = new ArrayList<String>(200);
        StringBuffer sb = new StringBuffer(html);
        int ltIndex = sb.indexOf("<");
        int start;
        int gtIndex;
        String str;
        //拆分html
        while(ltIndex != -1){
            gtIndex = sb.indexOf(">",ltIndex) + 1;
            if(gtIndex < 1){
                break;
            }
            str = new String("");
            start = ltIndex;
            for (;start < gtIndex;start++){
                str += sb.charAt(start);
            }
            str = str.trim();
            if(str.startsWith("<div") || str.startsWith("</div")//移除div和p标签
                   || str.startsWith("<p") || str.startsWith("</p")
                   || str.matches("^</img.*")
                   || str.matches("^</video.*")
                   || str.matches("^</audio.*")
                   || str.matches("^</embed.*")){
                sb.delete(ltIndex,gtIndex);
                str = new String("");
                for (start = 0;start < ltIndex;start++){
                    str += sb.charAt(start);
                }
                str = str.trim();
                if(str.length() > 0) {
                    list.add(str);
                }
                sb.delete(0,ltIndex);
                start = 0;
            }else if(str.matches("^<img.*")
                    || str.matches("^<video.*")
                    || str.matches("^<audio.*")
                    || str.matches("^<embed.*")){
                if(!str.endsWith("/>")){
                    str = str.substring(0,str.length()-1)+"/>";
                }
                String ss = new String(str);
                str = new String("");
                for (start = 0;start < ltIndex;start++){
                    str += sb.charAt(start);
                }
                str = str.trim();
                if(str.length() > 0) {
                    list.add(str);
                }
                list.add(ss);
                sb.delete(0,gtIndex);
                start = 0;
            }else {
                start = gtIndex;
            }
            ltIndex = sb.indexOf("<",start);
        }
        if(sb.length() > 0){
            str = sb.toString().trim();
            if(str.length() > 0){
                list.add(str);
            }
        }
        List<String> result = new ArrayList<String>(200);
        sb.setLength(0);
        Deque<String> deque = new LinkedList<String>();
        String tager;
        //移除span标签，拼接被拆分开的成对块元素标签
        for(String s:list){
            s = removerSpan(s);//移除span标签
            s = s.trim();
            if(s.length() == 0){
                continue;
            }
            if(s.startsWith("<")){
                tager = s.substring(0,s.indexOf('>')+1);
                if(!tager.startsWith("</") && !tager.endsWith("/>")
                   && !"<br>".equalsIgnoreCase(tager)){
                    gtIndex = tager.indexOf(' ');
                    int i2;
                    if(gtIndex < 0){
                        gtIndex = tager.indexOf('>');
                    }else if( gtIndex > (i2 = tager.indexOf('>'))){
                        gtIndex = i2;
                    }
                    tager = tager.substring(1,gtIndex);
                    tager = tager.toLowerCase();
                    //只处理成对的块元素，跳过行内元素
                    if(elementName.contains(tager)) {
                        deque.push(tager);
                    }
                }
            }
            if(deque.size() == 0){
                if(sb.length() > 0){
                    str = sb.toString().trim();
                    if(str.length()>0){
                        result.add(str);
                    }
                    sb.setLength(0);
                }
                result.add(s);
            }else {
                sb.append(s);
            }
            if(deque.size() == 0){
                continue;
            }
            if(s.endsWith(">")){
                tager = s.substring(s.lastIndexOf('<'));
                if(tager.startsWith("</")){
                    tager = tager.substring(2,tager.length()-1);
                    if(tager.equalsIgnoreCase(deque.peek())){
                    	deque.pop();
                    }
                }
            }
        }
        if(sb.length() > 0){
            str = sb.toString().trim();
            if(str.length() > 0){
                result.add(str);
            }
        }
        result = filterSpace(result);
        return result;
    }

    /**
     * 过滤html中的冗余空格
     * @param list
     * @return
     */
    private static List<String> filterSpace(List<String> list){
        List<String> result = new ArrayList<String>(50);
        Document doc;
        for(String s:list){
            s = s.replaceAll("\\s{2,}"," ");
            s = s.replaceAll(">\\s+<","><");
            s = s.replaceAll("[\\n\\t\\r]+","").trim();
            doc = Jsoup.parseBodyFragment(s);
            doc.outputSettings().prettyPrint(false);
            s = doc.body().html();
            if(s.length() > 0){
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 给标签的html内容的每个字符添加一个span标签
     * @param docList
     * @return
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws BadElementException 
     */
    private static List<String> addTagToText(List<String> docList,String htmlUrl) throws BadElementException, MalformedURLException, IOException, ParserException {
        List<String> list = new ArrayList<String>(100);
        String dir = htmlUrl.substring(0,htmlUrl.lastIndexOf('/')+1);
        String s;
        Document doc;
        for(int p = 1;p <= docList.size();p++){
            s = docList.get(p-1);
            s = s.trim();
            if(s.length() == 0){
                continue;
            }
            if(s.matches("^<img.*")){
                doc = Jsoup.parseBodyFragment("<div><span></span></div>");
                doc.outputSettings().prettyPrint(false);
                Element div = doc.body().child(0);
            	div.attr("id",String.valueOf(p));
            	div.attr("pid",String.valueOf(p));
            	Element span = div.child(0);
            	span.attr("parentid",String.valueOf(p));
            	span.attr("wordoffset","1");
            	span.addClass("wchar");
            	span.attr("style", "display:inline-block;text-indent:0em;");
            	span.html(s);
            	Element img = span.child(0);
            	String src = img.attr("src");
            	if(src.startsWith("http:/")||src.startsWith("https:/")){
            		img.attr("src", src);            		
            	}else if(src.startsWith("/")){
            		src = dir.substring(0,dir.indexOf('/', dir.indexOf(':')+3)) + src;
            		img.attr("src", src); 
            	}else{
            		src = dir +src;
            		img.attr("src", src);    
            	}
            	img.attr("parentid",String.valueOf(p));
            	img.attr("style", getImgStyle(src));
                list.add(div.outerHtml().replaceAll("\\n",""));
            }else if(s.matches("^<embed.+")
                    ||s.matches("^<video.+")
                    ||s.matches("^<audio.+")){
            	doc = Jsoup.parseBodyFragment("<div><span></span></div>");
                doc.outputSettings().prettyPrint(false);
                Element div = doc.body().child(0);
            	div.attr("id",String.valueOf(p));
            	div.attr("pid",String.valueOf(p));
            	Element span = div.child(0);
            	span.attr("parentid",String.valueOf(p));
            	span.attr("wordoffset","1");
            	span.addClass("wchar");
            	span.attr("style", "display:inline-block;text-indent:0em;");
            	span.html(s);
            	Element midea = span.child(0);
            	String src = midea.attr("src");
            	if(src.startsWith("http:/")||src.startsWith("https:/")){
            		midea.attr("src", src);            		
            	}else if(src.startsWith("/")){
            		src = dir.substring(0,dir.indexOf('/', dir.indexOf(':')+3)) + src;
            		midea.attr("src", src); 
            	}else{
            		src = dir +src;
            		midea.attr("src", src);    
            	}
            	midea.attr("parentid",String.valueOf(p));
                list.add(div.outerHtml().replaceAll("\\n",""));
            }else{
                s = addSpan(s,p);
                String div = "<div id = \""+ p +"\" pid = \""+p+"\">";
                s = div + s + "</div>";
                list.add(s);
            }
        }
        return list;
    }

    /**
     * 获取图片的宽高信息
     * @param url
     * @return
     * @throws BadElementException
     * @throws MalformedURLException
     * @throws IOException
     */
    private static String getImgStyle(String url) throws BadElementException,IOException{
//        log.info("图片路径："+url);
		Image img = Image.getInstance(new URL(url));
		String width = String.valueOf((int)img.getWidth());
		String height = String.valueOf((int)img.getHeight());
		return "width:"+width+"px;height:"+height+"px;";
    }
    
    /**
     * 加span
     * @param html
     * @param p
     * @return
     */
    private static String addSpan(String html, int p) throws ParserException {
        html = html.replaceAll("<!--.*-->","");
        if(html.indexOf('<') != -1) {
            Document doc = Jsoup.parseBodyFragment(html);
            doc.outputSettings().prettyPrint(false);
            Parser parser = new Parser();
            parser.setInputHTML(doc.outerHtml());
            AddParentId visitor = new AddParentId(String.valueOf(p));
            parser.visitAllNodesWith(visitor);
            doc = Jsoup.parseBodyFragment(visitor.getRootHtml());
            doc.outputSettings().prettyPrint(false);
            html = doc.body().html();
        }
        html = html.replaceAll("[\\n\\t\\r]+","").trim();
        StringBuffer sb = new StringBuffer(2000);
        int wordOffset = 1;
        char[] chars = html.toCharArray();
        boolean isAddTag = true;//是否添加标签 开关
        boolean isEscape = false;//是否转义字符 开关
        for(char c:chars){
            if(c == '<'){
                isAddTag = false;
            }
            if(isAddTag){
                if(c == '&'){
                    isEscape = true;
                }
            }
            if(isAddTag){//需要添加标签
                if(isEscape){//是转义字符
                    if(c == '&'){//转义字符开始表示符
                        sb.append("<span ");
                        sb.append("parentid='");
                        sb.append(p);
                        sb.append("' class='wchar' wordoffset='");
                        sb.append(wordOffset++);//字符序号
                        sb.append("'>");
                        sb.append(c);//字符
                    }else if(c == ';'){//结束标识符
                        sb.append(c);//字符
                        sb.append("</span>");
                    }else {
                        sb.append(c);
                    }
                }else {//不是转义字符
                    sb.append("<span parentid='");
                    sb.append(p);//段落
                    sb.append("' class='wchar' wordoffset='");
                    sb.append(wordOffset++);//字符序号
                    sb.append("'>");
                    sb.append(c);//字符
                    sb.append("</span>");
                }
            }else{//不需要添加标签
                sb.append(c);
            }
            if(c == '>'){
                isAddTag = true;
            }
            if(isAddTag){
                if(c == ';'){
                    isEscape = false;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将过滤掉大部分标签的txt拼成一个文本
     * @param list
     * @return
     */
    private static String tagListToTxt(List<String> list){
        StringBuffer sb = new StringBuffer(10000);
        for(String s : list){
            //替换转义字符
        	s = StringEscapeUtils.unescapeHtml4(s);//处理HTML转义字符
            sb.append(s);
            sb.append("~~");
        }
        String s = sb.toString();
        return s;
    }

    /**
     * 将标签拼成字符串
     * @param list
     * @return
     */
    private static String tagListToHtml(List<String> list){
        StringBuffer sb = new StringBuffer(10000);
        for(String s : list){
            s = s.replaceAll("\\n","");
            sb.append(s);
        }
        String s = sb.toString();
        return s;
    }

    /**
     * 通过锚点拿到两个锚点之间的节点
     * @param html
     * @param anchor1
     * @param anchor2
     * @return
     */
    private static String filterTagByAnchor(String html, String anchor1, String anchor2) throws ParserException {
        Document doc = Jsoup.parse(html);
        Element e1;
        Element e2;
        int start = 0;
        int end = 0;
        doc.outputSettings().prettyPrint(false);
        html = doc.body().html();
        if(html == null || html.trim().length() == 0){
            return "";
        }
        if(anchor1 == null){
            return html;
        }else if(anchor2 == null){
            e1 = doc.getElementById(anchor1);
            start = html.indexOf(e1.outerHtml());
            return html.substring(start);
        }else {
            e1 = doc.getElementById(anchor1);
            e2 = doc.getElementById(anchor2);
            start = html.indexOf(e1.outerHtml());
            end = html.indexOf(e2.outerHtml());
            if(end > start){
                return html.substring(start,end);
            }else {
                return "";
            }
        }
    }

    /**
     * 移除span标签
     * @param s
     * @return
     */
    private static String removerSpan(String s){
        StringBuffer sb = new StringBuffer(s);
        int start = sb.indexOf("<span");
        int end = 0;
        while (start != -1){
            end = sb.indexOf(">",start) + 1;
            sb.delete(start,end);
            start = sb.indexOf("<span");
        }
        start = sb.indexOf("</span");
        while (start != -1){
            end = sb.indexOf(">",start) + 1;
            sb.delete(start,end);
            start = sb.indexOf("</span");
        }
        return sb.toString().replaceAll("[\\n\\t\\r]+","").trim();
    }

   /* /**
     * 合并行内元素
     * @param list
     * @return
     *//*
    private static List<String> mergeElement(List<String> list){
        List<String> result = new ArrayList<String>(50);
        StringBuffer sb = new StringBuffer(1000);
        String s;
        String last;
        for(int i = 0;i<list.size();i++){
            s = list.get(i);
            if(s.indexOf('<') == -1){
                if(sb.length() == 0){
                    sb.append(s);
                }else if(sb.charAt(sb.length()-1) == '>'){
                    sb.append(s);
                }else {
                    last = sb.toString().trim();
                    if(last.length() > 0) {
                        result.add(last);
                    }
                    sb.setLength(0);
                    sb.append(s);
                }
            }else if(elementName.contains(s.substring(s.indexOf('<',s.indexOf(' ',s.indexOf('<')))))){
                if(sb.length() == 0){
                    result.add(s);
                }else {
                    last = sb.toString().trim();
                    if(last.length() > 0) {
                        result.add(last);
                    }
                    result.add(s);
                    sb.setLength(0);
                }
            }else {
                sb.append(s);
            }
        }
        return result;
    }*/

    /**
     * 给元素添加parentid属性
     */
    static class AddParentId extends NodeVisitor{
        private String parentId;
        private String rootHtml;
        public AddParentId(String parentId){
            this.parentId = parentId;
        }

        @Override
        public void visitTag(Tag tag) {
            String name = tag.getTagName();
            Node node = tag.getParent();
            node = getBodyNode(node);
            if(node == null){
                return;
            }
            tag.setAttribute("parentid",parentId);
            rootHtml = node.toHtml(false);
        }

        public String getRootHtml() {
            return rootHtml;
        }

        private Node getBodyNode(Node node){
            while(node != null && !node.getText().startsWith("body")){
                node = node.getParent();
            }
            return node;
        }
    }
}
