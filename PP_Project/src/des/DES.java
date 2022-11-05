package des;

import java.security.*;
import java.io.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES {

    public static void encryptDecrypt(String key, int cipherMode, File input, File output) throws  InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IOException, InvalidAlgorithmParameterException
    {
        FileInputStream fis = new FileInputStream(input);
        FileOutputStream fos = new FileOutputStream(output);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());

        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = skf.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        byte[] ivBytes = new byte[8];
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        if(cipherMode ==  Cipher.ENCRYPT_MODE)
        {
            cipher.init(Cipher.ENCRYPT_MODE,secretKey, iv, SecureRandom.getInstance("SHA1PRNG"));
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            write(cis,fos);
        }
        else if(cipherMode == Cipher.DECRYPT_MODE)
        {
            cipher.init(Cipher.DECRYPT_MODE,secretKey, iv, SecureRandom.getInstance("SHA1PRNG"));
            CipherOutputStream cos = new CipherOutputStream(fos,cipher);
            write(fis,cos);
        }
    }

    private static  void write(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[64];
        int numOfBytesRead;
        while((numOfBytesRead = input.read(buffer)) != -1)
        {
            output.write(buffer,0,numOfBytesRead);
        }
        output.close();
        input.close();
    }

    public static void encrypt(File file) {

        File encrypted = new File("encrypted.txt");

        try {
            encryptDecrypt("12345678", Cipher.ENCRYPT_MODE, file, encrypted);
            System.out.println("Encryption complete");
        }
        catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void decrypt(File file)  {

        File decrypted = new File("decrypted.txt");

        try {
            encryptDecrypt("12345678", Cipher.DECRYPT_MODE, file, decrypted);
            System.out.println("Decryption complete");
        }
        catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | IOException e)
        {
            e.printStackTrace();
        }
    }

}
