package com.sports.filip.agreement;

/**
 * shouxiner协议解析器
 *
 * @author Jin
 * @date 2015-4-10上午9:06:29
 */
public interface IShouxinerParser {
	/**
	 * 设置Function实现类
	 */
	public void setFunctionImpl(IShouxinerFunction funcImpl);

	/**
	 * 设置Page实现类，如果没有特殊需求通常不需要设置
	 */
//	public void setPageImpl(IShouxinerPage funcImpl);

	/**
	 * 解析协议字符串
	 */
	public ShouxinerActioin parse(String shouxinerStr);

	/**
	 * 执行协议字符串
	 */
	public void execute(String shouxinerStr);

	public static class Factory {
		public static IShouxinerParser newInstance() {
			IShouxinerParser sp = new ShouxinerParserImpl();
			return sp;
		}
	}
}
