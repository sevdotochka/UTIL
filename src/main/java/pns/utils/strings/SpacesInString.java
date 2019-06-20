/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.strings;

import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class SpacesInString {

    public static String removeAllSpaces(String s) {
        StringBuffer buffer = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
            buffer.append(tokenizer.nextToken());
        }
        return buffer.toString().trim();
    }

    // excess
    public static String removeExcessSpaces(String s) {
        StringBuffer buffer = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
            buffer.append(tokenizer.nextToken() + " ");
        }
        return buffer.toString().trim();
    }

    public static String[] removeExcessSpaces(String[] strArr) {
        for (int k = 0; k < strArr.length; k++) {
            strArr[k] = removeExcessSpaces(strArr[k]);
        }
        return strArr;
    }

    public static String[] trimming(String[] strArr) {
        for (int k = 0; k < strArr.length; k++) {
            strArr[k] = strArr[k].trim();
        }
        return strArr;
    }

}
