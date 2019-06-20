/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.fileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author kvaziuser
 */
public class FileSpecActor extends FileActor {

    /**
     * Formatting the directory path in DATE/TIME style, using the string
     * parameter.
     * <strong>formatStrTmplate must be a string, used by SimpleDateFormat
     * class</strong>
     *
     * @param formatStrTmplate
     * @return
     */
    public String setTemplateTimePath(String formatStrTmplate) {
        isFormatedPath = true;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStrTmplate);
        return dateFormat.format(currentDate);
    }

    /**
     * Formatting the directory path in DATE/TIME style, using the string
     * parameter.
     * <strong>formatStrTmplate must be a string, used by SimpleDateFormat
     * class</strong> <br />
     * The second boolean parameter indicates, that it's nessesary to reduce the
     * time to the UTC Time Zone
     *
     * @param formatStrTmplate
     * @param UTC
     * @return
     */
    public String setTemplateTimePath(String formatStrTmplate, boolean UTC) {
        isFormatedPath = true;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStrTmplate);
        if (UTC) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return dateFormat.format(currentDate);
    }

    /**
     * Formatting the directory path in DATE/TIME style, using the string
     * parameter.
     * <strong>formatStrTmplate must be a string, used by SimpleDateFormat
     * class</strong>
     *
     * @param formatStrTmplate
     * @return
     */
    public String setTemplateTimeFileName(String formatStrTmplate) {
        isFormatedFileName = true;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStrTmplate);
        return dateFormat.format(currentDate);
    }

    /**
     * Formatting the directory path in DATE/TIME style, using the string
     * parameter.
     * <strong>formatStrTmplate must be a string, used by SimpleDateFormat
     * class</strong> <br />
     * The second boolean parameter indicates, that it's nessesary to reduce the
     * time to the UTC Time Zone
     *
     * @param formatStrTmplate
     * @param UTC
     * @return
     */
    public String setTemplateTimeFileName(String formatStrTmplate, boolean UTC) {
        isFormatedFileName = true;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStrTmplate);
        if (UTC) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return dateFormat.format(currentDate);
    }

    /**
     * Concatinate the system's root Node with given dirName
     *
     * @param fileDir
     */
    @Override
    public void setFileDir(String fileDir) {
        super.setFileDir(fileDir); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("          Given fileDir: " + fileDir + "                    super.fileDir: " + super.fileDir);
        if (isFormatedPath) {
            String root = "";
            if (!isAppSubDirPath) {
                root = super.getRootPath();
            }
            super.fileDir = root + super.fileDir;//+ fileDir;
        } else {
            super.setFileDir(fileDir);
        }
        //System.out.println("               fileDir " + fileDir);
        super.fullFilePath(fileDir, fileName);
    }

    @Override
    public void setFileName(String fileName) {
        //To change body of generated methods, choose Tools | Templates.
        if (super.fileDir.charAt(super.fileDir.length() - 1) != '/') {
            super.fileDir = super.fileDir + "/";
        }
        super.fullFileName = super.fileDir + fileName;
    }

    public String urlRead(String url) throws MalformedURLException, IOException {
        StringBuffer sbf = new StringBuffer();
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sbf.append(inputLine);
        }
        in.close();
        return sbf.toString();
    }

}
