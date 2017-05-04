package com.china.ciic.bookgenerate.handle.decode;

public class ContentDecorator extends DecodeComponent{
	
	private DecodeComponent component;
	
	public ContentDecorator(DecodeComponent component){
		this.component = component;
	}

	@Override
	public String operation() {
		return component.operation();
	}

}
