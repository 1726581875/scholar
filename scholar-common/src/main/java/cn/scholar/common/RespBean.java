package cn.scholar.common;

import cn.scholar.common.message.BaseMsg;

import lombok.Data;

@Data
public class RespBean<T> {

	private int status;
	
	private T data;
	
	private String msg;
	
	public boolean isSuccess(){
		return status==1 ? true : false;
	}
	
	public RespBean(){}
	
	public RespBean(int status , T data, String msg){
		this.status = status;
		this.data = data;
		this.msg = msg;		
	}

	public RespBean(BaseMsg bm , T data){
		this.status = bm.getCode();
		this.data = data;
		this.msg = bm.getMsg();
	}


}
