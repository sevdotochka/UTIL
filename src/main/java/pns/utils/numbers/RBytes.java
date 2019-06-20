/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.numbers;

/**
 *
 * @author jpdw
 */
public class RBytes {

// *******************  BYTES *******************
    /**
     * Gets random byte value
     *
     * @return
     */
    public static byte rndByte() {
        double r = Math.random();
        byte b = (byte) (-r * 128 + (1 - r) * 127);
        return b;
    }

    /**
     * Gets random byte value between min and max
     *
     * @param min of byte type
     * @param max of byte type
     * @return
     */
    public static byte rndByte(byte min, byte max) {
        double r = Math.random();
        byte b = (byte) (r * min + (1 - r) * max);
        return b;
    }

    /**
     * Get random byte array with random length. The length is limited by 100
     *
     * @return
     */
    public static byte[] rndByteArray() {
        int length = (int) (Integer.MAX_VALUE * Math.random());
        if (length > 100) {
            length = 100;
        }
        byte[] bs = new byte[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndByte();
        }
        return bs;
    }

    /**
     * Get random byte array with fixed length.
     *
     * @param length
     * @return
     */
    public static byte[] rndByteArray(int length) {
        byte[] bs = new byte[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndByte();
        }
        return bs;
    }

    /**
     * Get random byte array with random length.
     *
     * @param min
     * @param max
     * @return
     */
    public static byte[] rndByteArray(byte min, byte max) {
        int length = (int) (Integer.MAX_VALUE * Math.random());
        if (length > 100) {
            length = 100;
        }
        byte[] bs = new byte[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndByte(min, max);
        }
        return bs;
    }

    /**
     * Get random byte array with fixed length. the values are between min and
     * max
     *
     * @param length
     * @param min
     * @param max
     * @return
     */
    public static byte[] rndByteArray(int length, byte min, byte max) {
        byte[] bs = new byte[length];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = rndByte(min, max);
        }
        return bs;
    }

}
