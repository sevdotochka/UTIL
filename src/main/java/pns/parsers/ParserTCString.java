/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.parsers;

/**
 *
 * @author User
 */
public class ParserTCString extends AbstractParserString {

    public ParserTCString() {
    }

    public ParserTCString(String dataToParse) {
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
        str = str.trim();
//        System.out.println("   WWWWWWWWWWWWWWWWWWWWWWW  " + str);
        String[] parts = str.split("TCMSM\\.");
//        System.out.println("SSSSSS WWWWWWWWWWWWWWWWWWWWWWWWSSSSSSSSSSSSSSSSSSS  parts.length  " + parts.length);

//        System.out.println("     TCMSM  --- before  SSSSSSSSSSSSSSSSSSSSSOOOOOOOOOOOOOOOOO   dataBlocks.size() " + dataBlocks.size());
        for (int k = 0; k < parts.length; k++) {
            String s = parts[k].trim();
            if (s.trim().length() != 0) {
                dataBlocks.add(s);
            }
        }
//        System.out.println("     TCMSM  --- after  SSSSSSSSSSSSSSSSSSSSSOOOOOOOOOOOOOOOOO   dataBlocks.size() " + dataBlocks.size());
//        System.out.println("  ");
//        System.out.println("  ");
//        System.out.println("  ");
    }

    @Override
    public boolean isValidDataBlocks() {
        if (dataBlocks == null) {
            return false;
        }
        boolean result = (dataBlocks.size() > 0);
//        System.out.println("    TCDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD dataBlocks.size()  " + dataBlocks.size());
        if (!result) {
            return false;
        }
        TestValidTCMString testValidStr = new TestValidTCMString();
        for (int k = 0; k < dataBlocks.size(); k++) {
//            System.out.println("BB@#######--   ");
            String block = dataBlocks.get(k);
//            System.out.println(" block: " + block);
            boolean tmp = testValidStr.validateBlockData(block);
//            System.out.println(k + "  TCTC---CTCTCTCTC:::VALIDATE RESULT  " + tmp);
            if (!tmp) {
                //   System.out.println(k + "  block:  " + block);
                return false;
            }
            result = result && tmp;
//            System.out.println("      " + tmp + "/" + result);

        }
        if (result) {
            attr = "TC";
        }

        //System.out.println("    TCEEEEEEEEEEEE  " + result);
        return result;
    }

}
