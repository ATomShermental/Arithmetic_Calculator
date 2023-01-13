package ZIPImplementation;

import Model.FileType;
import org.junit.jupiter.api.Test;
import zip.ZIP;

import static org.junit.jupiter.api.Assertions.*;

class ZIPTest {
        ZIP zip = new ZIP();

    @Test
    void zipUnzip() {
        byte[] data = "test".getBytes();
        byte[] zipped = zip.zip("test.txt", data);
        FileType fileType = zip.unzip(zipped);
        assertArrayEquals(data, fileType.getData());
        assertEquals("test.txt", fileType.getFilename());
    }

    @Test
    void allUnzip() {
        byte[] data = "test".getBytes();
        byte[] zipped = zip.zip("test.txt", data);
        byte[] doubleZipped = zip.zip("test.zip", zipped);
        FileType fileType = zip.allUnzip(new FileType("test.zip", doubleZipped));
        assertArrayEquals(data, fileType.getData());
        assertEquals("test.txt", fileType.getFilename());
    }
}