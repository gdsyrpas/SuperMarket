package API;

import Entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSearch {
    public static List<Product> searchProductsByTitle(List<Product> products, String query) {
        return products.stream()
                .filter(product -> product.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}