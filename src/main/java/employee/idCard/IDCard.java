package employee.idCard;

public class IDCard {
    private ICardState state;
    private char[][] magnetStripe;

    public void promote() {
        state.promote(this);
    }

    public IDCard(String magnetStripeString){
        state = new Active();
        magnetStripe = new char[100][1];
        for (int i = 0; i < magnetStripeString.length(); i++) {
            magnetStripe[i][0] = magnetStripeString.charAt(i);
        }
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
