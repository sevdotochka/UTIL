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
public class RConverter {

    /**
     * Creating a byte array from Double value
     *
     * @param value
     * @return
     */
    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    /**
     * Creating a byte array from Long value
     *
     * @param value
     * @return
     */
    public static byte[] toByteArray(long value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(value);
        return bytes;
    }

    /**
     * Creating a byte array from Integer value
     *
     * @param value
     * @return
     */
    public static byte[] toByteArray(int value) {
        byte[] bytes = new byte[4];
        ByteBuffer.wrap(bytes).putInt(value);
        return bytes;
    }

    /**
     * Creating a byte array from Short value
     *
     * @param value
     * @return
     */
    public static byte[] toByteArray(short value) {
        byte[] bytes = new byte[2];
        ByteBuffer.wrap(bytes).putShort(value);
        return bytes;
    }

    /**
     * Creating double number from a byteArray
     *
     * @param bytes
     * @return
     */
    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

    /**
     * Creating long number from a byteArray
     *
     * @param bytes
     * @return
     */
    public static long toLong(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getLong();
    }

    /**
     * Creating Integer number from a byteArray
     *
     * @param bytes
     * @return
     */
    public static int toInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    /**
     * Creating Integer number from a byteArray
     *
     * @param bytes
     * @return
     */
    public static short toShort(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getShort();
    }

    public static String toHex(byte[] bytes) {
        StringBuffer stBuf = new StringBuffer();
        for (byte b : bytes) {
            long k = b & 0xffff;
            String tmp = Long.toHexString(k);
            stBuf.append(tmp);
        }
        return stBuf.toString();
    }
}
