/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.parsers;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author kvaziuser
 */
public class ParserRESStrings extends AbstractParserString {

    public ParserRESStrings() {
    }

    public ParserRESStrings(String dataToParse) {
        super(dataToParse);
    }

    private void reCodingTest() {

        try {
//            System.out.println("************START RUS:    " + kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF);
            String utfStart = new String(kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF.getBytes("utf-8"));
            System.out.println("utfStart " + utfStart);
            String cpStart = new String(kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF.getBytes("cp1251"));
//            System.out.println("cpStart " + cpStart);
//            System.out.println("************END RUS:    " + kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF);
            String utfEnd = new String(kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF.getBytes("utf-8"));
            System.out.println("utfEnd " + utfEnd);
            String cpEnd = new String(kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF.getBytes("cp1251"));
            System.out.println("cpEnd " + cpEnd);
        } catch (Exception ee) {
        }

    }

    private String[] parsingByStart(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.length() == 0) {
            return null;
        }
        String[] res = new String[0];
        String cpStart = new String(kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF.getBytes("cp1251"));
        String cpEnd = new String(kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF.getBytes("cp1251"));
        res = str.split(cpEnd);
        if (res.length < 2) {
            res = str.split(kiam.AppConstants.BLOCKS_BREAKER_END_LAT_BRIEF);
        }
        if (res.length > 1) {
            return res;
        }
        return null;
    }

    @Override
    public boolean haveCharsSeparator(String str) {

        str = "   " + str;
        str = str.toLowerCase();
        boolean hasRus = false;
        boolean hasRusStart = false;
        boolean hasRusEnd = false;
        boolean hasLat = false;
        String rusStart1251 = "";
        String rusEnd1251 = "";
        try {
            String ss1251 = new String(str.getBytes(), "cp1251");
            String ssutf8 = new String(str.getBytes(), "utf-8");
            rusStart1251 = new String(kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF.toLowerCase().getBytes("cp1251"), "cp1251");
            rusEnd1251 = new String(kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF.toLowerCase().getBytes(), "cp1251");
            hasRusStart = str.contains(rusStart1251) || str.contains(kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF.toLowerCase());
            hasRusEnd = str.contains(rusEnd1251) || str.contains(kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF.toLowerCase());
            hasRus = hasRusEnd && hasRusStart;
//
//            if (!hasRus) {
//                System.out.println("NO RUS str: " + str.trim().substring(0, 10) + "  ss1251 " + ss1251.trim().substring(0, 10) + "    ssutf8: " + ssutf8.trim().substring(0, 10));
//            }
        } catch (UnsupportedEncodingException ex) {
        }
        hasLat = str.contains(kiam.AppConstants.BLOCKS_BREAKER_END_LAT_BRIEF.toLowerCase());
        System.out.println("  -- First 30 chars of block:  " + str.trim().substring(0, 30));
        //System.out.println("Validating RES Language:   hasLat/hasRus   " + hasLat + "  /  " + hasRus);
        boolean ress = hasLat || hasRus;

//        System.out.println(" $$$$$$$$$$$$$$$$--->>> ress-------------->>>ress  " + ress);
        return ress;
    }

    /**
     * returns the k-th block Content
     *
     * @param k
     * @return
     */
    @Override
    public void blockGenerator(String str) {
        dataBlocks.clear();
        if (str == null) {
            dataBlocks = null;
            return;
        }
        str = str.trim();
        if (str.length() == 0) {
            dataBlocks = null;
            return;
        }
//        System.out.println("       RES      before  #################===BBBBBBBB   dataBlocks.size() " + dataBlocks.size());
        str = str.replace(kiam.AppConstants.BLOCKS_BREAKER_END_LAT_BRIEF, System.lineSeparator() + System.lineSeparator() + "XXXXXX" + System.lineSeparator());
        str = str.replace(kiam.AppConstants.BLOCKS_BREAKER_END_RUS_BRIEF, System.lineSeparator() + System.lineSeparator() + "XXXXXX" + System.lineSeparator());
        str = str.replace(kiam.AppConstants.BLOCKS_BREAKER_START_RUS_BRIEF, System.lineSeparator() + System.lineSeparator() + System.lineSeparator());

        String[] sArray = str.split("XXXXXX");
//        System.out.println("     NNNNNNNNNNNNNNNNN MMMMMMMMMMMMMMMMM   " + str);
        for (int k = 0; k < sArray.length; k++) {
            String line = sArray[k].trim();
            if (line.length() > 0) {
                dataBlocks.add(line);
            }
        }
//        System.out.println("       after      before  #################===BBBBBBBB   dataBlocks.size() " + dataBlocks.size());
//        System.out.println("  ");
//        System.out.println("  ");
//        System.out.println("  ");
    }

    /**
     * Outputs to the Screen the blocks content
     */
    public void outputAllBlocs() {
        for (int k = 0; k < dataBlocks.size(); k++) {
//            System.out.println(getDataBlocks(k));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isValidDataBlocks() {
        //System.out.println("    isValidDataBlocks ####$$$################@@@@@@@@@  (dataBlocks == null)   " + (dataBlocks == null));
        if (dataBlocks == null) {
            return false;
        }
        boolean result = (dataBlocks.size() > 0);
        TestValidRESString testValidStr = new TestValidRESString();

//        System.out.println("  ESSSSSSSSS:::::::::::::::  " + result);
        if (!result) {
            System.out.println("    Number of blocs: " + dataBlocks.size());
            return false;
        }
        for (int k = 0; k < dataBlocks.size(); k++) {
            String block = dataBlocks.get(k);
            boolean tmp = testValidStr.validateBlockData(block);
//            System.out.println(k + "  ::::::::::::::::::::::: " + tmp + " block: " + block);
            result = result && tmp;
            if (!result) {
                //System.out.println(" Error in Block #   " + k);
                //System.out.println(" Defective block:  " + block);
            }
            // System.out.println("      " + tmp + " / " + result);

        }
        if (result) {
            attr = "RES";
        }
//        System.out.println("   EEEEEEEEEEEEEEEEEEEEE  EEEEEEEEEEEEEEEEEEEEEEEEEEEE::::       " + result);
        return result;
    }

}
