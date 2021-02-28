package employee;

import employee.idCard.IDCard;

public class Administrator extends Employee{
    private Profile profile;

    public Administrator(int id, String name, IDCard idCard, Profile profile, int pin, int superPin) {
        super(id, name, idCard, pin, superPin);
        this.profile = profile;
    }
}
