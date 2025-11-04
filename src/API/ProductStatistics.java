package API;

import Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductStatistics {


    public static List<Product> getOutOfStockProducts(List<Product> products) {
        List<Product> outOfStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() == 0) {
                outOfStockProducts.add(product);
            }
        }
        return outOfStockProducts;
    }
}