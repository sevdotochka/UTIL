/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.fileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class GenerateLists {

    /**
     * String Collection Generator from given string Array
     *
     * @param strs
     * @return
     */
    public static Collection<String> toSet(String[] strs) {
        Set<String> res = new HashSet<>();
        Collection cc = Arrays.asList(strs);
        res.addAll(cc);
        for (String ss : strs) {
            res.add(ss.trim());
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    public static Collection<String> generateTokens(String s) {
        Collection<String> res = new ArrayList<>();
        StringTokenizer stTokenizer = new StringTokenizer(s);
        while (stTokenizer.hasMoreTokens()) {
            res.add(stTokenizer.nextToken());
        }
        return res;
    }

    /**
     * String Collection Generator from given string Array
     *
     * @param strs
     * @return
     */
    public static Collection<String> toList(String[] strs) {
        Collection<String> res = new ArrayList<>();
        Collection cc = Arrays.asList(strs);
        res.addAll(cc);
        for (String ss : strs) {
            res.add(ss.trim());
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    /**
     * Byte Collection Generator from given byte Array
     *
     * @param strs
     * @return
     */
    public static Collection<Byte> toList(Byte[] strs) {
        Collection<Byte> res = new ArrayList<>();
        for (Byte ss : strs) {
            res.add(ss);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    /**
     * Integer Collection Generator from given integer Array
     *
     * @param strs
     * @return
     */
    public static Collection<Integer> toList(Integer[] strs) {
        Collection<Integer> res = new ArrayList<>();
        for (Integer ss : strs) {
            res.add(ss);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    /**
     * Long Collection Generator from given long Array
     *
     * @param strs
     * @return
     */
    public static Collection<Long> toList(Long[] strs) {
        Collection<Long> res = new ArrayList<>();
        for (Long ss : strs) {
            res.add(ss);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    /**
     * PrintOut collection of Objects
     *
     * @param co
     */
    public static void outputCollection(Collection<Object> co) {
        int k = 0;
        for (Object oo : co) {
            System.out.println(k + ":     length " + oo.toString().length() + System.lineSeparator() + oo.toString());
            k++;
        }
        System.out.println("    Collection's length: " + k);
    }

    /**
     * PrintOut collection of Objects in Short
     *
     * @param co
     */
    public static void outputCollectionShort(Collection<Object> co) {
        int k = 0;
        for (Object oo : co) {
            System.out.println(oo.toString());
            k++;
        }
    }

}
