package com.china.ciic.bookgenerate.handle.entity;

import java.util.ArrayList;
import java.util.List;

public class UrlEntity {

	private List<String> chapterList = new ArrayList<String>();
	private String directory;
	private String cover;
	private String chapters;
	public List<String> getChapterList() {
		return chapterList;
	}
	public void setChapterList(List<String> chapterList) {
		this.chapterList = chapterList;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getChapters() {
		return chapters;
	}
	public void setChapters(String chapters) {
		this.chapters = chapters;
	}
}
