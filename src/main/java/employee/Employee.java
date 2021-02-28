package employee;

import employee.idCard.IDCard;

public abstract class Employee {
    protected IDCard idCard;
    protected int id;
    protected String name;
    protected int pin;
    protected int superPin;

    public Employee(int id, String name, IDCard idCard, int pin, int superPin){
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.pin = pin;
        this.superPin = superPin;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPin() {
        return pin;
    }

    public int getSuperPin() {
        return superPin;
    }
}
