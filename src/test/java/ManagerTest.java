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

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        assertEquals("INSERT COIN", manager.display());
    }

    @Test
    public void displaySum() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        manager.insertCoin(Coin.QUARTER);
        assertEquals("$0.25", manager.display());
    }

    @Test
    public void displayFormat() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        manager.insertCoin(Coin.DIME);
        assertEquals("$0.10", manager.display());
    }

    @Test
    public void productCostsTooMuch() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        manager.selectProduct(Product.COLA);
        assertEquals("PRICE $1.00", manager.display());
    }

    @Test
    public void makePurchase() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        for (int i = 0; i < 4; i++) manager.insertCoin(Coin.QUARTER);
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

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        for (int i = 0; i < 4; i++) manager.insertCoin(Coin.QUARTER);
        manager.selectProduct(Product.COLA);
        assertEquals(Product.COLA, manager.dispensedProduct);
    }

    @Test
    public void changeValueToZero() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);

        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);

        Manager manager = new Manager(coinManager, inventory, VendingState.IDLE);
        for (int i = 0; i < 5; i++) manager.insertCoin(Coin.QUARTER);
        manager.selectProduct(Product.COLA);
        int quarters = manager.coinReturn.get(Coin.QUARTER);
        assertEquals(1, quarters);
        manager.display();
        manager.display();
        manager.selectProduct(Product.COLA);
        assertEquals("PRICE $1.00", manager.display());
    }
}
