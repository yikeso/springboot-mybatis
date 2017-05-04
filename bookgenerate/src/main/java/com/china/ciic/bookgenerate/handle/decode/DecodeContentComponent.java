package com.china.ciic.bookgenerate.handle.decode;

public class DecodeContentComponent extends DecodeComponent {
	
	private String content;
	
	public DecodeContentComponent(String content){
		this.content = content;
	}

	@Override
	public String operation() {
		return content;
	}

}
