package com.china.ciic.bookgenerate.common.util;

import com.china.ciic.bookgenerate.handle.entity.UrlEntity;
import com.google.common.base.Optional;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLHandle {

	/**
	 * 获得电子书地址
	 * @param fileUrl
	 * @return
	 */
	public static UrlEntity getBookUrl(String fileUrl){
		SAXBuilder builder=new SAXBuilder(false);
		Document document= null;
		UrlEntity url = new UrlEntity();
		List<String> list = new ArrayList<String>();
		try {
			document=builder.build(new URL(fileUrl));
			Element book=document.getRootElement();
			Element xhtml = book.getChild("xhtml");
			Iterator<?> chapters = xhtml.getChildren().iterator();
			while(chapters.hasNext()){
				Element row = (Element)chapters.next();
				Optional<String> idOptional=Optional.fromNullable(row.getAttributeValue("id"));
				Optional<String> textOptional=Optional.fromNullable(row.getText()); 

				if(idOptional.isPresent() && textOptional.isPresent()){
					if(textOptional.get().contains("b_content")){
						url.setDirectory(textOptional.get());
					}else if(textOptional.get().contains("chapter")){
						list.add(textOptional.get());
					}else if(textOptional.get().contains("cover")){
						url.setCover(textOptional.get());
					}else if(textOptional.get().contains("content")){
						url.setChapters(textOptional.get());
					}
				}
			}
			url.setChapterList(list);
		} catch (JDOMException e) { 
			e.printStackTrace();   
		} catch (IOException e) {   
			e.printStackTrace();   
		} 
		return url;
	}

}
