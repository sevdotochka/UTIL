/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.parsers;

import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class TestValidTCMString implements IValidateData {//

    /**
     * Tests the correct data in the header's first line in block
     *
     * @param str
     * @return
     */
    private Boolean validHead(String str) {

        boolean result = true;
        //System.out.println("QQQQQQQQQQQQQQQQ   str=" + str);
        String[] heads = str.split("\\s");

        result = result && GeneralValidation.validatePureNumberStr(heads[0]);
//        System.out.println("### 000000  valid head[0]: " + result);

        result = result && GeneralValidation.validatePureNumberStr(heads[1]);
//        System.out.println("###  010101111111 valid head[1]: " + result);

        result = result && GeneralValidation.validatePureNumberStr(heads[3]);
//        System.out.println("### 33333333333333333333  valid head[3]: " + result);

        result = result && GeneralValidation.validateDateTime(heads[2], "dd.MM.yyyy");
//        System.out.println("###  0202020222222222222222 valid head[2]: " + result);

        if (heads.length == 9) {
            result = result && GeneralValidation.validateDateTime(heads[5], "dd.MM.yyyy");
//            System.out.println("###  05050555555555555555555 valid head[5]: " + result);

            result = result && GeneralValidation.validateDateTime(heads[6], "HH:mm:ss");
//            System.out.println("### 06060666666666666666666666  valid head[6]: " + result);
        }
        // System.out.println("  #####-----  Boolean validHead " + result);
        return result;

    }

    /**
     * Tests the correct data in the header's second line in block
     *
     * @param str
     * @return
     */
    private boolean validHead1(String str) {
        boolean result = true;

        String[] heads = str.split("\\s");
        for (int k = 0; k < heads.length; k++) {
        }
//        System.out.println("    Boolean validHead1 " + result);
        return result;
    }

    //@Override
    public boolean validateBlockData(String strBlock) {
        if (strBlock == null) {
            return false;
        }
        strBlock = strBlock.trim();
        boolean result = true;
        String[] parts = strBlock.split(System.lineSeparator());
//        System.out.println("    7777777777777777777777              parts.length " + parts.length);
        if (parts.length < 2) {
            parts = strBlock.split("\\n");
        }
        if (parts.length < 2) {
            parts = strBlock.split("\\r");
        }
        //System.out.println("/*******************parts.length  " + parts.length);
        if (parts.length < 2) {
            return false;
        }
        result = result && validHead(parts[0]);
        // System.out.println("  555555555555555555555   validHead 0  " + result);
        if (!result) {
            return false;
        }
        result = result && validHead1(parts[1]);
        // System.out.println("     validHead 0" + result);

        for (int k = 2; k < parts.length; k++) {
            int p = 0;
            StringTokenizer st = new StringTokenizer(parts[k]);
            while (st.hasMoreTokens()) {
//                System.out.println("  p=" + p + " res=" + result);
//                if (!result) {
//                    System.out.println(" #######$$$$$# st=" + st);
//                }
                String tmp = st.nextToken();
                if (p == 0) {
                    result = result && GeneralValidation.validatePureNumberStr(tmp);
                }

                if (p == 1 || p == 2 || p == 5 || p == 6) {
                    result = result && GeneralValidation.validatePureDoubleStr(tmp);
                }
                if (p == 3) {
                    result = result && (tmp.charAt(0) == '-' || tmp.charAt(0) == '+');
                    tmp = GeneralValidation.exludeFirstCharacter(tmp);
                    result = result && GeneralValidation.validatePureDoubleStr(tmp);

                }

                p++;
            }
        }
        return result;
    }

}
