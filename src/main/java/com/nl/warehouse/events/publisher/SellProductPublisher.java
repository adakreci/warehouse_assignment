package com.nl.warehouse.events.publisher;

import com.nl.warehouse.events.SellEvent;
import com.nl.warehouse.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SellProductPublisher {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ApplicationEventPublisher applicationEventPublisher;

    public SellProductPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(final Product product) {
        log.info("publish product selling event");
        SellEvent customSpringEvent = new SellEvent(this, product);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
