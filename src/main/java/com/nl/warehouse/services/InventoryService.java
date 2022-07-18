package com.nl.warehouse.services;

import com.nl.warehouse.events.SellEvent;
import com.nl.warehouse.models.Article;
import com.nl.warehouse.models.Inventory;
import com.nl.warehouse.models.ProductComposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryService extends WarehouseService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Long, Article> inventories = new HashMap<>();

    @Value("${warehouse.inventory.data}")
    private String inventoriesFile;

    @PostConstruct
    public void init() {
        try {
            log.info("init InventoryService");
            Inventory inventory = loadFile(inventoriesFile);
            if (inventory != null && inventory.getArticles() != null) {
                log.info("loaded {} inventory items", inventory.getArticles().size());
                loadArticles(inventory.getArticles());
            } else {
                log.warn("inventory empty");
            }
        } catch (IOException e) {
            log.error("Error while loading inventory : {}", e.getMessage());
        }
    }

    public Map<Long, Article> findAll() {
        return this.inventories;
    }

    public void loadArticles(List<Article> articles) {
        this.inventories = articles.stream().collect(Collectors.toMap(Article::getId, article -> article));
    }

    public boolean isArticleAvailable(Long artId, Long amount) {
        if (this.inventories.containsKey(artId)) {
            Article article = this.inventories.get(artId);
            return amount <= article.getStock() && article.getStock() >= 0;
        } else {
            return false;
        }
    }

    @EventListener
    public void handleContextStart(SellEvent event) {
        log.info("Event for selling the product {}", event.getProduct().getName());
        List<ProductComposition> parts = event.getProduct().getProductCompositions();
        for (ProductComposition productPart : parts) {
            if (isArticleAvailable(productPart.getId(), productPart.getAmount())) {
                removeArticleFromStock(productPart.getId(), productPart.getAmount());
            }
        }
    }

    private void removeArticleFromStock(Long id, Long amount) {
        log.info("Removing {} articles from stock for id {}", amount, id);
        Article article = getArticle(id);
        article.setStock(article.getStock() - amount);
    }

    private Article getArticle(Long id) {
        return this.inventories.get(id);
    }

}
