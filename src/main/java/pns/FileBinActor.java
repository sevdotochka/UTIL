package pns;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import pns.fileUtils.FileActor;

/**
 *
 * @author User
 */
public class FileBinActor extends FileActor {

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
}
