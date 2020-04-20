public class Display {

    private static CoinManager coinManager;

    public Display(CoinManager coinManager) {
        this.coinManager = coinManager;
    }

    public String show() {
        String state = "INSERT COIN";
        if (coinManager.currentSum() != 0) state = "$" + (double) coinManager.currentSum() / 100;
        return state;
    }


}
