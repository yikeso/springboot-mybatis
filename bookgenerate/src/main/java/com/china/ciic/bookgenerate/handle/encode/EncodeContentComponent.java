package com.china.ciic.bookgenerate.handle.encode;

import com.china.ciic.bookgenerate.common.util.HtmlHandle;
import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;
import com.china.ciic.bookgenerate.model.json.Directory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

public class EncodeContentComponent extends EncodeComponent{
	
	private String chapter;
	private String encoding;
	private String anchor1;
	private String anchor2;
	private Directory dir;
	private int type;
	private Environment setSource;
	
//	public EncodeContentComponent(String chapter, String encoding, int type){
//		this.chapter = chapter;
//		this.encoding = encoding;
//		this.type = type;
//	}

	public EncodeContentComponent(String chapter, String encoding, int type,
								  String anchor1, String anchor2, Directory dir,
								  Environment setSource){
		this.chapter = chapter;//章节内容
		this.encoding = encoding;//字符集
		this.anchor1 = anchor1;//开始锚点
		this.anchor2 = anchor2;//结束锚点
		this.dir = dir;//对应章节标题
		this.type = type;//资源类型6电子书，7article
		this.setSource = setSource;//配置文件
	}

	public ChapterEntity operation() throws Exception {
		ChapterEntity entity = null;
		String createHtml = setSource.getProperty("CREATE_HTML_CONTENT","true");
		boolean isCreate = "true".equalsIgnoreCase(createHtml.trim())?true:false;
		if(type==6){
			entity = HtmlHandle.getBookContentByChapter("http://resource.gbxx123.com/"+chapter,
					encoding,anchor1,anchor2, isCreate);
			dir.setParagraphNum(entity.getParagraph());
		}else if(type==7){
			entity = HtmlHandle.getArticleContentByChapter("http://resource.gbxx123.com/"+chapter,
					encoding,anchor1,anchor2, isCreate);
			dir.setParagraphNum(entity.getParagraph());
		}
		//获取该目录及其子目录锚点元素的父元素的pid属性
		if(dir.getAnchor() != null && entity != null ) {
			String html = entity.getHtmlContent();
			String anchor = null;
			if (html != null && html.length() > 0) {
				Document doc = Jsoup.parse(html);
				Element e = doc.getElementById(dir.getAnchor());
				if(e != null) {
					dir.setPid(e.attr("pid"));
				}
				List<Directory> lsit = new ArrayList<Directory>(100);
				getAllChildren(dir,lsit);
				for(Directory d:lsit){
					if(d.isNewPage()){
						//新页面跳出
						break;
					}
					anchor = d.getAnchor();
					if(anchor != null) {
						e = doc.getElementById(anchor);
						if(e != null) {
							d.setPid(e.attr("pid"));
						}
					}
				}
			}
		}
		return entity;
	}

    /**
     * 获得改标题的所有子孙标题
     * @param dir
     * @param list
     */
	private void getAllChildren(Directory dir,List<Directory> list){
		if(dir == null){
			return;
		}
		List<Directory> sub = dir.getSubDirectory();
		if(sub != null && sub.size() >0 ){
			for (Directory d:sub){
				list.add(d);
				getAllChildren(d,list);
			}
		}
	}
}
