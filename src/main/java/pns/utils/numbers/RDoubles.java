/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.numbers;

import java.util.List;

/**
 *
 * @author PSEVDO tochka
 */
public class RDoubles {

    public static double rndDbl(double min, double max) {
        double res = Math.random();
        return res * min + (1 - res) * max;
    }

    public static double rndDbl() {
        double res = Math.random();
        return -res * Double.MAX_VALUE + (1 - res) * Double.MAX_VALUE;
    }

    public static double[] rndVec(int size) {
        double[] rr = new double[size];
        for (int k = 0; k < size; k++) {
            double res = Math.random();
            rr[k] = res;
        }
        return rr;
    }

    public static double[] rndVec() {
        int size = RInts.rndInt();
        size = Math.abs(size);
        double[] rr = new double[size];
        for (int k = 0; k < size; k++) {
            double res = Math.random();
            rr[k] = res;
        }
        return rr;
    }

    public static double normL2(double[] v) {
        double N = 0;
        for (int k = 0; k < v.length; k++) {
            N += v[k] * v[k];
        }
        return Math.sqrt(N);
    }

    public static double normL1(double[] v) {
        double N = 0;
        for (int k = 0; k < v.length; k++) {
            N += Math.abs(v[k]);
        }
        return N;
    }

    public static double normLP(double[] v, double p) {
        if (p > 0) {
            double N = 0;
            for (int k = 0; k < v.length; k++) {
                N += Math.pow(v[k], p);
            }
            double q = 1 / p;
            return Math.pow(N, q);
        }
        return -1;
    }

    public static double[] rndUnicVec1(int size) {
        double[] v = rndVec(size);
        double N = normL1(v);
        for (int k = 0; k < v.length; k++) {
            v[k] = v[k] / N;
        }
        return v;
    }

    public static double[] rndUnicVec2(int size) {
        double[] v = rndVec(size);
        double N = normL2(v);
        for (int k = 0; k < v.length; k++) {
            v[k] = v[k] / N;
        }
        return v;
    }

    public static double[] rndUnicVecP(int size, double p) {
        double[] v = rndVec(size);
        double N = normLP(v, p);
        for (int k = 0; k < v.length; k++) {
            v[k] = v[k] / N;
        }
        return v;
    }

    public static double[] rndUnicVec1() {
        int size = RInts.rndInt();
        double[] v = rndVec(size);
        double N = normL1(v);
        for (int k = 0; k < v.length; k++) {
            v[k] = v[k] / N;
        }
        return v;
    }

    public static double[] rndUnicVec2() {
        int size = RInts.rndInt();
        double[] v = rndVec(size);
        double N = normL2(v);
        for (int k = 0; k < v.length; k++) {
            v[k] = v[k] / N;
        }
        return v;
    }

    public static double[] rndUnicVecP(double p) {
        int size = RInts.rndInt();
        double[] v = rndVec(size);
        double N = normLP(v, p);
        for (int k = 0; k < v.length; k++) {
            v[k] = v[k] / N;
        }
        return v;
    }

    public double[] toDblArray(List<Double> lst) {
        double[] res = new double[lst.size()];
        for (int k = 0; k < lst.size(); k++) {
            res[k] = lst.get(k);
        }
        return res;
    }

}
