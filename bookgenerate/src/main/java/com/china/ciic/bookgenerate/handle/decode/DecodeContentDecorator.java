package com.china.ciic.bookgenerate.handle.decode;


import com.china.ciic.bookgenerate.common.util.ContentHandle;

public class DecodeContentDecorator extends ContentDecorator {
	
	public DecodeContentDecorator(DecodeComponent component){
		super(component);
	}
	
	@Override
	public String operation() {
		String content = super.operation();
		return ContentHandle.decode(content);
	}
}
