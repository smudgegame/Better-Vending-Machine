public class Manager {

    private static CoinManager coinManager;
    private static Inventory inventory;
    private static VendingState vendingState = VendingState.VENDING;
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
            case VENDING:
                if (sum == 0) state = "INSERT COIN";
                else state = format(sum);
                break;
            case NOT_ENOUGH_MONEY:
                vendingState = VendingState.VENDING;
                state = "PRICE " + format(inventory.getPrice());
                break;
            case PURCHASE:
                vendingState = VendingState.VENDING;
                state = "THANK YOU";
                break;
            case SOLD_OUT:
                vendingState = VendingState.VENDING;
                state = "SOLD OUT";
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
        if (inventory.inStock(product)) {
            if (sum >= price) {
                dispensedProduct = product;
                vendingState = VendingState.PURCHASE;
                coinManager.makeChange(price);
            } else vendingState = VendingState.NOT_ENOUGH_MONEY;
        }else  vendingState = VendingState.SOLD_OUT;
    }

    public void reset() {
        coinManager.returnMoney();
    }

    private String format(int sum) {
        if (sum % 10 == 0) {
            return "$" + (double) sum / 100 + "0";
        } else {
            return "$" + (double) sum / 100;
        }
    }


}
