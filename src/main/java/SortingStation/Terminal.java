package SortingStation;

public class Terminal {
    private TouchPad touchPad;
    private CardReader cardReader;

    public Terminal(TouchPad touchPad, CardReader cardReader) {
        this.touchPad = touchPad;
        this.cardReader = cardReader;
    }
}
