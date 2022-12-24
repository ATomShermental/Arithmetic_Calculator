package zip;

import org.junit.Assert;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ZIPTest {

    ZIP zipUtil = new ZIP();
    @org.junit.jupiter.api.Test
    void zip() throws IOException {
        FileOutputStream fot = new FileOutputStream("Assets/outputZIP.txt");
        zipUtil.zip("Assets/archive.zip","outputZIP.txt","Assets/input.txt");
        fot.write(zipUtil.unzip("Assets/archive.zip","outputZIP.txt"));
        Assert.assertEquals(new FileInputStream("Assets/input.txt").read(),new FileInputStream("Assets/outputZIP.txt").read());
    }
}