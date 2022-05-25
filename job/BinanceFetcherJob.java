package com.example.binance.job;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import com.example.binance.entity.Code;
import com.example.binance.provider.SettingsProvider;
import com.example.binance.service.CodeService;
import com.example.binance.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class BinanceFetcherJob {

    private final SettingsProvider settingsProvider;
    private final CodeService codeService;
    private final PriceService priceService;
    private final BinanceApiRestClient binanceApiRestClient;

    @Scheduled(fixedRate = 60000) // по расписанию.
    private void call() {
        List<TickerPrice> tickerPrices = binanceApiRestClient.getAllPrices();
        BigDecimal multiply = settingsProvider.getMultiply();
        tickerPrices.forEach(tickerPrice -> {
            String codeName = null;
            BigDecimal price = BigDecimal.ZERO;
            try {
                codeName = tickerPrice.getSymbol();
                Code code = codeService.get(codeName);
                price = new BigDecimal(tickerPrice.getPrice());
                priceService.save(code, price.multiply(multiply).toBigInteger(), LocalDateTime.now());
            } catch (Exception e) {
                log.error("Code: {}, Price: {}, Exception: {}", codeName, price, e.getMessage());
            }
        });
    }
}
