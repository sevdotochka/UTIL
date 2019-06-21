package pns.fileUtils;

import java.io.*;
import java.util.*;
import pns.utils.strings.RStrings;

/**
 *
 * @author User
 */
public class DirectoryDeepGo {

    protected String rootDir;
    protected String dirToInvestigate = "";
    protected List<File> subDirList = new LinkedList<File>();
    protected List<String> subDirNameList = new LinkedList<>();
    protected List<File> fileList = new ArrayList<File>();
    protected long afterMoment = System.currentTimeMillis() - (1000 * 3600 * 48);

    public DirectoryDeepGo() {
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
        System.out.println("  ~~~~~~~~~~~~~~ public void setRootDir(String rootDir)===>> " + rootDir);
    }

    public String getDirToInvestigate() {
        return dirToInvestigate;
    }

    public void setDirToInvestigate(String dirToInvestigate) {
        this.dirToInvestigate = dirToInvestigate;
    }

    public List<File> getSubDirList() {
        return subDirList;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public List<String> getSubDirNameList() {
        return subDirNameList;
    }

    public void setSubDirNameList(List<String> subDirNameList) {
        this.subDirNameList = subDirNameList;
    }

    public long getAfterMoment() {
        return afterMoment;
    }

    public void setAfterMoment(long afterMoment) {
        this.afterMoment = afterMoment;
    }

    /**
     * Takes up a String data and boolean byWord parameters. Then goes through
     * fileList. <br />
     * If byWords==true, removes from data and every element of fileList all
     * long White spaces and compares the result datas<br />
     * Else simply compares data whith each element of fileList <br />
     * return true? iff there is exists the value of data in one of element of
     * fileList
     *
     * @param data
     * @param byWords
     * @return
     */
    public boolean checkEqualDataInFiles(String data, boolean byWords) {
        if (data == null) {
            return false;
        }
        if (data.length() == 0) {
            return false;
        }
        data = data.trim();

//        System.out.println("  --------->>>> checkEqualDataInFiles    fileList.size() : " + fileList.size());
//        System.out.println("  --------->>>> checkEqualDataInFiles   root : " + rootDir);
//        System.out.println("      data: " + data + "   " + data.length());
        for (int k = 0; k < fileList.size(); k++) {

            FileActor fa = new FileActor(rootDir);
            File f = fileList.get(k);
//            System.out.println("     ");
            boolean bb = fa.fileRead(f.getAbsolutePath());
            String fileData = fa.getFileContent();
            if (byWords) {
//                StringTokenizer dataTokenizer = new StringTokenizer(data);
//                StringTokenizer fileDataTokenizer = new StringTokenizer(fileData);

//                System.out.println("f: " + f.getAbsolutePath());
                String clearData = RStrings.clearLongWhitespaces(data);
//                System.out.println("clearData: " + clearData + "  isNULL  " + (clearData == null));
                String clearFileData = RStrings.clearLongWhitespaces(fileData);
//                System.out.println("fileData: " + fileData + "  isNULL  " + (fileData == null));
//                System.out.println("clearFileData: " + clearFileData + "  isNULL  " + (clearFileData == null));

                if (clearData.equals(clearFileData)) {
//                    System.out.println(" f: " + f.getAbsolutePath());
                    return true;
                }
            } else {
//                System.out.println(" f: " + f.getAbsolutePath());
//                System.out.println("data.length(): " + data.trim().length() + "  fileData.length(): " + fileData.trim().length());
//                System.out.println("         data  " + data);
//                System.out.println("         fileData  " + fileData);
                if (data.trim().equals(fileData.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void prevFileList() {

    }

    public void goDeep(String investigateDir, boolean removeEmptyDirectory) {
        fileList.clear();
        if (investigateDir == null) {
            return;
        }
        if (investigateDir.length() == 0) {
            return;
        }
        String startDir = rootDir;
        if (dirToInvestigate.length() > 0) {
            startDir = rootDir + "/" + dirToInvestigate;
        }
        startDir = FileActor.getAbsoluteFileName(investigateDir);
//        System.out.println("    START DIR::::   " + startDir + "    ROOOT DIR " + rootDir);

        File startFile = new File(startDir);
//        System.out.println("  --------------->>>> >>>> startDir " + startDir);

        //List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();
        fileTree.add(startFile);

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            long currFileCreate = currentFile.lastModified();

//            System.out.println("   FilePath: " + currentFile.getAbsolutePath() + "; " + delta);
            if (currentFile.isDirectory()) {
                if (removeEmptyDirectory) {
                    removeEmptyDir(currentFile.getAbsolutePath());
                }
                if (currentFile.exists()) {
                    subDirList.add(currentFile);
                    Collections.addAll(fileTree, currentFile.listFiles());
                }

            } else {
                fileList.add(currentFile);

            }
        }
        // System.out.println("     --------    Collect     " + fileList.size() + " files ");
    }

    /**
     * Tests, is a String parameter points to a DIRECTORY, if it is true, tests
     * is it empty and remove empty DIR
     *
     * @param dirName
     */
    private void removeEmptyDir(String dirName) {
        File fd = new File(dirName);
        if (fd.exists()) {
            if (fd.isDirectory()) {
                File[] filesList = fd.listFiles();
                String dirContent = "";
                if (filesList.length == 0) {
                    fd.delete();
                }
            }
        }
    }

    /**
     * wolking throu the directoryName folder to collect a list of files and
     * directories in it
     */
    public void goDeep(File dirFile) {

        if (dirFile == null) {
            return;
        }

//        System.out.println("               (dirFile==null)  " + (dirFile == null) + ""
//                + "    dirFile.isFile() " + dirFile.isFile()
//                + "  dirFile.isDirectory() " + dirFile.isDirectory()
//                + "    (dirFile.exists() " + (dirFile.exists()));
        if (!dirFile.isDirectory()) {
            System.out.println("   Given path  " + dirFile.getAbsolutePath() + "  is not a Directory  ");
            return;
        }
        Queue<File> fileTree = new PriorityQueue<>();
        fileTree.add(dirFile);
        Date d = new Date();

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
//            System.out.println("   FilePath: " + currentFile.getAbsolutePath());

            long delta = Math.abs(System.currentTimeMillis() / 1000 - currentFile.lastModified() / 1000);
            Date fDate = new Date(currentFile.lastModified());
            // System.out.println(d + "   " + fDate + ";   " + (d.getTime() - fDate.getTime()) + " ; " + delta);
            if (currentFile.isDirectory()) {
                subDirList.add(currentFile);
                Collections.addAll(fileTree, currentFile.listFiles());

            } else {
                fileList.add(currentFile);

            }

        }
    }

    /**
     * wolking throu the rootDir/dirToInvestigate folder to collect a list of
     * files and directories in it
     */
    public void goDeep() {

        if (rootDir == null) {
            return;
        }
        if (rootDir.length() == 0) {
            return;
        }

        String startDir = rootDir;
        if (dirToInvestigate.length() > 0) {
            startDir = rootDir + "/" + dirToInvestigate;
        }
        startDir = FileActor.getAbsoluteFileName(startDir);
        System.out.println("  Scanning Dir   -- " + rootDir);

//int k = 0;
        File startFile = new File(startDir);
//        fileList.clear();
//        subDirList.clear();
        File rootDir = new File(this.rootDir);
        //List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();
        System.out.println("   ---------- startDir:   " + startDir);
        fileTree.add(startFile);

        Date d = new Date();
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
//            System.out.println("   FilePath: " + currentFile.getAbsolutePath());

            long delta = Math.abs(System.currentTimeMillis() / 1000 - currentFile.lastModified() / 1000);
            Date fDate = new Date(currentFile.lastModified());
            // System.out.println(d + "   " + fDate + ";   " + (d.getTime() - fDate.getTime()) + " ; " + delta);
            if (currentFile.isDirectory()) {
                subDirList.add(currentFile);
                Collections.addAll(fileTree, currentFile.listFiles());

            } else {
                fileList.add(currentFile);

            }

        }
        //System.out.println("  fileList.size() " + fileList.size());
    }

    /**
     * wolking throw the 'dirToInvestigate' folder to collect a list of files
     * and directories in it
     */
    public void goDeepDirectly() {
        System.out.println(" ---  Investigating the directory: " + dirToInvestigate);

        String startDir = "";
        if (dirToInvestigate.length() > 0) {
            startDir = dirToInvestigate;

        } else {
            System.out.println("  Error in start directory name");
            return;
        }
        startDir = FileActor.getAbsoluteFileName(startDir);
        File startFile = new File(startDir);
        if (!startFile.isDirectory()) {
            System.out.println(" No directory name given");
            return;
        }
        System.out.println(" ---  Investigating the directory: " + startFile.getAbsolutePath());
        Queue<File> fileTree = new PriorityQueue<>();
        fileTree.add(startFile);

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();

            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                fileList.add(currentFile);//getAbsolutePath()
            }
        }
    }

    public void outputFileList() {
        System.out.println(" File in  " + rootDir + ":");
        System.out.println("");
        for (int k = 0; k < fileList.size(); k++) {
            System.out.println("          File:  " + fileList.get(k).getAbsolutePath());
        }
    }

    public boolean testEquals(DirectoryDeepGo ddg, String testFileName) {
        boolean result = false;

        File testFile = new File(testFileName);
        if (!testFile.exists()) {
            System.out.println("The file does not exists ...");
            return true;
        }
        if (testFile.isDirectory()) {
            return false;
        }
        FileActor testFA = new FileActor(testFileName);
        testFA.fileRead();
        String testContent = testFA.getStringBuffer().toString();
        testContent = pns.utils.strings.RStrings.clearLongWhitespaces(testContent);
//        System.out.println(testContent);
        List<File> fl = ddg.getFileList();
        double totalFSZ = 0;
        for (int p = 0; p < fl.size(); p++) {
            File tmp = fl.get(p);
            FileActor fa = new FileActor(tmp.getAbsolutePath());
            double fsz = tmp.length() / 1024d;
            totalFSZ += fsz;
//            if (p % 10 == 0) {
//                System.out.println("Testing " + p + "-th file " + tmp.getAbsolutePath() + "  of " + fl.size() + "... Its size is " + fsz + "kb;  "
//                        + "Total files size is " + totalFSZ + "kb; "
//                        + " ....... Wait");
//            }

            fa.fileRead();;
            String tmpContent = fa.getStringBuffer().toString();
            tmpContent = pns.utils.strings.RStrings.clearLongWhitespaces(tmpContent);
            if (testContent.equals(tmpContent)) {
                System.out.println(" content exists at:  " + tmp.getAbsolutePath());
                result = true;

                //fa.renameFileTo();
                System.out.println("  ~~~~~~~~~~~~~~~~>>>>>>>>>> result " + result);
                return true;
            }
            // System.out.println(p + ":" + tmp.getAbsolutePath());
        }

        System.out.println("  ~~~~~~~~~~~~~~~~>>>>>>>>>> result " + result);
        return false;
    }
}
