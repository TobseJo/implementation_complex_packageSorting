package employee.idCard;

public class Invalid implements ICardState {
    @Override
    public void promote(IDCard idCard) {
        System.out.println("IdCard is now Invalid and cannot be unlocked again!");
    }

    @Override
    public void print() {
        System.out.println("IDCard is in State: invalid");
    }
}
