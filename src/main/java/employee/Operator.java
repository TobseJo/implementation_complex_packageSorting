package employee;

import employee.idCard.IDCard;

public class Operator extends Employee{
    public Operator(int id, String name, IDCard idCard, int pin, int superPin) {
        super(id, name, idCard, pin, superPin);
    }
}
