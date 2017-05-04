package com.china.ciic.bookgenerate.handle.encode;


import com.china.ciic.bookgenerate.common.util.LZString;
import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;

public class CompressContentDecorator extends ContentDecorator {

	public CompressContentDecorator(EncodeComponent component){
		super(component);
	}
	
	@Override
	public ChapterEntity operation() throws Exception {
		ChapterEntity chapter = super.operation();
		chapter.setContent(LZString.compress(chapter.getContent().toString()));
		if(chapter.getHtmlContent() != null && !"".equals(chapter.getHtmlContent().trim())) {
			chapter.setHtmlContent(LZString.compress(chapter.getHtmlContent().toString()));
		}
		return chapter;
	}
}
