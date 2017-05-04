package com.china.ciic.bookgenerate.handle.encode;


import com.china.ciic.bookgenerate.common.util.ContentHandle;
import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;

public class EncodeContentDecorator extends ContentDecorator {
	
	public EncodeContentDecorator(EncodeComponent component){
		super(component);
	}
	
	@Override
	public ChapterEntity operation() throws Exception {
		ChapterEntity chapter = super.operation();
		chapter.setContent(ContentHandle.encode(chapter.getContent()));
		if(chapter.getHtmlContent() != null && !"".equals(chapter.getHtmlContent().trim())) {
			chapter.setHtmlContent(ContentHandle.encode(chapter.getHtmlContent()));
		}
		return chapter;
	}
}
