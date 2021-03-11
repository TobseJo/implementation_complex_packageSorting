package SortingStation;

import employee.Employee;
import employee.idCard.Active;
import employee.idCard.IDCard;
import employee.idCard.Invalid;
import employee.idCard.Locked;

public class CardReader {

    //TODO testen fehlt noch
    public void readCardFromEmployee(Employee employee) {
        IDCard idCard = employee.swipeCard();
        if (idCard.getState() instanceof Active) {
            int validPin = Integer.valueOf(readMagnetStripe(idCard).split(";")[3]);
            System.out.println("Please insert your PIN!");
            int ctr = 0;
            while (validPin != employee.getPin()) {
                if (ctr < 3) {
                    System.out.println("Wrong pin inserted, retry!");
                    ctr++;
                } else {
                    idCard.promote();
                    System.out.println("Card is locked now and can be unlocked with superPin!");
                    readCardFromEmployee(employee);
                    break;
                }
            }
            if(validPin == employee.getPin()){
                System.out.println("Pin is correct!");
            }

        } else if (idCard.getState() instanceof Locked) {

            int validSuperPin = Integer.valueOf(readMagnetStripe(idCard).split(";")[4]);

            System.out.println("Please insert your SuperPIN!");
            int ctr = 0;
            while (validSuperPin != employee.getSuperPin()) {
                if (ctr < 2) {
                    System.out.println("Wrong SuperPin inserted, retry!");
                    ctr++;
                } else {
                    idCard.promote();
                    System.out.println("Card is invalid now!");
                    readCardFromEmployee(employee);
                    break;
                }
            }
        } else if (idCard.getState() instanceof Invalid) {
            System.out.println("This card cannot be read anymore and it cannot be unlocked by Pin/SuperPin!");
        } else {
            System.out.println("This card is in unknown State so it cannot be read!");
        }
    }

    private String readMagnetStripe(IDCard idCard){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            stringBuilder.append(idCard.getMagnetStripe()[i][0]);
        }
        return stringBuilder.toString();
    }


}
