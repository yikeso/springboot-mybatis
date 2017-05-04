package com.china.ciic.bookgenerate.handle.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileInfoEntity {

	private String resourceId;
	private List<String> chapters = new ArrayList<String>();
	private String path;
	private int type;
	private Map<String ,String> directoryMap = new HashMap<String, String>();
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public List<String> getChapters() {
		return chapters;
	}
	public void setChapters(List<String> chapters) {
		this.chapters = chapters;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Map<String, String> getDirectoryMap() {
		return directoryMap;
	}
	public void setDirectoryMap(Map<String, String> directoryMap) {
		this.directoryMap = directoryMap;
	}
}
