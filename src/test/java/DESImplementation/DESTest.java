package DESImplementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DESTest {
    private static final byte[] key = "12345678".getBytes();
    private static final byte[] data = "Hello World".getBytes();

    @Test
    void encrypt() throws Exception {
        byte[] encryptedData = DES.encrypt(data, key);
        assertNotNull(encryptedData);
        assertNotEquals(data, encryptedData);
    }

    @Test
    void decrypt() throws Exception {
        byte[] encryptedData = DES.encrypt(data, key);
        byte[] decryptedData = DES.decrypt(encryptedData, key);
        assertArrayEquals(data, decryptedData);
    }
}