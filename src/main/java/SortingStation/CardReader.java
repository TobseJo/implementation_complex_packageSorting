package SortingStation;

import configuration.Configuration;
import employee.Employee;
import employee.idCard.Active;
import employee.idCard.Invalid;
import employee.idCard.Locked;

public class CardReader {

    public void readCardFromEmployee(Employee employee){
        int validPin = -1;
        int validSuperPin = -1;
        if(employee.getIdCard().getState() instanceof Active){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                stringBuilder.append(employee.getIdCard().getMagnetStripe()[i][0]);
            }
            validPin = Integer.valueOf(Configuration.instance.usedAlgorithm.decrypt(stringBuilder.toString()));

            System.out.println("Please insert your PIN!");
            int ctr = 0;
            while(validPin != employee.getPin()){
                if(ctr < 3){
                    System.out.println("Wrong pin inserted, retry!");
                    ctr++;
                }else {
                    employee.getIdCard().setState(new Locked());
                    System.out.println("Card is locked now and can be unlocked with superPin!");
                    break;
                }
            }

            //TODO perhaps put this in the else tree after Locked is set
        }else if(employee.getIdCard().getState() instanceof Locked){
            System.out.println("Please insert your SuperPIN!");
            int ctr = 0;
            while(validSuperPin != employee.getSuperPin()){
                if(ctr < 2){
                    System.out.println("Wrong SuperPin inserted, retry!");
                    ctr++;
                }else {
                    employee.getIdCard().setState(new Invalid());
                    System.out.println("Card is invalid now!");
                    break;
                }
            }
        }else if(employee.getIdCard().getState() instanceof Invalid){
            System.out.println("This card cannot be read anymore and it cannot be unlocked by Pin/SuperPin!");
        }else{
            System.out.println("This card is in unknown State so it cannot be read!");
        }
    }


}
