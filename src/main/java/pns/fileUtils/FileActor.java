package pns.fileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpdw
 */
public class FileActor {

    /**
     * Full Filename in OS
     */
    protected String fullFileName = "";
    /**
     * Data directory where file set deploing
     */
    protected String fileDir = "data/";
    /**
     * Full name of Data directory where file set deploing
     */
    protected String fullFileDir = "data/";
    /**
     * The proper fileName
     */
    protected String fileName = "file";

    /**
     * Application Dir name
     */
    private String appDir;

    /**
     * Get the value of appDir
     *
     * @return the value of appDir
     */
    public String getAppDir() {
        return appDir;
    }

    /**
     * Set the value of appDir
     *
     * @param appDir new value of appDir
     */
    public void setAppDir(String appDir) {
        this.appDir = appDir;
    }

    /**
     * TRUE, iff it's nessesary to use the subDirectory of the running project
     */
    protected boolean isAppSubDirPath = false;

    /**
     * TRUE, iff it's nessesary to format the fileDirPath in date/time style
     */
    protected boolean isFormatedPath = false;
    /**
     * TRUE, iff it's nessesary to format the fileName in date/time style
     */
    protected boolean isFormatedFileName = false;

    protected String scriptName = "/home/abc/dist/n.sh ";
    protected String fileContent = "";

    private StringBuffer stringBuffer = new StringBuffer();

    public FileActor(String fileDir, String fileName) {
        fullFilePath(fileName, fileDir);
    }

