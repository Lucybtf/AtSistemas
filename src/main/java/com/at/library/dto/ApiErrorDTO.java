package com.at.library.dto;

import java.io.Serializable;

public class ApiErrorDTO extends DTO{

	private static final long serialVersionUID = -4797650817644110842L;
	
	private String code;
	
	private String msg;

	
	public ApiErrorDTO(String code, String message) {
		// TODO Auto-generated constructor stub
		this.code=code;
		this.msg=message;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public String toString() {
		return "ApiErrorDTO [code=" + code + ", msg=" + msg + "]";
	}
	
	
	
	


	
}
