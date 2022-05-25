package com.example.binance.config;


import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.example.binance.provider.SettingsProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BinanceRestConfig {

    private final SettingsProvider settingsProvider;


    @Bean
    public BinanceApiRestClient binanceApiRestClient() {
        return BinanceApiClientFactory.newInstance(settingsProvider.getApiKey(), settingsProvider.getSecretKey()).newRestClient();
    }


}
