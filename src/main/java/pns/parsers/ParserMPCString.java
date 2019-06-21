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
public class ParserMPCString extends AbstractParserString {

    private final String COD = "COD";
    private final String OBS = "OBS";
    private final String MEA = "MEA";
    private final String TEL = "TEL";
    private final String ACK = "ACK";
    private final String AC2 = "AC2";
    private final String NET = "NET";

    public ParserMPCString() {
    }

    public ParserMPCString(String dataToParse) {
        super(dataToParse);
    }

    @Override
    public boolean haveCharsSeparator(String str) {
        return true;
    }

    @Override
    public void blockGenerator(String str) {
        dataBlocks.clear();
        if (str == null) {
            dataBlocks = null;
            return;
        }
        if (str.length() == 0) {
            dataBlocks = null;
            return;
        }
        str.trim();
        boolean isCorrectRecord = true;
        String[] records = str.split(System.lineSeparator());
        for (int k = 0; k < records.length; k++) {
            String line = records[k].trim();
            boolean tmpH = isCorrectHead(line);
            boolean tmpD = testCorrectString(line);
            if (tmpD) {
                dataBlocks.add(line);
            }
//            System.out.println(k + ": " + line + " tmpH " + tmpH + "  tmpD " + tmpD);
//            System.out.println("   --- line.length(): " + line.length() + "   tmpD " + tmpD);

        }
//        System.out.println(" dataBlocks.size() " + dataBlocks.size());
    }

    private boolean isCorrectHead(String str) {
        boolean result = true;
        int p = 0;
        String[] heads = str.split("\\s");

        //for(int k = 0; k<heads.length; k++){
        if (heads.length > 0) {
            String tmp = heads[0].trim();
            result
                    = tmp.equals(this.AC2) || tmp.equals(this.ACK)
                    || tmp.equals(this.COD) || tmp.equals(this.MEA)
                    || tmp.equals(this.NET) || tmp.equals(this.OBS) || tmp.endsWith(this.TEL);
            //}
        }
        return result;
    }

    private boolean testCorrectString(String str) {
        if (str == null) {
            return false;
        }
        boolean result = str.length() > 0;
        int p = 0;
        StringTokenizer st = new StringTokenizer(str);
        //System.out.println("      str  " + str + "  " + st.countTokens());
        result = result && st.countTokens() > 3;
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken().trim();
            //System.out.println(p + " tmp:  " + tmp + ";  tmp.length():  " + tmp.length());
            //result = result && tmp.length() > 0;
            if (p == 1) {
                result = result && tmp.length() > 4;
                tmp = GeneralValidation.exludeFirstCharacter(tmp);
                result = result && GeneralValidation.validateDateTime(tmp, "YYYY");
            }
            if (p == 2 || p == 4) {
                result = result && GeneralValidation.validatePureNumberStr(tmp);
//                    System.out.println(p + "  tmp: " + tmp + "   : " + isCorrectRecord);

            }
            if (p == 3 || p == 9 || p == 10) {
                result = result && GeneralValidation.validatePureDoubleStr(tmp);
            }

            if (p == 8) {
                result = result
                        && (GeneralValidation.validatePureNumberStr(tmp)
                        || GeneralValidation.validatePureDoubleStr(tmp));
            }

            if (p == 11) {
                result = result && (tmp.length() == 1);
            }
            if (p == 12) {
                tmp = GeneralValidation.exludeFirstCharacter(tmp);
                result = result && GeneralValidation.validatePureNumberStr(tmp);
            }

            p++;
        }
        if (result) {
            attr = "RES";
        }
        return result;
    }

    private boolean testStr(String str) {
        boolean result = true;
        int p = 0;
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            System.out.println(p + " " + tmp);
            if (p == 1) {
                tmp = GeneralValidation.exludeFirstCharacter(tmp);
                result = result && GeneralValidation.validateDateTime(tmp, "YYYY");
                //setNULLDataBlocs(isCorrectRecord);
            }
            if (p == 2 || p == 4) {
                result = result && GeneralValidation.validatePureNumberStr(tmp);
//                    System.out.println(p + "  tmp: " + tmp + "   : " + isCorrectRecord);
                //setNULLDataBlocs(isCorrectRecord);
            }
            if (p == 3 || p == 9 || p == 10) {
                result = result && GeneralValidation.validatePureDoubleStr(tmp);
                System.out.println("  isCorrectRecord      " + result);
                //setNULLDataBlocs(isCorrectRecord);
            }
            if (p == 3 || p == 9 || p == 10) {
                result = result && GeneralValidation.validatePureDoubleStr(tmp);
                System.out.println("  isCorrectRecord      " + result);

            }
            if (p == 8) {
                result = result
                        && (GeneralValidation.validatePureNumberStr(tmp)
                        || GeneralValidation.validatePureDoubleStr(tmp));
                //setNULLDataBlocs(isCorrectRecord);
            }

            if (p == 11) {
                result = result && (tmp.length() == 1);
            }
            if (p == 12) {
                tmp = GeneralValidation.exludeFirstCharacter(tmp);
                result = result && GeneralValidation.validatePureNumberStr(tmp);
                //setNULLDataBlocs(isCorrectRecord);
            }

            p++;
        }

        if (result) {
            attr = "MPC";
        }
        return result;
    }

    @Override
    public String getDataToParse() {
        return super.getDataToParse(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValidDataBlocks() {
        if (dataBlocks == null) {
            return false;
        }
//        System.out.println("   dataBlocks.size()  " + dataBlocks.size());
        //System.out.println("   dataBlocks.size()  " + dataBlocks.get(0));
        boolean result = (dataBlocks.size() > 0);

//        TestMPCString testValidStr = new TestMPCString();
//        for (int k = 0; k < dataBlocks.size(); k++) {
//            String block = dataBlocks.get(k);
//            result = result && testValidStr.validateBlockData(block);
//            System.out.println(k + "     " + block);
//
//        }
        return result;
    }

}
