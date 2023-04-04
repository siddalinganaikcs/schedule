package com.jsp.medlife.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jsp.medlife.Model.Entities.User;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFountExcepyionTwo   extends RuntimeException
{
	private  static final long serialVersionUID = 1L;
	private String resourcename;
	private String fildname;
	private Object fieldValue;
	private Object fieldvalue;
	
	public ResourceNotFountExcepyionTwo(String resourcename, String fildname, Object fieldValue,Object fieldvalue) {
//		super(resourcename+": INVALED EMAIL OR PASSWORD "+""+fildname+":"+fieldValue+" "+fieldvalue);
		super(resourcename+":INVALED EMAIL OR PASSWORD");
		this.resourcename = resourcename;
		this.fildname = fildname;
		this.fieldValue = fieldValue;
		this.fieldValue = fieldValue;
	}
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getFildname() {
		return fildname;
	}
	public void setFildname(String fildname) {
		this.fildname = fildname;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Object getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(Object fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	
	
	
	
	
	
}
