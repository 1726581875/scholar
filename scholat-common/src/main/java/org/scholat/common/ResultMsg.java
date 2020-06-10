package org.scholat.common;

import org.scholat.common.message.BaseMsg;

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

	public ResultMsg(BaseMsg bm , T data){
		this.status = bm.getCode();
		this.data = data;
		this.msg = bm.getMsg();
	}
	
}
