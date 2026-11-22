package org.route.repository;

import jakarta.annotation.PostConstruct;
import org.route.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> myProducts = new CopyOnWriteArrayList<>();

    @PostConstruct
    void init() {
        myProducts.add(Product.builder()
                .sku("P001")
                .name("Desk Lamp")
                .price(BigDecimal.valueOf(800))
                .inStock(10)
                .category("Lighting")
                .brandSlug(null)
                .build());

        myProducts.add(Product.builder()
                .sku("P003")
                .name("ASUS TUF")
                .price(BigDecimal.valueOf(30000))
                .inStock(10)
                .category("laptops")
                .brandSlug("Asus")
                .build());

        myProducts.add(Product.builder()
                .sku("PH-A55-128")
                .name("Samsung")
                .price(new BigDecimal("18500.00"))
                .inStock(7)
                .category("mobile-phones")
                .brandSlug("Samsung")
                .build());
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(myProducts));
    }

    @Override
    public Product findBySku(String sku) {
        if (sku == null) {
            return null;
        }
        for (Product product : myProducts) {
            if (product.getSku().equalsIgnoreCase(sku.trim())) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        myProducts.add(product);
    }
}
