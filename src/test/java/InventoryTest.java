import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

    @Test
    public void productPrice() {
        Map<Product, Integer> currentStock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, currentStock);
        inventory.setProduct(Product.COLA);
        assertEquals(100, inventory.getPrice());
    }

    @Test
    public void inventoryTracking() {
        Map<Product, Integer> currentStock = new HashMap<>();
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap, currentStock);
        inventory.stock(Product.COLA,1);
        inventory.remove(Product.COLA);
        int colaStock = currentStock.get(Product.COLA);
        assertEquals(0, colaStock);
    }
}
