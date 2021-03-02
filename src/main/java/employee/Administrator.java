package employee;

import employee.idCard.IDCard;

public class Administrator extends Employee{
    private Profile profile;

    public Administrator(int id, String name, Profile profile, int pin, int superPin) {
        super(id, name, pin, superPin);
        this.profile = profile;
    }
}
