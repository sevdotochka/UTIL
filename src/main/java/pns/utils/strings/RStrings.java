/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.strings;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import pns.utils.numbers.RChars;
import pns.utils.numbers.RInts;

/**
 *
 * @author jpdw
 */
public class RStrings {

    final public static String LATIN_SMALL = "latin";
    final public static String LATIN_LARGE = "LATIN";
    final public static String DIGITS = "DIGITS";

    /**
     * Gets a random String
     *
     * @return
     */
    public static String rndString() {
        char[] c = RChars.rndCharArray();
        return new String(c);

    }

    /**
     * Gets a random String
     *
     * @return
     */
    public static String rndLetterString() {
        int spLen = RInts.rndInt(5, 15);
        char[] c = RChars.rndCharArray('a', 'z');
        char[] C = RChars.rndCharArray('A', 'Z');
        char[] d = RChars.rndCharArray('0', '9');
        String special = "";

        String s1 = new String(c) + new String(d) + new String(C);
        for (int k = 0; k < spLen; k++) {
            s1 += new String(c) + new String(d) + new String(C);
        }
        spLen = RInts.rndInt(7, 15);
        for (int k = 0; k < spLen; k++) {
            s1 += s1;
        }

        return shaffleString(s1);
    }

    /**
     * Encripting given string s by cript algorithm cript
     *
     * @param s
     * @param cript can be MD5, SHA, SHA256
     * @return
     */
    public static String strToHash(String s, String cript) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(cript.trim());
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));

        return new String(hash, Charset.forName("cp1251"));
    }

    /**
     * Encripting given string s by cript algorithm cript
     *
     * @param s
     * @param cript can be MD5, SHA, SHA256
     * @return
     */
    public static String strToHash(String s) {
        //MessageDigest digest = MessageDigest.getInstance(cript);
        byte[] hash = s.trim().getBytes();

        StringBuffer sbf = new StringBuffer();
        for (byte b : hash) {
            int bI = b & 0xff;
            bI = bI >> 3;
            bI = bI * bI * bI * bI + 147 * bI * bI * bI + 97 * bI + 47;
            String strHex = Integer.toString(bI, Character.MAX_RADIX);
            sbf.append(strHex + "");
        }

        return sbf.toString();
    }

    public static String lastMoment() {
        String res = "" + System.currentTimeMillis();
        res = res.substring(res.length() - 6);
        return res;
    }

    public static String rndLetterStringRNDLen(int minLen, int maxLen, int iterLen, boolean isRNDResLength, boolean hasSpec) {
        String res = "";
        int k = pns.utils.numbers.RInts.rndInt(minLen, maxLen);
        for (int i = 0; i < iterLen; i++) {
            int r = pns.utils.numbers.RInts.rndInt(minLen * k, maxLen * k);
            res += RStrings.rndString('a', 'z');
            res += RStrings.rndString('A', 'Z');
            res += RStrings.rndString('0', '9');
            if (hasSpec) {
                String sp = "";
                while (sp.length() < k / 2) {
                    sp += "!@#$%^&*()_+=-:;";
                }
                res += shaffleString(sp);
            }
        }
        res = shaffleString(res) + shaffleString(res) + shaffleString(res);
        res = shaffleString(res);
        if (isRNDResLength) {
            res = res.substring(0, k);
        } else {
            res = res.substring(0, (maxLen + minLen) / 2);
        }
        return res;
    }

    public static String rndLetterStringRNDLen(int minLen, int maxLen, char min, char max, int iterLen, boolean isRNDResLength, boolean hasSpec) {
        String res = "";

        int k = pns.utils.numbers.RInts.rndInt(minLen, maxLen);
        for (int i = 0; i < iterLen; i++) {
            int r = pns.utils.numbers.RInts.rndInt(minLen * k, maxLen * k);
            res += RStrings.rndString(min, max);
            if (hasSpec) {
                String sp = "";
                while (sp.length() < k / 2) {
                    sp += "!@#$%^&*()_+=-:;";
                }
                res += shaffleString(sp);
            }
        }
        res = shaffleString(res) + shaffleString(res) + shaffleString(res);
        res = shaffleString(res);
        if (isRNDResLength) {
            res = res.substring(0, k);
        } else {
            res = res.substring(0, (maxLen + minLen) / 2);
        }
        return res;
    }

    public static String rndLetterStringRNDLen(int len) {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < len; k++) {
            byte b = pns.utils.numbers.RBytes.rndByte((byte) 'a', (byte) 'z');
            String c = (char) b + "";
            if (Math.random() < .5) {
                c = c.toUpperCase();
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String rndLetterStringRNDLen(int minLen, int maxLen) {
        int len = pns.utils.numbers.RInts.rndInt(minLen, maxLen);
        return rndLetterStringRNDLen(len);
    }

    public static String rndLetterStringRNDLen(int minLen, int maxLen, char min, char max, int iterLen, boolean isRNDResLength) {
        String res = "";

        int k = pns.utils.numbers.RInts.rndInt(minLen, maxLen);
        for (int i = 0; i < iterLen; i++) {
            int r = pns.utils.numbers.RInts.rndInt(minLen * k, maxLen * k);
            res += RStrings.rndString(min, max);

        }
        res = shaffleString(res) + shaffleString(res) + shaffleString(res);
        res = shaffleString(res);
        if (isRNDResLength) {
            res = res.substring(0, k);
        } else {
            res = res.substring(0, (maxLen + minLen) / 2);
        }
        return res;
    }

    /**
     * Gets a random String of fixed length
     *
     * @return
     */
    public static String rndString(int length) {
        char[] c = RChars.rndCharArray(length);
        String s = new String(c);
        return shaffleString(s);
    }

    /**
     * Gets a random String jf characters between min and max
     *
     * @return
     */
    public static String rndString(char min, char max) {
        char[] c = RChars.rndCharArray(min, max);
        String s = new String(c);
        return shaffleString(s);

    }

    /**
     * Gets a random String of characters between min and max and having fixed length
     *
     * @return
     */
    public static String rndString(int length, char min, char max) {
        char[] c = RChars.rndCharArray(length, min, max);
        String s = new String(c);
        return shaffleString(s);
    }

    /**
     * Gets a random String of fixed charset
     *
     * @return
     */
    public static String rndString(String type) {
        char min = ' ';
        char max = ' ';
        if (type.trim().equals(LATIN_LARGE)) {
            min = 'A';
            max = 'Z';
        } else if (type.trim().equals(LATIN_SMALL)) {
            min = 'a';
            max = 'z';
        } else if (type.trim().equals(DIGITS)) {
            min = '0';
            max = '9';
        }
        char[] c = RChars.rndCharArray(min, max);
        String s = new String(c);
        return shaffleString(s);

    }

    /**
     * Gets a random String of fixed charset and fixed length
     *
     * @return
     */
    public static String rndString(int length, String type) {
        char min = ' ';
        char max = ' ';
        if (type.trim().equals(LATIN_LARGE)) {
            min = 'A';
            max = 'Z';
        } else if (type.trim().equals(LATIN_SMALL)) {
            min = 'a';
            max = 'z';
        } else if (type.trim().equals(DIGITS)) {
            min = '0';
            max = '9';
        }
        char[] c = RChars.rndCharArray(length, min, max);
        String s = new String(c);
        return shaffleString(s);

    }

    /**
     * Gets a random String of fixed charset, fixed additional characters and fixed length
     *
     * @return
     */
    public static String rndString(int length, String type, char[] cc) {
        char min = ' ';
        char max = ' ';
        if (type.trim().equals(LATIN_LARGE)) {
            min = 'A';
            max = 'Z';
        } else if (type.trim().equals(LATIN_SMALL)) {
            min = 'a';
            max = 'z';
        } else if (type.trim().equals(DIGITS)) {
            min = '0';
            max = '9';
        }
        String CC = new String(cc);
        CC = shaffleString(CC);
        char[] c = RChars.rndCharArray(length, min, max);
        if (min != max) {
            return new String(c) + CC;
        }

        return CC;
    }

    /**
     * Gets the code data string? using fixed string array and fixeddelimiter
     *
     * @param sData
     * @param delimiter
     * @return
     */
    public static String codeString(String[] sData, char delimiter) {
        String result = "";
        for (int i = 0; i < sData.length; i++) {
            if (i < sData.length - 1) {
                result += sData[i] + delimiter;
            } else {
                result += sData[i];
            }
        }
        return result;
    }

    public static String nullToRndString(String s) {
        if (s == null) {
            int len = pns.utils.numbers.RInts.rndInt(10, 15);
            s = rndString(len, 'a', 'z');
        }

        return s;
    }

    /**
     * Shaffling given string
     *
     * @param string
     * @return
     */
    public static String shaffleString(String string) {
        Shaffle s = new Shaffle();
        return s.shuffle(string);
    }

    /**
     * Breaks given string to the token Array
     *
     * @param string
     * @return
     */
    public static String[] strParts(String string) {
        //StringTokenizer st = new StringTokenizer(string);
        String[] strings = string.split("\\s+");
        return removeSpaces(strings);
    }

    /**
     * Clears the whitespaces in the given string and glue it's words
     *
     * @param str
     * @return
     */
    public static String clearLongWhitespaces(String str) {

        if (str == null) {
            str = "";
        }
        String result = "";
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s != null) {
                result += s.trim();
            }
        }
        return result.trim();
    }

    /**
     * Breaks given string to the token Array, using given delimeter
     *
     * @param string
     * @return
     */
    public static String[] strParts(String string, String delim) {
        //StringTokenizer st = new StringTokenizer(string);
        String[] strings = string.split(delim);
        return removeSpaces(strings);
    }

    /**
     * With every string in array String[] removes whete spaces from its end
     *
     * @param strings
     * @return
     */
    public static String[] removeSpaces(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        return strings;
    }

    /**
     * Counts a "Editable distance" between two strings S1 and S2
     *
     * @param S1
     * @param S2
     * @return
     */
    public static int sringDistance(String S1, String S2) {

        int m = S1.length(), n = S2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            D2[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            D1 = D2;
            D2 = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    D2[j] = i;
                } else {
                    int cost = (S1.charAt(i - 1) != S2.charAt(j - 1)) ? 1 : 0;
                    if (D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost) {
                        D2[j] = D2[j - 1] + 1;
                    } else if (D1[j] < D1[j - 1] + cost) {
                        D2[j] = D1[j] + 1;
                    } else {
                        D2[j] = D1[j - 1] + cost;
                    }
                }
            }
        }
        return D2[n];
    }

    /**
     * Removes the character c from a given string s
     *
     * @param s
     * @param c
     * @return
     */
    public static String removeChar(String s, char c) {
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                r += s.charAt(i);
            }
        }
        return r;
    }

    public static double usageFrequency(String TEXT, String word) {
        if (TEXT == null || word == null) {
            return -1;
        }
        if (TEXT.length() * word.length() == 0) {
            return -2;
        }
        int k = 0;
        word = removeChar(word, ',');
        StringTokenizer TXTTok = new StringTokenizer(TEXT);
        while (TXTTok.hasMoreTokens()) {
            String s = TXTTok.nextToken();
            s = removeChar(s, ',');
            if (word.equals(s)) {
                k++;
            }
        }
        if (TXTTok.countTokens() > 0) {
            return k / TXTTok.countTokens();
        }
        return 0;
    }

    /**
     * Removes all WHITE spaces in the string including all \r \n \t
     * <br />
     * The string s brakes into tokens, then the received parts are concatinates
     *
     * @param s
     * @return
     */
    public static String removeSpaces(String s) {
        StringTokenizer stt = new StringTokenizer(s);
        String res = "";
        StringBuffer sbf = new StringBuffer();

        while (stt.hasMoreTokens()) {
            String tok = stt.nextToken().trim();
            sbf.append(tok);
        }
        res = sbf.toString();
        return res;
    }

    /**
     * Given num value transforms to the string with leading zeros. The result string contains
     * strLen characters
     *
     * @param num
     * @param strLen
     * @return
     */
    public static String zeroFirst(int num, int strLen) {
        String res = "" + num;
        while (res.length() < strLen) {
            res = "0" + res;
        }
        return res;
    }

    /**
     * Given num value transforms to the string with leading zeros. The result string contains
     * strLen characters
     *
     * @param num
     * @param strLen
     * @return
     */
    public static String zeroFirst(long num, int strLen) {
        String res = "" + num;
        while (res.length() < strLen) {
            res = "0" + res;
        }
        return res;
    }

    /**
     * Given num value adds to ""( ""+num) or to "0"( "0"+num). Generates the leading-Zero string
     * from a given int
     *
     * @param num
     * @return
     */
    public static String zeroFirst(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return "" + num;
    }

    /**
     * Take up a string and replace all of LINESEPARATOR char to HTML 'br' tag
     *
     * @param txt
     * @return
     */
    public static String generateBRs(String txt) {
        String[] txtParts = txt.split(System.lineSeparator());
        String res = "";
        for (int k = 0; k < txtParts.length; k++) {
            res += txtParts[k] + "<br />";
        }
        return res;
    }

    /**
     * Takes Up an array-string and glue it to a sttring with a 'glue'-parameter
     *
     * @param parts
     * @param glue
     * @return
     */
    public static String joinStringArray(String[] parts, String glue) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String ss : parts) {
            stringBuffer.append(ss + glue);
        }
        return stringBuffer.toString();
    }

    /**
     * If the given string conains only digits returns true/ else returns false
     *
     * @param par
     * @return
     */
    public static boolean isDigitString(String par) {
        for (int k = 0; k < par.length(); k++) {
            byte b = (byte) par.charAt(k);
            if (b < 48 || b > 57) {
                System.out.println("    k: " + k + "   " + b);
                return false;
            }
        }
        return true;
    }

    /**
     * If the given stringarray conains only digits returns true/ else returns false
     *
     * @param pars
     * @return
     */
    public static boolean isDigitString(String[] pars) {
        boolean res = true;
        for (String par : pars) {
            res = isDigitString(par) && res;
        }
        return res;
    }

    public static String convertToUTF(String ss, String encodingTo, String encodingFrom) throws UnsupportedEncodingException {
//С??СТ

//        InputStream stream = new ByteArrayInputStream(ss.getBytes(encodingFrom));
//        Scanner csc = new Scanner(stream, encodingTo);
        String res = new String(ss.getBytes(encodingFrom), encodingTo);

        return res;
    }
}
