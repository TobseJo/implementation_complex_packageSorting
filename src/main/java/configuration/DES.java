package configuration;

import employee.idCard.IDCard;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class DES implements IStrategy{

    private static SecretKeySpec secretKey;


    public static void setKey(String myKey) {
        try {
            var key = myKey.getBytes("UTF-8");

            var sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 24);

            secretKey = new SecretKeySpec(key, "DESede");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encrypt(String strToEncrypt, IDCard idCard) {

        try {
            setKey(Configuration.instance.key);

            var cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            var buffer = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(buffer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
