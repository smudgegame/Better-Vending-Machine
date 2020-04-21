public class Manager {

    private static CoinManager coinManager;
    private static Inventory inventory;
    private static VendingState vendingState = VendingState.IDLE;

    public Manager(CoinManager coinManager, Inventory inventory, VendingState vendingState) {
        Manager.coinManager = coinManager;
        Manager.inventory = inventory;
        Manager.vendingState = vendingState;
    }

    public String display() {
        String state = "";
        int sum = coinManager.currentSum();
        switch (vendingState) {
            case IDLE:
                state = "INSERT COIN";
                break;
            case VENDING:
                state = format(sum);
                break;
            case NOT_ENOUGH_MONEY:
                state = "PRICE " + format(inventory.getPrice());
                break;
        }
        return state;
    }

    public void insertCoin(Coin coin) {
        coinManager.receiveCoin(coin);
        vendingState = VendingState.VENDING;
    }

    public void selectProduct(Product product) {
        inventory.setProduct(product);
        int sum = coinManager.currentSum();
        int price = inventory.getPrice();
        if (price > sum) vendingState = VendingState.NOT_ENOUGH_MONEY;
    }

    private String format(int sum) {
        if (sum % 10 == 0) {
            return "$" + (double) sum / 100 + "0";
        } else {
            return "$" + (double) sum / 100;
        }
    }

}
