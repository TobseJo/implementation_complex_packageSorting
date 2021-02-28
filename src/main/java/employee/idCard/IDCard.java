package employee.idCard;

public class IDCard {
    private ICardState state;
    private char[][] magnetStripe;

    public IDCard(){
        state = new Active();
        magnetStripe = new char[100][1];
    }

    public char[][] getMagnetStripe() {
        return magnetStripe;
    }

    public ICardState getState() {
        return state;
    }

    public void setState(ICardState state) {
        this.state = state;
    }
}
