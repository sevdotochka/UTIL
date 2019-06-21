package pns.fileUtils;

//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class DirGoing {

    protected String directoryName;
    protected String parentDirectoryName;
    protected List<File> subDirList = new ArrayList<>();
    protected List<File> fileList = new ArrayList<>();

    public DirGoing() {
    }

    public DirGoing(String directoryName) {
        this.directoryName = directoryName;
        createParentDir();
    }

    /**
     * Get the value of DirectoryName
     *
     * @return the value of DirectoryName
     */
    public String getDirectoryName() {
        return directoryName;
    }

    public String getParentDirectoryName() {
        return parentDirectoryName;
    }

    /**
     * Set the value of DirectoryName
     *
     * @param DirectoryName new value of DirectoryName
     */
    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        createParentDir();
    }

    public List<File> getSubDirList() {
        return subDirList;
    }

    public List<File> getFileList() {
        return fileList;
    }

    /**
     * Tests, is the directoryName valid
     *
     * @return
     */
    public boolean testValidDirName() {
        File f = new File(directoryName);
        if (directoryName == null) {
            System.out.println("Null Name input");
            return false;
        }
        if (directoryName.length() == 0) {
            System.out.println("Input Name has no characters");
            return false;
        }
        if (!f.exists()) {
            System.out.println("Input Name is incorrect");
            return false;
        }
        if (!f.isDirectory()) {
            System.out.println("Input Name is not a directoryName");
            return false;
        }
        return f.isDirectory();
    }

    public void createSubDirList() {
        if (testValidDirName()) {
            subDirList.clear();;
            fileList.clear();
            File f = new File(directoryName);
            File[] files = f.listFiles();
            for (int k = 0; k < files.length; k++) {
                String currFN = files[k].toString();

                if (files[k].isDirectory()) {
                    subDirList.add(files[k]);
                } else if (files[k].isFile()) {
                    fileList.add(files[k]);
                }
            }
        }
    }

    public void createParentDir() {
        if (testValidDirName()) {
            File f = new File(directoryName);
            parentDirectoryName = f.getParent();
        }
    }

}
