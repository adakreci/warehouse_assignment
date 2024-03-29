package com.nl.warehouse.events.listeners;

import com.nl.warehouse.events.SellEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class SellEventListener implements ApplicationListener<SellEvent> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onApplicationEvent(SellEvent event) {
        log.info("SellEventListener event {}", event.getProduct().getName());
    }

}
