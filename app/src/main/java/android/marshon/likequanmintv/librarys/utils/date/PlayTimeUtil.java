package android.marshon.likequanmintv.librarys.utils.date;

/**
 * Created by Marshon.Chen on 2016/6/12.
 * DESC:
 */
public class PlayTimeUtil {

    //tiem   millionsecond
    public static String getPlayTime(int time){
        StringBuilder timeStr=new StringBuilder();
        // 1s = 1000ms   1minute=60s    1h=60minute= 60*60*1000ms
        if (time<60*60*1000){
            timeStr.append("00");
        }else if (time<60*1000){
            timeStr.append(":00");
        }
        return checkTime(timeStr,time);
    }

    private static String checkTime(StringBuilder timeStr, int subtime){
        int oneHour=60*60*1000;
        int oneMinute=60000;
        int oneSecond=1000;
        if (subtime>=oneHour){
            int hour = subtime / oneHour;
            timeStr.append(""+translate(hour));
            return checkTime(timeStr,subtime % oneHour);
        }else if(oneHour>subtime&&subtime>=oneMinute){
            int minute = subtime / oneMinute;
            timeStr.append(":"+translate(minute));
            return checkTime(timeStr,subtime % oneMinute);
        }else if (oneMinute>subtime&&subtime>=oneSecond){
            int second = subtime / oneSecond;
            timeStr.append(":"+translate(second));
            return timeStr.toString();
        }else{
            timeStr.append(":00");
            return timeStr.toString();
//            throw  new RuntimeException("你这算法有Bug");
        }
    }

    private static String translate(int number){

        if (number<10){
            return "0"+number;
        }else{
            return ""+number;
        }
    }



}
