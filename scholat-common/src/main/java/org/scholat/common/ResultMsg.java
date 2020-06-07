package org.scholat.common;

import org.scholat.common.exception.ComonException;
import org.scholat.common.exception.CommonEnum;

import lombok.Data;

@Data
public class ResultMsg<T> {

	private int status;
	
	private T data;
	
	private String msg;
	
	public boolean isSuccess(){
		return status==1 ? true : false;
	}
	
	public ResultMsg(){}
	
	public ResultMsg(int status ,T data, String msg){
		this.status = status;
		this.data = data;
		this.msg = msg;		
	}
	
	public ResultMsg(ComonException ce , T data){
		this.status = ce.getCode();
		this.data = data;
		this.msg = ce.getMsg();		
	}
	
	public ResultMsg(CommonEnum ee , T data){
		this.status = ee.getCode();
		this.data = data;
		this.msg = ee.getMsg();		
	}
	
}
