package com.sports.filip.agreement;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Function返回结果
 * 
 * @author Jin
 * @date 2015-4-10上午9:34:03
 */
public class FunctionResult {
	private JSONObject mResults = new JSONObject();

	public FunctionResult() {
		setResultCode(2);
	}

	public FunctionResult(int resultCode, String resultMsg) {
		setResultCode(resultCode);
		if (!TextUtils.isEmpty(resultMsg))
			setResultMsg(resultMsg);
	}

	public FunctionResult(int resultCode) {
		this(resultCode, null);
	}

	public int getResultCode() {
		return mResults.optInt("resultCode");
	}

	public void setResultCode(int code) {
		setArg("resultCode", code);
	}

	public String getResultMsg() {
		return mResults.optString("resultMsg");
	}

	public void setResultMsg(String msg) {
		setArg("resultMsg", msg);
	}

	public void setArg(String argName, Object argVal) {
		try {
			mResults.putOpt(argName, argVal);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getTextResults() {
		return mResults.toString();
	}
}