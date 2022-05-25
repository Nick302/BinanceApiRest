package com.example.binance.service;

import com.example.binance.entity.Code;
import com.example.binance.entity.Price;
import com.example.binance.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;

    public Optional<Price> getLastPrice(Code code) {
        log.info(">>>  PriceService: getLastPrice");
        return priceRepository.findFirstByCodeOrderByTimeDesc(code);
    }

    public void clear(LocalDateTime upTo) {
        log.info(">>>  PriceService: Clear");
        this.priceRepository.deleteAllByTimeLessThan(upTo);
    }

    public Price save(Code code, BigInteger priceVal, LocalDateTime time) {
        Price price = new Price();
        price.setCode(code);
        price.setVal(priceVal);
        price.setTime(time);
        log.info(">>> PriceService: SAVE: >>> code: {} , val: {}", code.getId(), priceVal);
        return priceRepository.save(price);
    }

    public   List<Price> findPriceByCode_Id(Long code_id) {
        return priceRepository.findPriceByCode_Id(code_id);
    }


}
