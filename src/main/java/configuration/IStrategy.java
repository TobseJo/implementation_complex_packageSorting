package configuration;

public interface IStrategy {
    public String encrypt(String magnetStripe);

    public String decrypt(String encryptedMessage);
}
