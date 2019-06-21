/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.parsers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
abstract public class AbstractParserString {

    protected ArrayList<String> dataBlocks = new ArrayList<String>();
    protected String dataToParse = "";
    protected String attr = "";

    /**
     *
     */
    public AbstractParserString() {
    }

    /**
     * Sets the String to parse
     *
     * @param dataToParse
     */
    public AbstractParserString(String dataToParse) {
        this.dataToParse = dataToParse;
    }

    public String getDataToParse() {
        return dataToParse;
    }

    public ArrayList<String> getDataBlocks() {
        //System.out.println(k + " " + dataBlocks.get(k));
        return dataBlocks;

    }

    public void setDataBlocks(ArrayList<String> dataBlocks) {
        this.dataBlocks = dataBlocks;
    }

    /**
     * returns the k-th block Content
     *
     * @param k
     * @return
     */
    public String getDataBlocks(int k) {
        if (dataBlocks == null) {
            return null;
        }
//        System.out.println(k + " " + (dataBlocks == null));
        if (k < dataBlocks.size()) {
            return dataBlocks.get(k);
        } else {
            return null;
        }

    }

    public String getBlocks() {
        String result = "";
        for (String s : dataBlocks) {
            result += s + System.lineSeparator();
        }
        return result;
    }

    /**
     * On the given input String str generates the block's data
     *
     * @param str
     */
    public void blockGenerator(String str) {
    }

    /**
     * validates the correction of the block's data
     *
     * @return
     */
    public boolean isValidDataBlocks() {
        return false;
    }

    public String getAttr() {
        return attr;
    }

    abstract public boolean haveCharsSeparator(String str);
}
