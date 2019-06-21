/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils;

import java.text.ParseException;

/**
 *
 * @author Movement
 */
public class ParserStr {

    public static int parseInt(String s) throws ParseException {
        return Integer.parseInt(s);
    }

    public static long parseLong(String s) throws ParseException {
        return Long.parseLong(s);
    }

    public static double parseDouble(String s) throws ParseException {
        return Double.parseDouble(s);
    }

}
