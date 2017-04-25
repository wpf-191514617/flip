package com.sports.filip.agreement;

/**
 * 命令信息
 *
 * @author Frank
 * @email frank@mondol.info
 * @create_date 2015年4月21日
 */
public class CommandInfo {
	/**
	 * 命令名
	 */
	public String commandName;
	/**
	 * 命令版本，从1开始
	 */
	public int commandVersion;

	public CommandInfo() {
	}

	public CommandInfo(String cmdName, int cmdVer) {
		commandName = cmdName;
		commandVersion = cmdVer;
	}
}
