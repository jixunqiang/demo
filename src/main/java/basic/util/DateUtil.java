package basic.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date 类
 */
public class DateUtil {

    public static String dateTime() {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
        return ft.format(dNow);
    }

    /**
     * 以字符串形式返回当前时间的毫秒数
     * @return
     */
    public static String getTimeMillions() {
        Calendar cal = Calendar.getInstance();
        long lt = cal.getTimeInMillis();
        return String.valueOf(lt);
    }
}