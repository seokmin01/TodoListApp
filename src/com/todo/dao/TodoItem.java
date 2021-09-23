package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;


    public TodoItem(String title, String desc) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
        this.title=title;
        this.desc=desc;
        this.current_date=sdf.format(new Date());
    }
    
    public TodoItem(String title, String desc, String date) {
    	this.title=title;
        this.desc=desc;
        this.current_date=date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

	@Override
	public String toString() {
		return "TodoItem [title=" + title + ", desc=" + desc + ", current_date=" + current_date + "]";
	}
    
	public String toSaveString() {
		return title + "##" + desc + "##" + current_date + "\n";
	}
}
