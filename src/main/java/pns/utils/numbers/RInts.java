/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.numbers;

import java.nio.ByteBuffer;

/**
 *
 * @author User
 */
public class RInts {

// *******************  INTEGERS *******************
    /**
     * Gets random int value
     *
     * @return
     */
    public static int rndInt() {
        double r = Math.random();
        int b = (int) (-r * Integer.MAX_VALUE + (1 - r) * Integer.MAX_VALUE);
        return b;
    }

    /**
     * Gets random long value
     *
     * @return
     */
    public static long rndLong() {
        double r = Math.random();
        long b = (long) (-r * Long.MAX_VALUE + (1 - r) * Long.MAX_VALUE);
        return b;
    }

    /**
     * Gets random int value between min and max
     *
     * @param min of int type
     * @param max of int type
     * @return
     */
    public static int rndInt(int min, int max) {
        double r = Math.random();
        int b = (int) (r * min + (1 - r) * max);
        return b;
    }

    /**
     * Gets random int value between min and max
     *
     * @param min of int type
     * @param max of int type
     * @return
     */
    public static long rndLong(long min, long max) {
        double r = Math.random();
        long b = (long) (r * min + (1 - r) * max);
        return b;
    }

    /**
     * Get random int array with random length. The length is limited by 100
     *
     * @return
     */
    public static int[] rndIntArray() {
        int length = (int) (Integer.MAX_VALUE * Math.random());
        if (length > 100) {
            length = 100;
        }
        int[] bs = new int[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndInt();
        }
        return bs;
    }

    /**
     * Get random int array with fixed length.
     *
     * @param length
     * @return
     */
    public static int[] rndIntArray(int length) {
        int[] bs = new int[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndInt();
        }
        return bs;
    }

    /**
     * Get random int array with random length.
     *
     * @param min
     * @param max
     * @return
     */
    public static int[] rndIntArray(int min, int max) {
        int length = (int) (Integer.MAX_VALUE * Math.random());
        if (length > 100) {
            length = 100;
        }
        int[] bs = new int[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndInt(min, max);
        }
        return bs;
    }

    /**
     * Get random int array with fixed length. the values are between min and
     * max
     *
     * @param length
     * @param min
     * @param max
     * @return
     */
    public static int[] rndIntArray(int length, int min, int max) {
        int[] bs = new int[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndInt(min, max);
        }
        return bs;
    }

}
