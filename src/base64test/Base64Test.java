/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base64test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author mblanton
 */
public class Base64Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
             if (!Base64.isBase64(args[0])){
                 throw new Exception("Arg 1 is not a Base64 string!");
            }
             else {
                 String decodedBase64String = new String(Base64.decodeBase64(args[0]));
                 File tempFile = File.createTempFile("base64Test", ".tmp");
                 tempFile.deleteOnExit();
                 FileWriter fw = new FileWriter(tempFile, false);
                 PrintWriter pw = new PrintWriter(fw);
                 pw.write(decodedBase64String);
                 pw.close();
                 String fileType = getFileType(tempFile.toPath());
                 System.out.println(fileType);
                 System.in.read();
             }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static String getFileType(Path filePath) {
        String fileType = "Undetermined";
        try {
            fileType = Files.probeContentType(filePath);
        }
        catch(IOException ioE) {
            System.err.println("ERROR: Unable to determine file type for " + filePath.toString()  
              + " due to exception " + ioE);
        }
        
        return fileType;
    }
    
}
