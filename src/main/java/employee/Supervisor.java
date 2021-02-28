package employee;

import employee.idCard.IDCard;

public class Supervisor extends Employee{
    private boolean isSenior;

    public Supervisor(int id, String name, boolean isSenior, IDCard idCard, int pin, int superPin) {
        super(id, name, idCard, pin, superPin);
        this.isSenior = isSenior;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
