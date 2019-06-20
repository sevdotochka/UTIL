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
public class WrapByBytes {

    public int hugePrime(int dim) {
        int res = 2 * 3 * 5 * 7 * 11;
        res = res * 13 * 17 * 19 * 23;

        return res + 1;

    }

    public byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public byte[] toByteArray(long value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public long toLong(byte[] bytes) {

        return ByteBuffer.wrap(bytes).getLong();
    }
}
