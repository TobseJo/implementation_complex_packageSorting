package configuration;

import employee.idCard.IDCard;

public interface IStrategy {
    public void encrypt(IDCard idCard);
}
