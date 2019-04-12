package com.asriputridga.diary2.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
    public static String getTimeRightNow(){
        SimpleDateFormat desiredFormat = new SimpleDateFormat(
                "kk:mm dd-MM-yyyy");
        return desiredFormat.format(new Date());

    }
}
