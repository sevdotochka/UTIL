/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.utils.numbers;

import static pns.utils.numbers.RBytes.rndByte;

/**
 *
 * @author User
 */
public class RChars {
// *******************  CHARS *******************

    /**
     * Gets random char value
     *
     * @return
     */
    public static char rndChar() {
	double r = Math.random();
	char b = (char) (-r * 128 + (1 - r) * 127);
	return b;
    }

    /**
     * Gets random char value between min and max
     *
     * @param min of char type
     * @param max of char type
     * @return
     */
    public static char rndChar(char min, char max) {
	double r = Math.random();
	byte b = (byte) (r * (byte) min + (1 - r) * (byte) max);
	return (char) b;
    }

    /**
     * Get random char array with random length. The length is limited by 100
     *
     * @return
     */
    public static char[] rndCharArray() {
	int length = (int) (Integer.MAX_VALUE * Math.random());
	if (length > 100) {
	    length = 100;
	}
	char[] bs = new char[length];
	for (int i = 0; i < bs.length; i++) {
	    bs[i] = (char) rndByte();
	}

	return bs;
    }

    /**
     * Get random char array with fixed length.
     *
     * @param length
     * @return
     */
    public static char[] rndCharArray(int length) {
	char[] bs = new char[length];
	for (int i = 0; i < bs.length; i++) {
	    bs[i] = (char) rndByte();
	}

	return bs;
    }

    /**
     * Get random char array with random length.
     *
     * @param min
     * @param max
     * @return
     */
    public static char[] rndCharArray(char min, char max) {
	int length = (int) (Integer.MAX_VALUE * Math.random());
	if (length > 200) {
	    length = 200;
	}
	char[] bs = new char[length];
	for (int i = 0; i < bs.length; i++) {
	    bs[i] = (char) rndByte((byte) min, (byte) max);
	}

	return bs;
    }

    /**
     * Get random char array with fixed length. the values are between min and
     * max
     *
     * @param length
     * @param min
     * @param max
     * @return
     */
    public static char[] rndCharArray(int length, char min, char max) {
	char[] bs = new char[length];
	for (int i = 0; i < bs.length; i++) {
	    bs[i] = (char) rndByte((byte) min, (byte) max);
	}

	return bs;
    }

}
