package com.nl.warehouse.services;

import com.nl.warehouse.events.publisher.SellProductPublisher;
import com.nl.warehouse.exceptions.NoProductException;
import com.nl.warehouse.models.Inventory;
import com.nl.warehouse.models.Product;
import com.nl.warehouse.models.ProductComposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService extends WarehouseService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${warehouse.products.data}")
    private String productsFile;

    private Map<String, Product> products = new HashMap<>();

    private final InventoryService inventoryService;

    private final SellProductPublisher  sellProductPublisher;

    public ProductService(InventoryService inventoryService, SellProductPublisher sellProductPublisher) {
        this.inventoryService = inventoryService;
        this.sellProductPublisher = sellProductPublisher;
    }

    @PostConstruct
    public void init() {
        try {
            log.info("init ProductService");
            Inventory inventory = loadFile(productsFile);
            if (inventory != null && inventory.getProducts() != null) {
                log.info("loaded {} products", inventory.getProducts().size());
                loadProducts(inventory.getProducts());
            } else {
                log.warn("products empty");
            }
        } catch (IOException e) {
            log.error("Error while loading products : {}", e.getMessage());
        }

    }

    public Map<String, Product> findAll() {
        return this.products;
    }

    public Map<String, Product> findAllAvailable() {
        Map<String, Product> products = new HashMap<>();
        this.products.forEach((k, product) -> {
            if (isProductAvailable(product)) {
                products.put(product.getName(), product);
            }
        });
        return products;
    }

    public boolean isProductAvailable(Product product) {
        for (ProductComposition composition : product.getProductCompositions()) {
            if (!inventoryService.isArticleAvailable(composition.getId(), composition.getAmount())) {
                return false;
            }
        }
        return true;
    }

    public void loadProducts(List<Product> products) {
        this.products = products.stream().collect(Collectors.toMap(Product::getName, product -> product));
    }

    public Product getProduct(String name) {
        return this.products.get(name);
    }

    public void sellProduct(String name) {
        Product product = getProduct(name);
        if (isProductAvailable(product)) {
            sellProductPublisher.publish(product);
        } else {
            throw new NoProductException(
                    "Product " + name + " no longer in stock.");

        }
    }

}
