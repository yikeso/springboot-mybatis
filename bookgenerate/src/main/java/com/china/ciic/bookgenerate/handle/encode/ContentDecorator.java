package com.china.ciic.bookgenerate.handle.encode;


import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;

public class ContentDecorator extends EncodeComponent{
	
	private EncodeComponent component;
	
	public ContentDecorator(EncodeComponent component){
		this.component = component;
	}

	@Override
	public ChapterEntity operation() throws Exception {
		return component.operation();
	}

}
