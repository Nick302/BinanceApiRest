package com.example.binance.job;

import com.example.binance.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
@EnableScheduling
public class ClearIldRecords {

    private final PriceService priceService;

    @Scheduled(fixedRate = 60000)
    private void clear24After() {
        priceService.clear(LocalDateTime.now().minusHours(24));
    }

}
