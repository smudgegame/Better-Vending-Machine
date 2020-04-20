public class Display {

    private static CoinManager coinManager;

    public Display(CoinManager coinManager) {
        Display.coinManager = coinManager;
    }

    public String show() {
        int sum = coinManager.currentSum();
        String state = "INSERT COIN";
        if (sum != 0) state = format(sum);
        return state;
    }

    private String format(int sum) {
        if (sum % 10 == 0) {
            return "$" + (double) sum / 100 + "0";
        } else {
            return "$" + (double) sum / 100;
        }
    }

}
