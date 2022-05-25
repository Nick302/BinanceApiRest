package com.example.binance.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component // чтоб слушал события , в компоненте был
public class BinanceListener {

    @EventListener
    public void acceptBinanceEntity(BinanceEvent event) {
        System.out.println("Событие: " + event.toString());
    }

}
