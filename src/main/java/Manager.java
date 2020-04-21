public class Manager {

    private static CoinManager coinManager;
    private static Inventory inventory;
    private static VendingState vendingState = VendingState.IDLE;
    public Product dispensedProduct = null;

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
                vendingState = VendingState.IDLE;
                state = "PRICE " + format(inventory.getPrice());
                break;
            case PURCHASE:
                vendingState = VendingState.IDLE;
                state = "THANK YOU";
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
        if (sum >= price) {
            dispensedProduct = product;
            vendingState = VendingState.PURCHASE;
        } else vendingState = VendingState.NOT_ENOUGH_MONEY;
    }

    private String format(int sum) {
        if (sum % 10 == 0) {
            return "$" + (double) sum / 100 + "0";
        } else {
            return "$" + (double) sum / 100;
        }
    }

}
