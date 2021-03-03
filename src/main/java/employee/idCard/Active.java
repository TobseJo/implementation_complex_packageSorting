package employee.idCard;

public class Active implements ICardState{

    @Override
    public void promote(IDCard idCard) {
        System.out.println("active -> locked");
        idCard.setState(new Locked());
    }

    @Override
    public void print() {
        System.out.println("IDCard is in State: active");
    }
}
