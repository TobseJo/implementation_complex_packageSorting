package employee.idCard;

public class Locked implements ICardState {
    @Override
    public void promote(IDCard idCard) {
        System.out.println("locked -> invalid");
        idCard.setState(new Invalid());
    }

    @Override
    public void print() {
        System.out.println("IDCard is in State: locked");
    }
}
