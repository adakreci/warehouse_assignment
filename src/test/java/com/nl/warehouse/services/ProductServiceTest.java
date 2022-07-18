package com.nl.warehouse.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Map;

import com.nl.warehouse.models.Article;
import com.nl.warehouse.models.Product;
import com.nl.warehouse.exceptions.NoProductException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    private static final Long LEG = 1L;
    private static final Long SCREW = 2L;
    private static final String Rainbow_CHAIR = "Rainbow chair";
    private static final String DINING_CHAIR = "Dining Chair";
    private static final String DINNING_TABLE = "Dinning Table";

    @Autowired
    ProductService productService;

    @Autowired
    InventoryService inventoryService;

    @Test
    public void testFindAllProducts_success() throws IOException {
        Map<String, Product> products = productService.findAll();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProduct_success() {
        assertNotNull(productService.getProduct(DINING_CHAIR));
    }

    @Test
    public void testGetProduct_nonExistentProduct() {
        assertNull(productService.getProduct(Rainbow_CHAIR));

    }

    @Test
    public void testProductAvailability_success() {
        inventoryService.init();
        Product productChair = productService.getProduct(DINING_CHAIR);
        assertTrue(productService.isProductAvailable(productChair));
        Product productTable = productService.getProduct(DINNING_TABLE);
        assertTrue(productService.isProductAvailable(productTable));

    }

    @Test
    public void testSellProductAncCheckAvail_success() {

        inventoryService.init();
        Map<Long, Article> inventory = inventoryService.findAll();
        assertEquals(inventory.get(LEG).getStock(), 12);
        assertEquals(inventory.get(SCREW).getStock(), 17);

        Map<String, Product> availProducts = productService.findAllAvailable();
        assertEquals(2, availProducts.size());

        productService.sellProduct(DINING_CHAIR);
        inventory = inventoryService.findAll();
        assertEquals(inventory.get(LEG).getStock(), 8);
        assertEquals(inventory.get(SCREW).getStock(), 9);
        Product productChair = productService.getProduct(DINING_CHAIR);
        assertTrue(productService.isProductAvailable(productChair));
        availProducts = productService.findAllAvailable();
        assertEquals(2, availProducts.size());

        productService.sellProduct(DINING_CHAIR);
        inventory = inventoryService.findAll();
        assertEquals(inventory.get(LEG).getStock(), 4);
        assertEquals(inventory.get(SCREW).getStock(), 1);
        assertFalse(productService.isProductAvailable(productChair));
        availProducts = productService.findAllAvailable();
        assertEquals(0, availProducts.size());

        Exception exception = assertThrows(NoProductException.class, () -> {
            // out of stock
            productService.sellProduct(DINING_CHAIR);
        });
        String expectedMessage = "Product " + DINING_CHAIR
                + " no longer in stock.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        inventory = inventoryService.findAll();
        assertEquals(inventory.get(LEG).getStock(), 4);
        assertEquals(inventory.get(SCREW).getStock(), 1);

    }

}