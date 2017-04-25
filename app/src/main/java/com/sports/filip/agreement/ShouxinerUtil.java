package com.sports.filip.agreement;

import android.text.TextUtils;

import com.awhh.everyenjoy.library.base.util.Trace;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * shouxiner协议工具类
 *
 * @author Frank
 * @email frank@mondol.info
 * @create_date 2015年4月21日
 */
public class ShouxinerUtil {
	public final static String SHOUXINER_SCHEME = "shouxiner://";
	public final static String SHOUXINER_FUNCTION = "function";
	public final static String SHOUXINER_PAGE = "page";

	/**
	 * 判断指定字符串是否是shouxiner协议
	 */
	public static boolean isShouxiner(String str) {
		return !TextUtils.isEmpty(str) && str.startsWith(SHOUXINER_SCHEME);
	}

	/**
	 * 判断指定字符串是否是shouxiner page
	 */
	public static boolean isShouxinerPage(String str) {
		return !TextUtils.isEmpty(str) && str.startsWith(SHOUXINER_SCHEME + SHOUXINER_PAGE + "/");
	}

	/**
	 * 判断指定字符串是否是shouxiner function
	 */
	public static boolean isShouxinerFunction(String str) {
		return !TextUtils.isEmpty(str) && str.startsWith(SHOUXINER_SCHEME + SHOUXINER_FUNCTION + "/");
	}

	public static String urlDecodeString(String str) {
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static Map<String, String> urlParamsToMap(String urlParams) {
		String[] akvs = urlParams.split("&");
		HashMap<String, String> mapArgs = new HashMap<String, String>();
		for (String akv : akvs) {
			int kvIndex = akv.indexOf('=');
			String key = akv.substring(0, kvIndex);
			String val = akv.substring(kvIndex + 1);

			Trace.d("keyValue : key = " + key + ", value = " + ShouxinerUtil.urlDecodeString(val));
			
			
			mapArgs.put(key, ShouxinerUtil.urlDecodeString(val));
			
		}
		return mapArgs;
	}
}
