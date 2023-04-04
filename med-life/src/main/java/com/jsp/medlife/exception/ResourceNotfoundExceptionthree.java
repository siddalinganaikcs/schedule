package com.jsp.medlife.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotfoundExceptionthree  extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     private String resourcename;
     private String fieldname;
     private Object fieldValue;
     
	public ResourceNotfoundExceptionthree(String resourcename, String fieldname, Object fieldValue) {
		super(resourcename+" : CONFIRM  PASSWORD DID'T MATCH PLS CHECK "+" "+fieldname+" "+fieldValue);
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldValue = fieldValue;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
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
