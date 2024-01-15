import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptionUtilTest {

    @Test
    void testEncryptData() {
        String data = "Hello, this is a secret message!";
        String secretKey = "YourSecretKey123";

        String encryptedData = EncryptionUtil.encryptData(data, secretKey);

        assertNotNull(encryptedData);
        assertNotEquals(data, encryptedData);
    }

    @Test
    void testDecryptData() {
        String data = "Hello, this is a secret message!";
        String secretKey = "YourSecretKey123";
        String encryptedData = EncryptionUtil.encryptData(data, secretKey);

        String decryptedData = EncryptionUtil.decryptData(encryptedData, secretKey);

        assertNotNull(decryptedData);
        assertEquals(data, decryptedData);
    }

    @Test
    void testEncryptAndDecryptData() {
        String data = "Hello, this is a secret message!";
        String secretKey = "YourSecretKey123";

        String encryptedData = EncryptionUtil.encryptData(data, secretKey);
        String decryptedData = EncryptionUtil.decryptData(encryptedData, secretKey);

        assertNotNull(encryptedData);
        assertNotNull(decryptedData);
        assertEquals(data, decryptedData);
    }

    @Test
    void testEncryptAndDecryptWithDifferentKeys() {
        String data = "Hello, this is a secret message!";
        String secretKey1 = "YourSecretKey123";
        String secretKey2 = "YourSecretKey124";

        String encryptedData = EncryptionUtil.encryptData(data, secretKey1);
        String decryptedData = EncryptionUtil.decryptData(encryptedData, secretKey2);

        assertNull(decryptedData);
    }
}
