package com.sports.filip.agreement;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * shouxiner命令参数
 * 
 * @author Frank
 * @email frank@mondol.info
 * @create_date 2015年4月16日
 */
public class CommandArgument implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String EXTRA_COMMAND_ARGUMENT = "command_argument";
	public final static String EXTRA_COMMAND_NAME = "command_name";

	private Map<String, String> mArgs;

	public CommandArgument(Map<String, String> args) {
		this.mArgs = args;
	}

	public String getCmdSeq() {
		return getArg("cmdSeq", "");
	}

	
	public Map<String , String> getmArgs(){
		return mArgs;
	}
	
	/**
	 * 根据参数名获取参数值
	 */
	public <T> T getArg(String argName, T defVal) {
		Object object = defVal;
		String value = mArgs.get(argName);
		if (TextUtils.isEmpty(value)) {
			return defVal;
		}
		try {
			if (defVal instanceof String)
				object = value;
			else if (defVal instanceof Boolean) {
				object = Boolean.parseBoolean(mArgs.get(argName));
			} else if (defVal instanceof Short) {
				object = Boolean.parseBoolean(mArgs.get(argName));
			} else if (defVal instanceof Integer) {
				object = Integer.parseInt(mArgs.get(argName));
			} else if (defVal instanceof Long) {
				object = Long.parseLong(mArgs.get(argName));
			} else if (defVal instanceof Float) {
				object = Float.parseFloat(mArgs.get(argName));
			} else if (defVal instanceof Double) {
				object = Double.parseDouble(mArgs.get(argName));
			}
		} catch (NumberFormatException e) {
			return defVal;
		}
		return (T) object;
	}

	@Override
	public String toString() {
		StringBuilder sbStr = new StringBuilder();
		for (Map.Entry<String, String> e : mArgs.entrySet()) {
			if (sbStr.length() > 0)
				sbStr.append(", ");
			sbStr.append(e.getKey() + "=" + e.getValue());
		}
		return sbStr.toString();
	}
	
	
}
