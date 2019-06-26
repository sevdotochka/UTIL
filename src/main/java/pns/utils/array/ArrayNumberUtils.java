/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Movement
 */
public class ArrayNumberUtils {

    public static double[] sortArray(double[] args) {
        Arrays.sort(args);
        return args;
    }

    public static int[] sortArray(int[] args) {
        Arrays.sort(args);
        return args;
    }

    public static byte[] sortArray(byte[] args) {
        Arrays.sort(args);
        return args;
    }

    public static float[] sortArray(float[] args) {
        Arrays.sort(args);
        return args;
    }

    public static double[] sliceArray(double[] args, int from, int to) {
        List<Double> argLst = new ArrayList<>();
        for (int k = 0; k < args.length; k++) {
            if (k >= from && k < to) {
                argLst.add(args[k]);
            }
        }
        double[] res = new double[argLst.size()];
        for (int k = 0; k < argLst.size(); k++) {
            res[k] = argLst.get(k);
        }
        return res;
    }

    public static double[] addMidlePT(double[] args) {

        List<Double> argLst = new ArrayList<>();
        for (double d : args) {
            argLst.add(d);
        }

        int K = argLst.size() - 1;
        for (int k = 1; k < K; k++) {
            argLst.add((argLst.get(k - 1) + argLst.get(k + 1)) / 2);
        }
        Collections.sort(argLst);

        double[] argsD = new double[argLst.size()];
        for (int kD = 0; kD < argsD.length; kD++) {
            argsD[kD] = argLst.get(kD);
        }
        System.out.println(" end argLst  " + argLst);

        return argsD;
    }

    public static double[] breakArr(double[] args, int start, int len) {
        double[] res = Arrays.copyOfRange(args, start, len);
        return res;
    }

    public static double makeSumm(double[] args) {
        double res = 0;
        for (double d : args) {
            res += d;
        }
        return res;
    }
}
