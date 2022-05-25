package com.example.binance.event;

import org.springframework.context.ApplicationEvent;

public class BinanceEvent extends ApplicationEvent {

    private final AccessType accessType;

    public BinanceEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "BinanceEvent{" +
                "accessType=" + accessType +
                ", source=" + source +
                '}';
    }
}
