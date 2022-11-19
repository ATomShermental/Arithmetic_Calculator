import des.DES;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DESTests {
    @Test
    public void testEncrypt() throws IOException {
        File input = new File("Assets/input.txt");
        DES.encrypt(input);
        DES.decrypt(new File("Assets/encrypted.txt"));
        Assert.assertEquals(new FileInputStream("Assets/input.txt").read(),new FileInputStream("Assets/decrypted.txt").read());
    }
}
