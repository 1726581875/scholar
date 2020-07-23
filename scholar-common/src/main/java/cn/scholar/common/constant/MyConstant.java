package cn.scholar.common.constant;

/**
 * @author xmz
 * 2020年6月6日
 * 一些常量
 */
public interface MyConstant {

	public final static String TOKEN_NAME = "token";
	public final static int EXPIRE_TIME = 1000;

	public final static int PAGE_SIZE = 8;

	public final static String IMAGE_PATH = "F://image//";//服务器存放图片位置

	public final static String IMAGE_PRE = "/image/"; //图片映射路径

	public final static String DEFAULT_IMAGE = MyConstant.IMAGE_PRE + "default.png"; //位置默认图片
	
}
