package zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZIP {
    public void zip(String archiveName, String filename, byte[] data) {
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archiveName));
            ZipEntry entry = new ZipEntry(filename);
            zout.putNextEntry(entry);
            zout.write(data);
            zout.closeEntry();
            zout.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void zip(String archiveName, String outputFilename, String inputFilename) {
        try {
            FileInputStream fis = new FileInputStream(inputFilename);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            zip(archiveName, outputFilename, buffer);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] unzip(String archiveName, String filename) {
        try {
            ZipFile zipFile = new ZipFile(archiveName);

            var entry = zipFile.getEntry(filename);
            return zipFile.getInputStream(entry).readAllBytes();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
