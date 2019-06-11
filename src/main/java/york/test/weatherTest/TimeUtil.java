package york.test.weatherTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String getTodayDate() {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(System.currentTimeMillis()))
                .split(" ")[0];

    }
}
