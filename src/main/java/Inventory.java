import java.util.Map;

public class Inventory {

    private Product product;
    private Map<Product, Integer> priceMap;

    public Inventory(Product product, Map<Product, Integer> priceMap) {
        this.product = product;
        this.priceMap = priceMap;
        priceMap.put(Product.COLA, 100);
        priceMap.put(Product.CHIPS, 50);
        priceMap.put(Product.CANDY, 65);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return priceMap.get(product);
    }
}
