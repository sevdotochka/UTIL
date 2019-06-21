package pns.parsers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class GeneralValidation {

    /**
     * Validation of protocol's data string. Tests, that the string is a sequens
     * of didgits, probably with leading '+' or '-' symbol
     *
     * @param str
     * @return
     */
    public static boolean validateStr(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return false;
        }
        Boolean result = true;
        int k = 0;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            k = 1;
        }
        for (; k < str.length(); k++) {
            result = result && Character.isDigit(str.charAt(k));
        }
        return result;
    }

    /**
     * Validates? is the first char in the given String str is '+' or '-'
     *
     * @param str
     * @return
     */
    public static boolean validateSignedString(String str) {

        return (str.length() > 1) && (str.charAt(0) == '+' || str.charAt(0) == '-');
    }

    /**
     * Validation of protocol's data string. Tests, that the string is a sequens
     * of pure didgits
     *
     * @param str
     * @return
     */
    public static boolean validatePureNumberStr(String str) {

        str = str.trim();
        if (str.length() == 0) {
            return false;
        }
        Boolean result = true;
        int k = 0;
        for (; k < str.length(); k++) {
            result = result && Character.isDigit(str.charAt(k));
        }
//        System.out.println(str + "   OOOO 88888 " + result);
        return result;
    }

    /**
     * Validation of protocol's data string. Tests, that the string is a sequens
     * of didgits, devided by one point (like double).
     *
     * @param str
     * @return
     */
    public static boolean validatePureDoubleStr(String str) {
        if (str == null) {
            return false;
        }
        str = str.trim();
        if (str.length() == 0) {
            return false;
        }
        boolean result = true;
//        System.out.println(" str " + str);
        String[] parts = str.split("\\.");
//        System.out.println("   parts len " + parts.length);
        if (parts.length != 2) {
            return false;
        }
        return validateStr(parts[0]) && validateStr(parts[1]);
    }

    public static boolean validateDateTime(String dateTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
//        System.out.println("            isDateTime --- dateTime:  " + dateTime + "  format: " + format);

        try {
            Date d = dateFormat.parse(dateTime.trim());
            //System.out.println(" Date: " + d);
        } catch (Exception pe) {
            return false;
        }
        return true;
    }

    public static String exludeFirstCharacter(String str) {
        String result = "";
        for (int k = 0; k < str.length(); k++) {
            if (k != 0) {
                result += str.charAt(k);
            }
        }
        return result;
    }

    public static boolean validateProtocols(String data) {
        AbstractParserString ap0 = new ParserRESStrings(data);
        AbstractParserString ap1 = new ParserTCString(data);
        AbstractParserString ap2 = new ParserMPCString(data);
        ap0.blockGenerator(data);
        ap1.blockGenerator(data);
        ap2.blockGenerator(data);

        return ap0.isValidDataBlocks() || ap1.isValidDataBlocks() || ap2.isValidDataBlocks();

    }

    public static String attributeOfData(AbstractParserString ap) {
        return ap.getAttr();
    }

    /**
     * Converts CP1251 to UTF-8
     *
     * @param s
     * @return
     */
    public static String FromCP1251toUTF8(String s) {
        String utf8String = s;
//        try {
//            //utf8String = new String(s.getBytes(), "cp1251");
//            utf8String = new String(s.getBytes());
//
//        } catch (Exception ex) {
//            System.out.println("  ex:  " + ex.getMessage());
//            //Logger.getLogger(AppConstants.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return utf8String;
    }

}
