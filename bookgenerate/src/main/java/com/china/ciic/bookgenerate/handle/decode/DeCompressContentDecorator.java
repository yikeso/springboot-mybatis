package com.china.ciic.bookgenerate.handle.decode;


import com.china.ciic.bookgenerate.common.util.LZString;

public class DeCompressContentDecorator extends ContentDecorator {

	public DeCompressContentDecorator(DecodeComponent component){
		super(component);
	}
	
	@Override
	public String operation(){
		String content = super.operation();
		return LZString.decompress(content);
	}
}
