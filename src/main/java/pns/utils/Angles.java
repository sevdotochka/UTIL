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
public class Angles {

    double deg = 0;
    double min = 0;
    double sec = 0;

    public double createRADegMinSec(String asc) {
        /*
       RA.deg  = 15*tempRES(j,7)+15*tempRES(j,8)/60+15*tempRES(j,9)/3600;  % угол прямого восхождения для текущего измерени (град)
       Dec.deg = sDec*(tempRES(j,10)+tempRES(j,11)/60+tempRES(j,12)/3600);
         */

        String HH = asc.substring(0, 2);
        String MM = asc.substring(2, 4);
        String SS = asc.substring(4, 6) + "." + asc.substring(6);

        double h = 0;
        double m = 0;
        double s = 0;

        try {
            h = Double.parseDouble(HH);
        } catch (NumberFormatException e) {
        }
        try {
            m = Double.parseDouble(MM);
        } catch (NumberFormatException e) {
        }
        try {
            s = Double.parseDouble(SS);
        } catch (NumberFormatException e) {
        }

        deg = 15 * h + 15 * m / 60 + 15 * s / 3600;
        double rad = Math.toRadians(deg);

        return rad;
    }

    public double createDeclaineDegMinSec(String asc) {
        double signDeg = 1;
        /*
       RA.deg  = 15*tempRES(j,7)+15*tempRES(j,8)/60+15*tempRES(j,9)/3600;  % угол прямого восхождения для текущего измерени (град)
       Dec.deg = sDec*(tempRES(j,10)+tempRES(j,11)/60+tempRES(j,12)/3600);
         */
        if (asc.codePointAt(0) == '-') {
            signDeg = -1;
        }
        String GG = asc.substring(1, 3);
        String MM = asc.substring(3, 5);
        String SS = asc.substring(5, 7) + "." + asc.substring(7);

        double g = 0;
        double m = 0;
        double s = 0;

        try {
            g = Double.parseDouble(GG);
        } catch (NumberFormatException e) {
        }
        try {
            m = Double.parseDouble(MM);
        } catch (NumberFormatException e) {
        }
        try {
            s = Double.parseDouble(SS);
        } catch (NumberFormatException e) {
        }

        deg = signDeg * (g + m / 60 + s / 3600);
        double rad = Math.toRadians(deg);

        return rad;
    }

}
