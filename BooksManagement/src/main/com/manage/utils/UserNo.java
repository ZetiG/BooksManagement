package main.com.manage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 为学生和教师生成学号和工号
 */
public class UserNo {

    /**
     * 生成学号，当前时间加一位随机数
     *
     * @return
     */
    public static String generateStuNo() {
        String date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        date = sdf.format(new Date());
        String nextInt = String.valueOf(new Random().nextInt(10));
        date = date.concat(nextInt);
        return date;
    }

}
