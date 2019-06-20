/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils;

/**
 *
 * @author uranium
 */
public class AdditionalObservationData {

    public double createAccur(String s) {
        double res = 0;
        s = s.substring(0, 3);
        try {
            res = Double.parseDouble(s);
        } catch (NumberFormatException e) {
        }
        return res;
    }

    public double createBrightness(String s) {
        double res = 0;
        s = s.substring(3);
        try {
            res = Double.parseDouble(s);
        } catch (NumberFormatException e) {
        }
        return res;
    }

}
