package com.sports.filip.agreement;

import com.awhh.everyenjoy.library.base.util.Trace;

import java.util.Map;

/**
 * 手心协议解析
 * 
 * @author Jin
 * @date 2015-4-10上午9:28:14
 */
public class ShouxinerParserImpl implements IShouxinerParser {
	private IShouxinerFunction mFunctionImpl;
//	private IShouxinerPage mPageImpl;

	@Override
	public void setFunctionImpl(IShouxinerFunction funcImpl) {
		mFunctionImpl = funcImpl;
	}

//	@Override
//	public void setPageImpl(IShouxinerPage pageImpl) {
//		mPageImpl = pageImpl;
//	}

	/**
	 * shouXiner 协议解析
	 */
	@Override
	public ShouxinerActioin parse(String shouxinerStr) {
		UnsupportedOperationException eNotSupport = new UnsupportedOperationException("不支持的协议格式：" + shouxinerStr);
		ShouxinerActioin seRetn = null;

		Trace.d("implString -> " + ShouxinerUtil.urlDecodeString(shouxinerStr));
		
		
		if (ShouxinerUtil.isShouxiner(shouxinerStr)) {
			String pBody = shouxinerStr.substring(ShouxinerUtil.SHOUXINER_SCHEME.length());
			int cIndex = pBody.indexOf('/');
			if (cIndex <= 0)
				throw eNotSupport;
			String actionName = pBody.substring(0, cIndex);
			String commandName = null;
			CommandArgument cmdArgs = null;
			int pIndex = pBody.indexOf('?');
			if (pIndex < 0) {
				// 没有参数
				commandName = pBody.substring(cIndex + 1);
			} else {
				commandName = pBody.substring(cIndex + 1, pIndex);
				String argsStr = pBody.substring(pIndex + 1);
				Map<String, String> mapArgs = ShouxinerUtil.urlParamsToMap(argsStr);
				cmdArgs = new CommandArgument(mapArgs);
			}

			if (actionName.equalsIgnoreCase(ShouxinerUtil.SHOUXINER_FUNCTION)) {
				seRetn = new FunctionAction(mFunctionImpl, commandName, cmdArgs);
			} else if (actionName.equalsIgnoreCase(ShouxinerUtil.SHOUXINER_PAGE)) {
//				seRetn = new PageAction(mPageImpl, commandName, cmdArgs);
			} else {
				throw eNotSupport;
			}
		} else {
			Trace.d("shouxinerAction-------> throw");
			throw eNotSupport;
		}
		
		Trace.d("shouxinerAction------->" + seRetn);
		
		return seRetn;
	}

	@Override
	public void execute(String shouxinerStr) {
		try {
			ShouxinerActioin se = parse(shouxinerStr);
			
			se.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
