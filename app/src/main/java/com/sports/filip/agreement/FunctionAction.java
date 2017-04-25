package com.sports.filip.agreement;


import com.awhh.everyenjoy.library.base.util.Trace;
import com.sports.filip.util.AcacheUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pengfei on 2016/6/28.
 */
public class FunctionAction extends ShouxinerActioin implements IAsyncComplete<FunctionResult>{

    public final static String TAG = FunctionAction.class.getSimpleName();
    private final static CommandInfo[] mFunctionInfos;
    private IShouxinerFunction mFuncImpl;

    static {
		/* 注册function命令 */
        List<CommandInfo> lstFuncs = new LinkedList<CommandInfo>();
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GETCOMMANDS, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GETPAGES, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_SETTITLEBARVISIBLE, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_CLOSE, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GETLOCATE, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_PUBLISHTOPIC, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_SETWEBVIEWSIZE, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_OPEN, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.RECINDEX_TYPE8 , 2));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_INSTALLAPP, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_RECINDEX, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_POSTSADD, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_RECPOSTS , 2));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_RECEXPERT , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_SUBSCRIBE , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MATCHINDEX , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_SUBMIT_PAY_ORDER, 3));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_USERINFO , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MARCH_LEFT, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MARCH_RIGHT, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MARCH_FOLLOW , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MATCHCONTENT , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_CLOSEWIN , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_CHAT_LOGIN , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MATCHLIVE , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.EXPERT_ARTICLES , 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_SCAN_GRAPHIC_CODE, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GETSELFINFO, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_SHARETOMM, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GET_DEVICE_ID, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_PLAY_MEDIA, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GET_SEMESTERLIST, 1));
//        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_GET_CHILDSIMPRESS, 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_RULE , 1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_MYBETS ,1));
        lstFuncs.add(new CommandInfo(IShouxinerFunction.FUNCTION_RECINDEXRECOMMAND , 1));
        mFunctionInfos = lstFuncs.toArray(new CommandInfo[lstFuncs.size()]);
    }

    public FunctionAction(IShouxinerFunction funcImpl, String cmdName, CommandArgument cmdArgs) {
        actionName = ShouxinerUtil.SHOUXINER_FUNCTION;
        mFuncImpl = funcImpl;
//        mPageImpl = pageImpl;
        commandName = cmdName;
        commandArgs = cmdArgs;
    }
    
    @Override
    public void execute() {
        if(commandName.equalsIgnoreCase(IShouxinerFunction.EXPERT_ARTICLES)){
            String param =commandArgs.getArg("param" , ""); 
            mFuncImpl.onExpert_articles(param);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MYBETS)){
            mFuncImpl.onMyBets();
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_RULE)){
            mFuncImpl.onDetailRule();
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_CHAT_LOGIN)){
            mFuncImpl.onChatLogin();
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MATCHLIVE)){
            String href = commandArgs.getArg("href" , "");
            mFuncImpl.onMatchlive(href);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_CLOSEWIN)){
            String id = commandArgs.getArg("id" , "");
            String type = commandArgs.getArg("type" , "");
            mFuncImpl.onCloseWin(id , type);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_RECINDEXRECOMMAND))
        {
            String type = commandArgs.getArg("type" , "");
            mFuncImpl.recIndexRecommand(type);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MATCHCONTENT)){
            String ids = commandArgs.getArg("ids" , "");
            mFuncImpl.onMatchcontent(ids);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MARCH_FOLLOW)){
            mFuncImpl.onMarch_follow();
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_USERINFO)){
            String userid = commandArgs.getArg("userid" , "");
            mFuncImpl.onFunctionUserinfo(userid);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MARCH_LEFT)){
            String param = commandArgs.getArg("param" , "");
            mFuncImpl.onMarch_left(param);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MARCH_RIGHT)){
            String param = commandArgs.getArg("param" , "");
            mFuncImpl.onMarch_right(param);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_MATCHINDEX)){
            String ids = commandArgs.getArg("ids" , "");
            mFuncImpl.onMatchindex(ids);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.RECINDEX_TYPE8)){
            Trace.d("web-------recindex_type8");
            mFuncImpl.recindex_type8();
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_SUBSCRIBE)){
            String userId = commandArgs.getArg("param" , "");
            mFuncImpl.setFunctionSubscribe(userId);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_RECEXPERT)){
            String userId = commandArgs.getArg("id" , "1");
            mFuncImpl.onExpertsDetail(userId , "1");
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_RECPOSTS)){
            String expertId = commandArgs.getArg("param" , "");
            mFuncImpl.OnOpenExperts(expertId);
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_RECINDEX)){
            String charge = commandArgs.getArg("type" , 0) + "";
            AcacheUtil.putSmdseq(commandArgs.getCmdSeq());
            mFuncImpl.onExecute(charge , this);
        }else if (commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_SUBMIT_PAY_ORDER)) {
            String charge = commandArgs.getArg("charge" , "");
            AcacheUtil.putSmdseq(commandArgs.getCmdSeq());
            mFuncImpl.submitPayOrder(charge , this);
        } else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_CLOSE)){
            mFuncImpl.close(commandArgs.getCmdSeq());
        } else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_POSTSADD)){
            mFuncImpl.close(commandArgs.getCmdSeq());
        }else if(commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_SETTITLEBARVISIBLE)){
            boolean isVisible = commandArgs.getArg("isVisible", false);
            mFuncImpl.setTitleBarVisible(commandArgs.getCmdSeq(), isVisible, this);
        }else if (commandName.equalsIgnoreCase(IShouxinerFunction.FUNCTION_OPEN)) {
            String uri = commandArgs.getArg("uri", "");
            if (uri.startsWith("http://") || uri.startsWith("https://")) {
                mFuncImpl.open(commandArgs.getCmdSeq(), uri, this);
            } else if (ShouxinerUtil.isShouxinerPage(uri)) {
                IShouxinerParser.Factory.newInstance().execute(uri);
            } else {
                FunctionResult fr = new FunctionResult(1, "不支持的uri: " + uri);
                invokeFunctionResult(commandArgs.getCmdSeq(), fr);
            }
        }
    }

    private void invokeFunctionResult(String cmdSeq, FunctionResult rs) {
        if (null != mFuncImpl) {
            String rsString = "";
            if (rs != null) {
                rsString = rs.getTextResults();
            }
            try {
                mFuncImpl.execJavascript("onShouxinerSchemeCallback(" + cmdSeq + "," + rsString + ")");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onComplete(FunctionResult result) {
        if (null != commandArgs) {
            invokeFunctionResult(commandArgs.getCmdSeq(), result);
        }
    }
}
