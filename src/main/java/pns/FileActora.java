package pns;

import java.io.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

/**
 *
 * @author PSEVDO
 */
public class FileActora {

    /**
     * Full Filename in OS
     */
    protected String fullFileName = "";
    /**
     * Data directory where file set deploing
     */
    private String fileDir = "data/";
    /**
     * Full name of Data directory where file set deploing
     */
    private String fullFileDir = "data/";
    /**
     * The proper fileName
     */
    private String fileName = "";

    /**
     * TRUE, iff it's nessesary to use the subDirectory of the running project
     */
    private boolean isAppSubDirPath = false;

    private StringBuffer stringBuffer = new StringBuffer();

    public FileActora(String fileDir, String fileName) {
	fullFilePath(fileDir, fileName);
    }

    public FileActora() {
    }

    /**
     * Directly generates the FullFileName, while creating the FileActor
     *
     * @param fullFileName
     */
    public FileActora(String fullFileName) {
	this.fullFileName = fullFileName;
    }

    /**
     * if isAppSubDirPath==true, the object works with the directory subDir of
     * currentDir, else it works in the tunned directory
     * <br />
     * default isAppSubDirPath value is false
     *
     * @param isAppSubDirPath
     */
    public FileActora(boolean isAppSubDirPath) {
	this.isAppSubDirPath = isAppSubDirPath;
    }

    /**
     * Generates the FullFilePath
     *
     * @param fileDir
     * @param fileName
     */
    public void fullFilePath(String fileDir, String fileName) {
	fileDir = fileDir.trim();
	fileName = fileName.trim();
	String volRoot = getRootPath();
//        System.out.println(" fileDir:  " + fileDir + " fileName: " + fileName + " isAppSubDirPath " + isAppSubDirPath);

	if (!isAppSubDirPath) {
	    fullFileName = volRoot + fileDir + "/" + fileName;
	    fullFileDir = volRoot + fileDir;
	} else {
	    fullFileName = fileDir + "/" + fileName;
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
     * Generates full file name in standard format : DIRNAME/min-sec-milisec
     * Firstly it's need to create the DIRNAME
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

    public String getFullFileName() {
	return fullFileName;
    }

    public String getFileDir() {
	return fileDir;
    }

    public void setFileDir(String fileDir) {
	this.fileDir = fileDir;
	fullFilePath(fileName, fileDir);
    }

    public String getFileName() {
	return fileName;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
	this.fileName = fileName;
	fullFilePath(fileName, fileDir);
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

	return rootVolume;
    }

    public StringBuffer getStringBuffer() {
	return stringBuffer;
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
	final FileActora other = (FileActora) obj;
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
     * Writes given data string to fullFileDir
     *
     * @param data
     */
    public void fileWrite(String data) {
	//System.out.println("            " + fullFileDir);
	File f = new File(fullFileDir);
	if (!f.exists()) {
	    f.mkdirs();
	}
	f = new File(fullFileName);
	try (BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(f))) {
	    byte[] b = data.getBytes();
	    buffOut.write(b);
	} catch (IOException ex) {
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

    /**
     * Read the file from fullFileName to the (StringBuffer)stringBuffer field
     */
    public void fileRead() {
	String line;
	File f = new File(fullFileName);

	try (BufferedReader bir
		= new BufferedReader(
			new InputStreamReader(
				new FileInputStream(f))
		)) {

	    line = bir.readLine();
	    stringBuffer.append(line);
	    stringBuffer.append(System.lineSeparator());

	    //System.out.println("line : " + line);
	    while ((line = bir.readLine()) != null) {
		stringBuffer.append(line);
		stringBuffer.append(System.lineSeparator());

		//System.out.println("line : " + line);
	    }
	} catch (IOException ex) {
	    System.out.println(" ex : " + ex.getMessage());
	}
    }

    /**
     * Read the file from fullFileName and puts it content to the
     * (StringBuffer)stringBuffer field
     */
    public Boolean fileRead(String fname) {
	String line;
	File f = new File(fname);

	try (BufferedReader bir
		= new BufferedReader(
			new InputStreamReader(
				new FileInputStream(f))
		)) {

	    line = bir.readLine();
	    stringBuffer.append(line);
	    stringBuffer.append(System.lineSeparator());

	    //System.out.println("line : " + line);
	    while ((line = bir.readLine()) != null) {
		stringBuffer.append(line);
		stringBuffer.append(System.lineSeparator());

		//System.out.println("line : " + line);
	    }
	} catch (IOException ex) {
	    System.out.println(" ex : " + ex.getMessage());
	}
	return f.exists();
    }

    /**
     * Generate Standard Directory Name of format :
     * DATADIR/YYYY/MM/DD/RandomString
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
     * Generate Standard Directory Name of format :
     * DATADIR/YYYY/MM/DD/RandomString
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
}
