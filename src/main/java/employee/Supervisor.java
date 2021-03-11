package employee;


public class Supervisor extends Employee{
    private boolean isSenior;

    public Supervisor(int id, String name, boolean isSenior, int pin, int superPin) {
        super(id, name, pin, superPin);
        this.isSenior = isSenior;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