    public FileActor() {
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * Directly generates the FullFileName, while creating the FileActor
     *
     * @param fullFileName
     */
    public FileActor(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    /**
     * if isAppSubDirPath==true, the object works with the directory subDir of currentDir, else it works in the tunned directory
     * <br />
     * default isAppSubDirPath value is false
     *
     * @param isAppSubDirPath
     */
    public FileActor(boolean isAppSubDirPath) {
        this.isAppSubDirPath = isAppSubDirPath;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    /**
     * Generates the FullFilePath
     *
     * @param fileDir
     * @param fileName
     */
    public void fullFilePath(String fileDir, String fileName) {
//        System.out.println("    String fileDir: " + fileDir);
//        System.out.println("    String fileName: " + fileName);
        fileDir = fileDir.trim();
        fileName = fileName.trim();
        String volRoot = getRootPath();
//        System.out.println("  volRoot: " + volRoot + " fileDir:  " + fileDir + " fileName: " + fileName + " isAppSubDirPath " + isAppSubDirPath);

        if (fileDir.charAt(fileDir.length() - 1) != '/') {
            fileDir += "/";

        }
        if (!isAppSubDirPath) {
            fullFileName = volRoot + fileDir + fileName;
            fullFileDir = volRoot + fileDir;
        } else {
            fullFileName = fileDir + fileName;
            fullFileDir = fileDir;
        }
    }

    public void fullFilePath(String fileName) {
        fileName = fileName.trim();
        String volRoot = getRootPath();
        String fileDir = getFileDir();
        Calendar cldr = Calendar.getInstance();
        String dName = dirNameCreator();
        fullFileName = volRoot + fileDir + dName + "/" + fileName;
        fullFileDir = volRoot + fileDir + dName;
    }

    /**
     * Generates full file name in standard format : DIRNAME/min-sec-milisec Firstly it's need to create the DIRNAME
     */
    public void fullFilePath() {
        String volRoot = getRootPath();
        String fileDir = getFileDir();
        Calendar cldr = Calendar.getInstance();
        int minM = cldr.get(Calendar.MINUTE);
        int secM = cldr.get(Calendar.SECOND);
        int miliM = cldr.get(Calendar.MILLISECOND);

        String fileName = pns.utils.strings.RStrings.zeroFirst(minM) + "-"
                + pns.utils.strings.RStrings.zeroFirst(secM) + "-"
                + pns.utils.strings.RStrings.zeroFirst(miliM);

        String dName = dirNameCreator("");

        fullFileName = volRoot + fileDir + dName + "/" + fileName;
        fullFileDir = volRoot + fileDir + dName;
    }

    public boolean isIsFormatedPath() {
        return isFormatedPath;
    }

    public void setIsFormatedPath(boolean isFormatedPath) {
        this.isFormatedPath = isFormatedPath;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public boolean isIsFormatedFileName() {
        return isFormatedFileName;
    }

    public void setIsFormatedFileName(boolean isFormatedFileName) {
        this.isFormatedFileName = isFormatedFileName;
    }

    public boolean isIsAppSubDirPath() {
        return isAppSubDirPath;
    }

    public void setIsAppSubDirPath(boolean isAppSubDirPath) {
        this.isAppSubDirPath = isAppSubDirPath;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
        fullFilePath(fileDir, fileName);
    }

    public void setFileDir() {
        this.fileDir = fileDir;
        fullFilePath(fileDir, fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        fullFilePath(fileDir, fileName);
    }

    public void setFileName() {
        this.fileName = fileName;
        fullFilePath(fileDir, fileName);
    }

    /**
     * Generate the root's Name of the current drive
     *
     * @return
     */
    public String getRootPath() {
        String rootVolume = "/";
        String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String[] absParts1 = absolutePath.split(":/");
        String[] absParts = absolutePath.split("/");
        if (absParts1.length == 2) {
            rootVolume = absParts[1] + "/";
        }
        if (isAppSubDirPath) {
            rootVolume = System.getProperty("user.dir") + "/";
        }
        String path = new File(".").getAbsolutePath();
        //System.out.println(" ROOT VOLUME " + rootVolume + "   path " + path);
        File f = new File(rootVolume);
        rootVolume = f.getParent();
        return rootVolume;
    }

    /**
     * Return current application root
     *
     * @param upLevel ---- if it`s need an UP-level rootpath
     * @return
     */
    public String getAppRootPath(boolean upLevel) {
        String uri = new File(".").getAbsolutePath();
        Path path = Paths.get(uri);
        File f = path.getParent().toFile();
//        String path = new File(".").getAbsolutePath();
//        File f = new File(path);
        if (upLevel) {
            return f.getParentFile().getAbsolutePath();
        } else {
            return f.getAbsolutePath();
        }
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public boolean createDir(String name) {
        File f = new File(name);
        if (!f.exists()) {
            f.mkdirs();
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.fullFileName);
        hash = 89 * hash + Objects.hashCode(this.fileDir);
        hash = 89 * hash + Objects.hashCode(this.fileName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileActor other = (FileActor) obj;
        if (!Objects.equals(this.fullFileName, other.fullFileName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FileActor{" + "fullFileName=" + fullFileName + ", fileDir=" + fileDir + ", fileName=" + fileName + '}';
    }

    /**
     * Writes given data string to fullFileDir and the fullFileName
     * <div>
     * These parameters has a default values? iff not be set
     * </div>
     *
     * @param data
     */
    public boolean fileWrite(String data) {
//        System.out.println("       ----->>>>>>public boolean fileWrite(String data)  fullFileName: " + fullFileName);
        File f = new File(fullFileName).getParentFile();
        System.out.println("       ----->>>>>>public boolean fileWrite(String data)          fullFileDir: " + f.getAbsolutePath());
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(fullFileName);
        System.out.println("       ----->>>>>>public boolean fileWrite(String data)          fullFileDir: " + fullFileName);
        try (BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(f))) {
            byte[] b = data.getBytes();
            System.out.println("******Run Write Process:  Size: " + b.length);
            buffOut.write(b);
            return true;
        } catch (IOException ex) {
            System.out.println(" ex: " + ex.getMessage());
            System.out.println("  c data  " + data);
            return false;
        }
    }

    /**
     * Writes given data string to fullFileDir and the fullFileName
     * <div>
     * These parameters has a default values? iff not be set
     * </div>
     * Then it set up the rights for the file
     *
     * @param data
     * @param isReadable
     * @param isWriteble
     * @param issetExecutable
     */
    public boolean fileWrite(String data, boolean issetExecutable, boolean isReadable, boolean isWriteble) {
//        System.out.println("       ----->>>>>>public boolean fileWrite(String data)  fullFileName: " + fullFileName);
//        System.out.println("       ----->>>>>>public boolean fileWrite(String data)          fullFileDir: " + fullFileDir);
        File f = new File(fullFileDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(fullFileName);
//        System.out.println("         @@@@@   fullFileName: " + fullFileName);
        try (BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(f))) {
            byte[] b = data.getBytes();
            buffOut.write(b);
        } catch (IOException ex) {
            return false;
        }
        f.setExecutable(issetExecutable);
        f.setReadable(isReadable, false);
        f.setWritable(isWriteble, false);
        return true;
    }

    /**
     * Writes given data string to fullFileDir and the fullFileName
     * <div>
     * These parameters has a default values? iff not be set
     * </div>
     * Then it set up the rights for the file
     *
     * @param data
     * @param chmod
     * @param outFileName =
     */
    public boolean fileWrite(String data, String chmod, String outFileName) {
//        System.out.println("       ----->>>>>>public boolean fileWrite(String data)  fullFileName: " + fullFileName);
//        System.out.println("       ----->>>>>>public boolean fileWrite(String data)          fullFileDir: " + fullFileDir);
        File f = new File(fullFileDir);
        if (!f.exists()) {
            f.mkdirs();
        }

        f = new File(fullFileName);
        byte[] b;
        String ssDATA = "";
        System.out.println("         fileWrite   fullFileName: " + fullFileName);
        System.out.println("         fileWrite   scriptNamee: " + scriptName);
        try (BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(f))) {
            b = data.getBytes();
            buffOut.write(b);
        } catch (IOException ex) {
            return false;
        }
        try {
            ssDATA = new String(b, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileActor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //scriptName = appDir + "/scripts/sendmailtokibs.sh";
        File cf = new File(scriptName);
//        System.out.println("   -------------------->>>        LevelUP " + cf.getAbsolutePath());
//        System.out.println("   cf exists : " + (cf.exists()));

        String absPath = f.getAbsolutePath();
        if (cf.exists()) {
            String[] args = new String[10];

            args[0] = "chmod    " + chmod + "    " + f.getAbsolutePath();
            System.out.println(" chmod: args[0] " + args[0]);
            exeSHELL(args, null);

            String body = " Data_space_in_space_space_space_the_space_attachment... ";

            String subj = parseFileName(f.getName());
            String mail = "meas@keldysh.ru"; // "solovjov_pn@mail.ru";
            //args[0] = scriptName.trim() + " -t solovjov_pn@mail.ru -s 'Message From ASPOS '    -a \'" + f.getAbsolutePath() + "\' -b 'Data in the attachment...' ";
            args[0] = scriptName.trim() + "  " + mail + "   " + f.getAbsolutePath() + " " + subj;
//            String tms = "*******************" + System.lineSeparator();
//            tms += "*******************" + System.lineSeparator();
//            tms += "*******************" + System.lineSeparator();
//            tms += "*******************" + System.lineSeparator();
//            tms += "+++" + System.lineSeparator();
//            tms += "+++" + System.lineSeparator();
//            tms += "+++" + System.lineSeparator();
//
//            tms += "    script: " + scriptName + System.lineSeparator();
//
//            tms += "+++" + System.lineSeparator();
//
//            tms += "+++  args[0]  " + args[0];
//
//            tms += "+++  fullFileName " + fullFileName;
//            tms += "+++" + System.lineSeparator();
//            tms += "+++" + System.lineSeparator();
//            tms += "+++" + System.lineSeparator();
//
//            tms += "*******************" + System.lineSeparator();
//            tms += "*******************" + System.lineSeparator();
//            tms += "*******************" + System.lineSeparator();
//            System.out.println(tms);
//
//            System.out.println(" *****>>>  RUN: ********* script: args[0] " + args[0] + System.lineSeparator() + System.lineSeparator() + "  outFileName " + outFileName);
            exeSHELL(args, outFileName);
        }
        return true;
    }

    private String parseFileName(String ss) {
        String res = ss.substring(4);
//        System.out.println(" ******************************************=========>>>>>> " + res);
        return res;
    }

    private void collectOUTPUT(Process proc, String outFileName) throws IOException {
        String s = "", out = "";
        boolean upWrite = true;
        fileContent = "";
        File f = new File(outFileName);
        if (f.exists()) {
            fileRead(f.getAbsolutePath());
            if (fileContent.length() > 3000) {
                upWrite = false;
            }
        }

        if (proc != null) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            int k = 0;
            System.out.println("Script outputs:");
            while ((s = reader.readLine()) != null) {
                if (k != 2 && upWrite) {
                    out += "        Message  " + ":  " + s + System.lineSeparator();
                }
                System.out.println("            INFO #: " + k + ":  " + s);

                k++;
            }
            System.out.println(out);

            Date d = new Date();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:SSS");

            String ds = df.format(d);
            out = "<< " + ds + " >>" + out;
            out = fileContent + System.lineSeparator() + System.lineSeparator() + out;
            String[] fileContentParts = fileContent.split("endlog");
            String folog = fileContentParts[fileContentParts.length - 1] + "endlog";
            outToFile(f, out + folog);
        }

    }

    private static void outToFile(File f, String out) throws IOException {

        BufferedWriter outWrite = new BufferedWriter(new FileWriter(f));
        outWrite.write(out);  //Replace with the string
        //you are trying to write
        outWrite.close();

        Runtime rt = Runtime.getRuntime();
        rt.exec("chmod 660 " + f.getAbsolutePath());

    }

    private void exeSHELL(String[] args, String outFileName) {
        System.out.println("  logUpload: outFileName " + outFileName);
        Runtime rt = Runtime.getRuntime();
        try {
            Process proc = rt.exec(args[0]);
            collectOUTPUT(proc, outFileName);
        } catch (IOException ex) {
            Logger.getLogger(FileActor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("   NPE  " + e + "  " + "  args ==null  " + (args == null));
            System.out.println(" NRE  details ------------->>  0: " + args[0] + "  1: " + args[1]);
            System.out.println(" NRE  rt==null: " + (rt == null));

        }
    }

    /**
     * Writes given binary data content to fullFileDir
     *
     * @param content
     */
    public void fileWrite(byte[] content) {
        //System.out.println("            " + fullFileDir);
        File f = new File(fullFileDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(fullFileName);
        try (BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(f))) {
            buffOut.write(content);

        } catch (IOException ex) {
        }

    }

    /**
     * Export given serializable data Object to fullFileName
     *
     * @param data
     */
    public void objectExport(Object data) {

        File f = new File(fullFileDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(fullFileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(f)))) {
            oos.writeObject(data);
        } catch (IOException ex) {
        }

    }

    /**
     * Import serializable data Object from fullFileName
     *
     * @param data
     */
    public Object objectImport(String fname) {
        File f = new File(fname);

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(f))) {
            return ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return null;
    }

    public void fileRead(URL url) {
        StringBuffer sbf = new StringBuffer();
        fileContent = "";
        try {

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                sbf.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        fileContent = sbf.toString();
    }

    /**
     * Read the file from fullFileName to the (StringBuffer)stringBuffer field
     */
    public void fileRead() {
        String line;
        File f = new File(fullFileName);
        try (BufferedReader bir
                = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(f), "cp1251")
                )) {

            line = bir.readLine();

//            line = new String(line.getBytes(), Charset.forName("cp1251"));
            stringBuffer.append(line);
            stringBuffer.append(System.lineSeparator());
            int k = 0;
//            System.out.println("line : " + line);
            while ((line = bir.readLine()) != null) {

                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());

                //System.out.println("line : " + line);
            }
            fileContent = stringBuffer.toString();
        } catch (IOException ex) {
            System.out.println(" ex : " + ex.getMessage());
        }

    }

    /**
     * Read the file from fname and puts it content to the (StringBuffer)stringBuffer field
     */
    public Boolean fileRead(String fname) {
        String line = "";
        fileContent = "";
        File f = new File(fname);

        try (BufferedReader bir
                = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(f))
                )) {
            line = bir.readLine();
//          line = bir.readLine();
//            System.out.println(" line == null " + (line == null));
            if (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());
            }
            //System.out.println("line : " + line);
            while ((line = bir.readLine()) != null) {

                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());

                //System.out.println("line : " + line);
            }
        } catch (IOException ex) {
            System.out.println("Read Exception: " + ex.getMessage());
            return false;
        }
        fileContent = stringBuffer.toString();

        if (stringBuffer == null) {
            fileContent = "";
        }
        stringBuffer.delete(0, stringBuffer.length());
        return f.exists();
    }

    /**
     * Read the file from fname and puts it content to the (StringBuffer)stringBuffer field
     */
    public Boolean fileReadUTF(String fname) {
        String line = "";
        fileContent = "";
        File f = new File(fname);

        try (BufferedReader bir
                = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(f), "utf-8")
                )) {
            line = bir.readLine();
//          line = bir.readLine();
//            System.out.println(" line == null " + (line == null));
            if (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());
            }
            //System.out.println("line : " + line);
            while ((line = bir.readLine()) != null) {

                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());

                //System.out.println("line : " + line);
            }
        } catch (IOException ex) {
            System.out.println("Read Exception: " + ex.getMessage());
            return false;
        }
        fileContent = stringBuffer.toString();

        if (stringBuffer == null) {
            fileContent = "";
        }
        stringBuffer.delete(0, stringBuffer.length());
        return f.exists();
    }

    /**
     * Read the file from fname and puts it content to the (StringBuffer)stringBuffer field
     */
    public Boolean fileRead(String fname, String encoding) {
        String line = "";
        fileContent = "";
        File f = new File(fname);

        try (BufferedReader bir
                = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(f), encoding)
                )) {
            line = bir.readLine();

            if (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());
            }
            //System.out.println("line : " + line);
            while ((line = bir.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());

                //System.out.println("line : " + line);
            }
        } catch (IOException ex) {
            System.out.println("Read Exception: " + ex.getMessage());
            return false;
        }
        fileContent = stringBuffer.toString();

        if (stringBuffer == null) {
            fileContent = "";
        }
        stringBuffer.delete(0, stringBuffer.length());

        return true;
    }

    /**
     * Generate Standard Directory Name of format : DATADIR/YYYY/MM/DD/RandomString
     *
     * @return
     */
    public String dirNameCreator(String directoryName, boolean isUTC) {
        directoryName = directoryName.trim();
        Calendar cldr = Calendar.getInstance();
        if (isUTC) {
            cldr = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        }

        int mY = cldr.get(Calendar.YEAR);
        int mM = cldr.get(Calendar.MONTH);
        int mD = cldr.get(Calendar.DATE);
        int mH = cldr.get(Calendar.HOUR_OF_DAY);

        if (directoryName.length() == 0) {
            directoryName = pns.utils.strings.RStrings.rndString(pns.utils.numbers.RInts.rndInt(4, 7), pns.utils.strings.RStrings.LATIN_SMALL);
        }

        String result = pns.utils.strings.RStrings.zeroFirst(mY) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mM + 1) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mD) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mH) + "/"
                + directoryName;

        return result;
    }

    /**
     * Generate Standard Directory Name of format : DATADIR/YYYY/MM/DD/RandomString
     *
     * @return
     */
    public String dirNameCreator(String directoryName) {

        directoryName = directoryName.trim();
        Calendar cldr = Calendar.getInstance();

        int mY = cldr.get(Calendar.YEAR);
        int mM = cldr.get(Calendar.MONTH);
        int mD = cldr.get(Calendar.DATE);
        int mH = cldr.get(Calendar.HOUR_OF_DAY);

        if (directoryName.length() == 0) {
            directoryName = pns.utils.strings.RStrings.rndString(pns.utils.numbers.RInts.rndInt(4, 7), pns.utils.strings.RStrings.LATIN_SMALL);
        }
        String result = pns.utils.strings.RStrings.zeroFirst(mY) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mM + 1) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mD) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mH) + "/"
                + directoryName;

        return result;
    }

    /**
     * Generate Standard Directory Name of format : DATADIR/YYYY/MM/DD
     *
     * @return
     */
    private String dirNameCreator() {
        Calendar cldr = Calendar.getInstance();
        int mY = cldr.get(Calendar.YEAR);
        int mM = cldr.get(Calendar.MONTH);
        int mD = cldr.get(Calendar.DATE);
        int mH = cldr.get(Calendar.HOUR_OF_DAY);

        String result = pns.utils.strings.RStrings.zeroFirst(mY) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mM + 1) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mD) + "/"
                + pns.utils.strings.RStrings.zeroFirst(mH);

        return result;
    }

    public static String getAbsoluteFileName(String path) {

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f.getAbsolutePath();
    }

    public void renameFileTo() {
//        System.out.println("    --------->>  " + this.fullFileName);
        int rnd = (int) (10000 * Math.random());
        long nano = System.nanoTime();
        String postfix = "_notFormat!_" + nano + rnd;
//        System.out.println("  ----->>> nano " + nano);
//        System.out.println("  ----->>> post " + postfix);
        File from = new File(fullFileName);
        File to = new File(fullFileName + postfix);
        try {
            boolean renamed = from.renameTo(to);
            if (renamed) {
                System.out.println("");
                System.out.println(" The error founded at " + from.getAbsolutePath());
                System.out.println(" and it has been renamed to " + to.getAbsolutePath());
                System.out.println("");
            }

//            System.out.println("    ~~~>>>renamed " + renamed);
        } catch (Exception e) {
        }
    }

}
