package com.jsp.medlife.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFountException  extends RuntimeException
{

	/**
	 * 
	 */
	private  static final long serialVersionUID = 1L;
	private Object resourcename;
	private Object fildname;
	private Object fieldValue;
	
	
	public ResourceNotFountException(String resourcename, String fildname, Object fieldValue) {
		super(resourcename+"IS EXIST PLS TRY WITH DIFFRENT EMAIL AND NAME");
		this.resourcename = resourcename;
		this.fildname = fildname;
		this.fieldValue = fieldValue;
	}


	public Object getResourcename() {
		return resourcename;
	}


	public void setResourcename(Object resourcename) {
		this.resourcename = resourcename;
	}


	public Object getFildname() {
		return fildname;
	}


	public void setFildname(Object fildname) {
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
	
	
	
	
}	


