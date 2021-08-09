package manabboro.roomdatabase.sample.util;

import java.text.SimpleDateFormat;

public class DateUtils {

    public static String formatDate(long dateTaken) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("d MMM yyyy HH:mm a");
       return simpleDateFormat.format(dateTaken);
    }
}
