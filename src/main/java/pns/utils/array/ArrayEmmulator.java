/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Movement
 */
public class ArrayEmmulator {

    public static List<Double> doubleValuesEmmulatorList(double from, double to, double step) {
        List<Double> preRes = new ArrayList<>();
        double x = from;
        System.out.println("x= " + x);
        while (x <= to) {
            double rnd = pns.utils.numbers.RDoubles.rndDbl(x, x + step);
            preRes.add(rnd);
            x += step;
        }
        return preRes;
    }

    public static double[] doubleValuesEmmulator(double from, double to, double step) {
        List<Double> preRes = new ArrayList<>();
        double x = from;
        System.out.println("x= " + x);
        while (x <= to) {
            double rnd = pns.utils.numbers.RDoubles.rndDbl(x, x + step);
            preRes.add(rnd);
            x += step;
        }
        double[] res = new double[preRes.size()];
        for (int k = 0; k < res.length; k++) {
            res[k] = preRes.get(k);
        }
        return res;
    }

    public static double[] doubleValuesSimpleEmmulator(double from, double to, double step) {
        List<Double> preRes = new ArrayList<>();
        double x = from;

        while (x < to) {
            preRes.add(x);
            x += step;
        }
        double[] res = new double[preRes.size()];
        for (int k = 0; k < res.length; k++) {
            res[k] = preRes.get(k);
        }
        return res;
    }

    public static double[] doubleValuesEmmulator(double from, double to, int count) {
        double step = (to - from) / count;
        return doubleValuesEmmulator(from, to, step);
    }

    public static double[] doubleValuesSympleEmmulator(double from, double to, int count) {
        double step = (to - from) / count;
        return doubleValuesSimpleEmmulator(from, to, step);
    }

    public static double[] doubleValuesEmmulator(int count) {
        double[] res = new double[count];
        for (int k = 0; k < count; k++) {
            res[k] = pns.utils.numbers.RDoubles.rndDbl();
        }
        return res;
    }
}
