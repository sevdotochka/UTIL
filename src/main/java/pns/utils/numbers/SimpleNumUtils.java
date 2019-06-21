/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.numbers;

/**
 *
 * @author User
 */
public class SimpleNumUtils {

    public static int gettingIntFromSTR(String s) {
        int res = 0;
        try {
            res = Integer.parseInt(s);
        } catch (NumberFormatException e) {
        }
        return res;
    }

    public static int gettingByteFromSTR(String s) {
        byte res = 0;
        try {
            res = Byte.parseByte(s);
        } catch (NumberFormatException e) {
        }
        return res;
    }

    public static long gettingLongFromSTR(String s) {
        long res = 0;
        try {
            res = Long.parseLong(s);
        } catch (NumberFormatException e) {
        }
        return res;
    }

    public static double gettingDoubleFromSTR(String s) {
        double res = 0;
        try {
            res = Double.parseDouble(s);
        } catch (NumberFormatException e) {
        }
        return res;
    }
}
