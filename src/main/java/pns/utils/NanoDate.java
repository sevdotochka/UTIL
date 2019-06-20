/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author uranium
 */
public class NanoDate implements Comparable, Serializable {

    private double nanoDateValue = 0;
    private String tmpMoment = "";
    private String mantissa = "";
    private Date nd = new Date();

    public NanoDate() {
    }

    public double getNanoDateValue() {
        return nanoDateValue;
    }

    public String getMantissa() {
        return mantissa;
    }

    public void setMantissa(String mantissa) {
        this.mantissa = mantissa;
    }

    public void setNanoDateValue(double nanoDateValue) {
        this.nanoDateValue = nanoDateValue;
    }

    public void setTmpMoment(String tmpMoment) {
        this.tmpMoment = tmpMoment;
    }

    public void setNanoDateValue() throws ParseException {
        long sec = 0;

        //14
        String tmpTime = tmpMoment.substring(0, 14);
        //System.out.println("    tmpTime  " + tmpTime);

        mantissa = tmpMoment.substring(14);
        while (mantissa.length() < 6) {
            mantissa += "0";
        }
        // System.out.println("    mantissa " + mantissa);

        nd = new SimpleDateFormat("ddMMyy  HHmmss").parse(tmpTime);
        //System.out.println("    nd  " + nd);

        // System.out.println("   exactDate  " + nd.getTime());
        sec = nd.getTime() / 1000;
        //  System.out.println("   sec   " + sec);

        String tmpNano = sec + "." + mantissa;
        //  System.out.println("   tmpNano  " + tmpNano);

        nanoDateValue = Double.parseDouble(tmpNano);
        // System.out.println("   nanodateValue " + nanoDateValue);

    }

    @Override
    public String toString() {

        String rep = dateTimeMechanism.convertLongToDateStr(nd.getTime(), "yy-MM-dd HH:mm:ss") + "." + mantissa;
        // String rep = tmpMoment;
        return rep;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof NanoDate) {
            NanoDate nd = (NanoDate) o;
            double tmp = nanoDateValue - nd.getNanoDateValue();
            if (tmp < 0) {
                return -1;
            } else if (tmp > 0) {
                return 1;
            } else {
                return 0;
            }

        }
        return 1000;
    }

}
