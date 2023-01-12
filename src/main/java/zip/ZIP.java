package zip;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIP {
    public byte[] zip(String filename, byte[] data) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zout = new ZipOutputStream(byteArrayOutputStream);
            ZipEntry zipEntry = new ZipEntry(filename);
            zout.putNextEntry(zipEntry);
            zout.write(data);
            zout.closeEntry();
            zout.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] unzip(byte[] data) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(data));

            while ( zipInputStream.getNextEntry() != null) {
                return zipInputStream.readAllBytes();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new byte[0];
    }
}
