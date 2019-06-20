/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.numbers;

/**
 *
 * @author User
 */
public class HashMaker {

    private WrapByBytes wrapByBytes = new WrapByBytes();

    public long calcHash(String c, long M, long R0, long R1) {
        long R = R0;
        long res = 0;
        int cnt = 0;
        byte[] b = c.getBytes();
        for (byte bb : b) {
            cnt++;
            int k = bb & 0xff;
            if (cnt % 2 == 0) {
                R = R1;
            }
            res = ((R * res) + k * b.length) % M;
        }
        res = wrapByBytes.toLong(wrapByBytes.toByteArray(res));
        return res + b.length;
    }

    public int crcGenerator(byte[] bts, int mod) {
        int res = 0;
        for (byte b : bts) {
            res += b & 0xff;
        }
        res = Math.abs(res);
        return res % mod;
    }

    public long crcGenerator(byte[] bts, long mod) {
        int res = 0;
        for (byte b : bts) {
            res += b & 0xff;
        }
        res = Math.abs(res);
        return res % mod;
    }

}
