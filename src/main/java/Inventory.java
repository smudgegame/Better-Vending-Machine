import java.util.Map;

public class Inventory {

    private Product product;
    private Map<Product, Integer> priceMap;
    private Map<Product, Integer> currentStock;

    public Inventory(Product product, Map<Product, Integer> priceMap, Map<Product, Integer> currentStock) {
        this.product = product;
        this.priceMap = priceMap;
        this.currentStock = currentStock;
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

    public void stock(Product product, int amount) {
        currentStock.put(product, currentStock.getOrDefault(product, 0) + amount);
    }

    public boolean inStock(Product product) {
        return currentStock.getOrDefault(product,0) != 0;
    }

    public void remove(Product product) {
        currentStock.put(product, currentStock.get(product) - 1);
    }
}
