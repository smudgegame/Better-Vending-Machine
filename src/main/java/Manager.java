public class Manager {

    private static CoinManager coinManager;
    private static Inventory inventory;
    private static VendingState vendingState = VendingState.VENDING;
    public Product dispensedProduct = null;
    private static boolean exactChange;

    public Manager(CoinManager coinManager, Inventory inventory, VendingState vendingState, boolean exactChange) {
        Manager.coinManager = coinManager;
        Manager.inventory = inventory;
        Manager.vendingState = vendingState;
        Manager.exactChange = exactChange;
        vendingStateToDefault();
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
                vendingStateToDefault();
                state = "PRICE " + format(inventory.getPrice());
                break;
            case PURCHASE:
                vendingStateToDefault();
                state = "THANK YOU";
                break;
            case SOLD_OUT:
                vendingStateToDefault();
                state = "SOLD OUT";
                break;
            case EXACT_CHANGE_ONLY:
                if (sum == 0) state = "EXACT CHANGE ONLY";
                else state = format(sum);
                break;
            case NOT_EXACT:
                vendingStateToDefault();
                state = "NOT EXACTLY " + format(inventory.getPrice());
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
            if (exactChange) {
                if (sum == price) {
                    makeSale(product);
                } else vendingState = VendingState.NOT_EXACT;
            } else {
                if (sum >= price) {
                    makeSale(product);
                    coinManager.makeChange(price);
                } else vendingState = VendingState.NOT_ENOUGH_MONEY;
            }
        } else vendingState = VendingState.SOLD_OUT;
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

    private void vendingStateToDefault() {
        if (exactChange) {
            vendingState = VendingState.EXACT_CHANGE_ONLY;
        } else {
            vendingState = VendingState.VENDING;
        }
    }

    private void makeSale(Product product) {
        dispensedProduct = product;
        inventory.remove(product);
        inventory.setProduct(null);
        vendingState = VendingState.PURCHASE;
    }

}
