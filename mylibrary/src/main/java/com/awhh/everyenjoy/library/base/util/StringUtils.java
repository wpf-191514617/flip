package com.awhh.everyenjoy.library.base.util;

import android.content.Context;

import com.awhh.everyenjoy.library.R;
import com.awhh.everyenjoy.library.base.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

	/**
	 *  判断字符串是否为空
	 *
	 * @param str
	 * @return   true   为空
	 * 			 false  不为空
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	
    
    public static String longSizeToString(long size,Context ctx) {
        String sizeStr = android.text.format.Formatter.formatFileSize(ctx, size);
        return sizeStr;
    }

	/**
	 * 邮箱 格式验证
	 * @param email
	 * @return
	 */
    public static boolean isEmail(String email) {
		if(isEmpty(email)){
			return false;
		}
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    /**
     * 手机号：手机号码格式验证
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
		if(isEmpty(mobiles)){
			return false;
		}
		 Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0,0-9]))\\d{8}$");
		 Matcher m = p.matcher(mobiles);
		 return m.matches();
   }
    
    
    public static String  getStringResurce(int id, Context ctx){
        return ctx.getResources().getString(id);
    }
    
    
    public static String  getStringResurce(int id){
        return MyApplication.getAppContext().getResources().getString(id);
    }


	/**
	 * is url
	 *
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		if(isEmpty(url)){
			return false;
		}
		Pattern pattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
		return pattern.matcher(url).matches();
	}

    public static String getStingByArray(String[] cards){
    	String cardString = "";
		for (int i = 0; i < cards.length; i++) {
			if (i == cards.length-1) {
				cardString += cards[i];
			}else{
				cardString += cards[i] + ",";
			}
		}
		return cardString;
    }


	
	private static double EARTH_RADIUS = 6378.137;
	private static double rad(double d)
	{
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据经纬度， 计算俩点之间的距离
	 * @param lat1	
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}


	public static boolean isPassword(String password)
	{
		if(isEmpty(password)){
			ToastUtils.showToastShort(R.string.chk_userpwd_len);
			return false;
		}
		// 密码：范围验证
		int passwordLegth = password.length();
		if (passwordLegth <6 || passwordLegth > 20) {
			
			ToastUtils.showToastShort(R.string.chk_userpwd_len);
			return false;
		}

		Pattern p = Pattern.compile("^[0-9a-zA-Z]+$");
		Matcher m = p.matcher(password);
//		return m.matches();
		
		if(!m.matches()){
			ToastUtils.showToastShort(R.string.chk_userpwd_len);
			return false;	
		}
		
		return true;
	}
	
	public static boolean isAuthCode(String code){
		if(isEmpty(code)){
			ToastUtils.showToastShort("输入的验证码不能为空");
			return false;
		}
		if(code.length() != 6){
			ToastUtils.showToastShort("请输入6位验证码");
			return false;
		}
		return true;
	}
	
}
