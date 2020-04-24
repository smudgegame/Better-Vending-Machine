import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ManagerTest {

    @Test
    public void defaultDisplayState() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        assertEquals("INSERT COIN", manager.display());
    }

    @Test
    public void displaySum() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        manager.insertCoin(Coin.QUARTER);
        assertEquals("$0.25", manager.display());
    }

    @Test
    public void displayFormat() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        manager.insertCoin(Coin.DIME);
        assertEquals("$0.10", manager.display());
    }

    @Test
    public void productCostsTooMuch() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        inventory.stock(Product.COLA, 1);
        manager.selectProduct(Product.COLA);
        assertEquals("PRICE $1.00", manager.display());
    }

    @Test
    public void makePurchase() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        for (int i = 0; i < 4; i++) manager.insertCoin(Coin.QUARTER);
        inventory.stock(Product.COLA, 1);
        manager.selectProduct(Product.COLA);
        assertEquals("THANK YOU", manager.display());
        assertEquals("INSERT COIN", manager.display());
    }

    @Test
    public void dispensePurchase() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        for (int i = 0; i < 4; i++) manager.insertCoin(Coin.QUARTER);
        inventory.stock(Product.COLA, 1);
        manager.selectProduct(Product.COLA);
        assertEquals(Product.COLA, manager.dispensedProduct);
    }

    @Test
    public void changeValueToZero() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        for (int i = 0; i < 5; i++) manager.insertCoin(Coin.QUARTER);
        inventory.stock(Product.COLA, 2);
        manager.selectProduct(Product.COLA);
        int quarters = coinManager.inReturn(Coin.QUARTER);
        assertEquals(1, quarters);
        manager.display();
        manager.display();
        manager.selectProduct(Product.COLA);
        assertEquals("PRICE $1.00", manager.display());
    }

    @Test
    public void returnMoney() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        for (int i = 0; i < 5; i++) manager.insertCoin(Coin.QUARTER);
        manager.reset();
        int quarters = coinManager.inReturn(Coin.QUARTER);
        assertEquals(5, quarters);
    }

    @Test
    public void soldOut() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, false);
        for (int i = 0; i < 5; i++) manager.insertCoin(Coin.QUARTER);
        manager.selectProduct(Product.COLA);
        assertEquals("SOLD OUT", manager.display());
        assertEquals("$1.25", manager.display());
    }

    @Test
    public void exactChange() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, true);
        assertEquals("EXACT CHANGE ONLY", manager.display());
    }

    @Test
    public void requiresExactChange() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> stock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, stock);

        Manager manager = new Manager(coinManager, inventory, VendingState.VENDING, true);
        for (int i = 0; i < 3; i++) manager.insertCoin(Coin.QUARTER);
        inventory.stock(Product.COLA, 1);
        manager.selectProduct(Product.COLA);
        assertEquals("NOT EXACTLY $1.00", manager.display());
        manager.insertCoin(Coin.QUARTER);
        manager.selectProduct(Product.COLA);
        assertEquals("THANK YOU", manager.display());
    }
}
