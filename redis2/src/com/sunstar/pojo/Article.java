package com.sunstar.pojo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{
	/**
	 * 实体类实现序列化接口
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date time;
	private String name;
	private String contentValue;
	
	public Article() {
		super();
	}

	public Article(String id, Date time, String name, String contentValue) {
		super();
		this.id = id;
		this.time = time;
		this.name = name;
		this.contentValue = contentValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentValue() {
		return contentValue;
	}

	public void setContentValue(String contentValue) {
		this.contentValue = contentValue;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", time=" + time + ", name=" + name
				+ ", contentValue=" + contentValue + "]";
	}
	
    //序列化 
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        
        return null;
    }
    
    

}
