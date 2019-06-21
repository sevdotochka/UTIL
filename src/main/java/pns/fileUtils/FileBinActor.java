/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.fileUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class FileBinActor extends FileActor {

    /**
     * запись в бинарный файл байтового массива
     *
     * @param fileName
     * @param data
     * @return
     */
    public boolean writeFile(String fileName, byte[] data) {

        if (fileName.length() * data.length == 0) {
            return false;
        }
        File f = new File(fullFileDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        OutputStream output = null;

        try {
            output = new BufferedOutputStream(new FileOutputStream(fileName));
            output.write(data);
            System.out.println(" file " + f.getAbsolutePath());

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                //Logger.getLogger(FileBinActor.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        //return false;
    }

    /**
     * запись в бинарный файл объекта
     *
     * @param fileName
     * @param data
     * @return
     */
    public boolean writeFile(String fileName, Object data) {

        if (fileName.length() == 0) {
            return false;
        }

        File f = new File(fileName).getParentFile();
        if (!f.exists()) {
            f.mkdirs();
        }
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(fileName);

            ObjectOutputStream objectOut = new ObjectOutputStream(output);
            objectOut.writeObject(data);
            objectOut.close();

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {

            return true;
        }
        //return false;
    }

    /**
     * чтение их бинарного файла данных
     *
     * @param fileName
     * @return
     */
    public Object readBinFile(String fileName) {

        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            return oi.readObject();

        } catch (FileNotFoundException ex) {
            System.out.println("+--- " + ex);
        } catch (IOException ex) {
            System.out.println("+-----+- " + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileBinActor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
