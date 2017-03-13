package com.sports.filip.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author：huafang2016
 * Date: 2016/8/30 15:39
 * Email：15291967179@163.com
 */
public class ScoreUtil
{
                                                                                                                                                                                                                                                                                                                    

    /**
     * 获取未来7天
     */
    public static List<String> getNextServenDays()
    {
        SimpleDateFormat fomater = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 8; i++)
        {
            Calendar calendar = Calendar.getInstance();
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.set(Calendar.DAY_OF_YEAR, dayOfYear + i);
            dates.add(fomater.format(calendar.getTime()));
        }
        return dates.subList(1, 8);
    }


    /**
     * 获取过去7天的日期
     *
     * @return
     */
    public static List<String> getPassedSevenDays()
    {
        SimpleDateFormat fomater = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 8; i++)
        {
            Calendar calendar = Calendar.getInstance();
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.set(Calendar.DAY_OF_YEAR, dayOfYear - i);
            dates.add(fomater.format(calendar.getTime()));
        }
        return dates.subList(1, 8);
    }

}
