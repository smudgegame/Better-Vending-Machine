import org.junit.Test;
import sun.security.krb5.internal.crypto.EType;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

    @Test
    public void productPrice() {
        Map<Product, Integer> priceMap = new HashMap<>();
        Inventory inventory = new Inventory(null, priceMap);
        inventory.setProduct(Product.COLA);
        assertEquals(100, inventory.getPrice());
    }

}
