package com.china.ciic.bookgenerate.handle.entity;

public class ChapterEntity {
	
	public ChapterEntity(){
		paragraph = 0;
	}
	
	private String content;
	private String htmlContent;
	private int paragraph;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParagraph() {
		return paragraph;
	}
	public void setParagraph(int paragraph) {
		this.paragraph = paragraph;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
}
