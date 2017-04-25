package com.sports.filip.agreement;

/**
 * WebView容器实现接口
 *
 * @author Frank
 * @email frank@mondol.info
 * @create_date 2015年4月13日
 */
public interface IWebViewContainer {
	/**
	 * 是否隐藏标题栏
	 */
	public void setTitleBarVisible(String cmdSeq, Boolean isVisible,
								   IAsyncComplete<FunctionResult> callback);

	/**
	 * 设置标题栏标题
	 */
	public void setTitleBarText(String cmdSeq, String title, IAsyncComplete<FunctionResult>
			callback);

	/**
	 * 关闭页面
	 */
	public void close(String cmdSeq);
}
