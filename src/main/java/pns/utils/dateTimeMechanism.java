package pns.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpdw
 */
public class dateTimeMechanism {

    /**
     * Convert currant Moment to the Human date
     *
     * @return
     */
    public static String convertLongToDateStr() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy   hh:mm");
        return format.format(d);
    }

    /**
     * Convert given Moment to the Human date
     *
     * @param moment
     * @return
     */
    public static String convertLongToDateStr(long moment) {
        Date d = new Date(moment);
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy   hh:mm");
        return format.format(d);
    }

    /**
     * Convert given Moment to the Human date, using given format string
     *
     * @param moment
     * @return
     */
    public static String convertLongToDateStr(long moment, String formatString) {
        Date d = new Date(moment);
        SimpleDateFormat format = new SimpleDateFormat(formatString);

        return format.format(d);
    }

    /**
     * Convert given Moment to the Human date, using given format string
     *
     * @param moment
     * @return
     */
    public static String convertLongToDateStr(long moment, String formatString, boolean isUTC) {
        Date d = new Date(moment);
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        if (isUTC) {
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return format.format(d);
    }

    public static Date parseDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

    public static String convert2StandardFormat(long moment, boolean isUTC, boolean showMili) {
        //"yyyy-MM-dd HH:mm:ss.SSS"
        String formatStr = "";
        if (showMili) {
            formatStr = "dd.MM.yyyy   HH:mm:ss.SSS";
        } else {
            formatStr = "dd.MM.yyyy   HH:mm:ss";
        }
        return convertLongToDateStr(moment, formatStr, isUTC);
    }

    public static long removeHHMMSS(long moment) throws ParseException {
        String conv = convertLongToDateStr(moment);
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        return format.parse(conv).getTime();
    }

    public static boolean isToday(long moment) {
        String testConv = convertLongToDateStr(moment, "dd MM yyyy");
        String conv = convertLongToDateStr(new Date().getTime(), "dd MM yyyy");
        return conv.equals(testConv);
    }

    public static long strDateToLong(String s) {
        Date d = strToDate(s, "///");
        return d.getTime();
    }

    public static Date strToDate(String s, String dateDelim) {
        String[] parts = s.split(dateDelim);
        if (parts.length != 3) {
            return new Date(2);
        }
        if (parts[0].length() != 4) {
            return new Date(2);
        }
        if (parts[1].length() != 2) {
            return new Date(2);
        }
        if (parts[2].length() != 2) {
            return new Date(2);
        }
        s = s.replaceAll(dateDelim, "-");
        try {
            return parseDate(s, "yyyy-MM-dd");
        } catch (ParseException ex) {
            Logger.getLogger(dateTimeMechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date(2);

    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Calendar toCalendar(long moment) {
        Date date = new Date(moment);
        Calendar cal = toCalendar(date);
        return cal;
    }

    public static Timestamp toTimeStamp() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public static Timestamp toTimeStamp(long moment) {
        Date date = new Date(moment);
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public static double toDoubleStamp() {
        long mm = System.currentTimeMillis();
        Instant instant = Instant.now();
        System.out.println("instant " + instant);
        int nano = instant.getNano();
        long sec = instant.getEpochSecond();
        String preRes = sec + "." + nano;
        System.out.println("sec.nano      " + preRes);
        System.out.println("sec.nano1 mm  " + mm);
        double res = Double.parseDouble(preRes);
        System.out.println("  res " + res);
//   1541068881.584000000
//   1541068881584
        return res;
    }

    public static double toNanoSec() {
        long mm = System.currentTimeMillis();
        Instant instant = Instant.now();
        //System.out.println("instant " + instant);
        int nano = instant.getNano();

        return nano;
    }

}
