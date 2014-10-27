package com.qunar.ops.oaengine.result;

public class ActivityInfo {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean current;
	private boolean history;
	private String id;
	private TaskInfo info = new TaskInfo();
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TaskInfo getInfo() {
		return info;
	}
	public void setInfo(TaskInfo info) {
		this.info = info;
	}
	public boolean isHistory() {
		return history;
	}
	public void setHistory(boolean history) {
		this.history = history;
	}
	

}
