package base;

public interface IStrategy {
    String encrypt(String magnetStripe);
    String decrypt(String encryptedMessage);
}
