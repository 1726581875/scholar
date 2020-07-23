package cn.scholar.common.utils;

import cn.scholar.common.RespBean;
import cn.scholar.common.message.BaseMsg;
import cn.scholar.common.message.enums.CommonEnum;

/**
 * @author xmz
 * 2020年6月6日
 * 返回方便封装结果信息
 */
public class ResultUtil {

	/**
	 * 告诉前端成功，但不返回数据
	 * @return
	 *Object
	 */
	public static Object success(){
		return new RespBean<Object>(CommonEnum.SUCCESS,null);
	}
	
	/**
	 * 告诉前端成功，并数据
	 * @param data
	 * @return
	 *Object
	 */
	public static Object success(Object data){
		return new RespBean<Object>(CommonEnum.SUCCESS,data);
	}
	
	
	/**
	 * 告诉前端有错误，返回错误信息，不带数据
	 * @param errMsg
	 * @return
	 *Object
	 */
	public static Object fail(BaseMsg errMsg){
		return new RespBean<Object>(errMsg,null);
	}
	
	public static Object fail(String errMsg){
		return new RespBean<Object>(-999,null,errMsg);
	}

}
