package com.sports.filip.agreement;

/**
 * shouxiner action
 *
 * @author Frank
 * @email frank@mondol.info
 * @create_date 2015年4月21日
 */
public abstract class ShouxinerActioin {
	public String actionName;
	public String commandName;
	public CommandArgument commandArgs;

	public abstract void execute();
}
