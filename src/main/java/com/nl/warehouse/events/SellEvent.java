package com.nl.warehouse.events;

import com.nl.warehouse.models.Product;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class SellEvent extends ApplicationEvent {
    private Product product;

    public SellEvent(Object src, Product product) {
        super(src);
        this.product = product;
    }

}
