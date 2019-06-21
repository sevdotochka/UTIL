/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.fileUtils;

import java.io.File;

/**
 *
 * @author uranium
 */
public class FileMoverCopier {

    public static void moveFile(File fileSorce, String targetFile) {
        System.out.println(" $$==     ");
        System.out.println(" $$== Moving File    ..... .... .. . ... ..  ");

        File nf = new File(targetFile);
        File ff = new File(targetFile).getParentFile();
        //   File pnf = new File(nf.getAbsolutePath());
        if (!ff.exists()) {
            ff.mkdirs();
        }
        fileSorce.renameTo(nf);

        System.out.println("File " + fileSorce.getAbsolutePath() + "  moved to " + nf.getAbsolutePath());
        System.out.println(" $$==     ");

    }
}
