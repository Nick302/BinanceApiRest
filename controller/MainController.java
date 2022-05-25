package com.example.binance.controller;

import com.example.binance.entity.Price;
import com.example.binance.event.AccessType;
import com.example.binance.event.BinanceEvent;
import com.example.binance.repository.PriceRepository;
import com.example.binance.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class MainController {

    private final PriceRepository priceRepository;
    private final PriceService priceService;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public MainController(PriceRepository priceRepository, PriceService priceService, ApplicationEventPublisher publisher) {
        this.priceRepository = priceRepository;
        this.priceService = priceService;
        this.publisher = publisher;
    }

    @GetMapping(path = "/api/{code_id}/{amount}", produces = "application/json")
    public   List<Price>  getBook(@PathVariable Long code_id, @PathVariable Long amount) {
        publisher.publishEvent(new BinanceEvent(code_id, AccessType.READ));
        log.info(" >>> MainController >>> getBook : Success");

         return priceService.findPriceByCode_Id(code_id).stream().sorted().limit(amount).collect(Collectors.toList());
    }

     @GetMapping("/test/{code}")
    public List<Price> getAll(@PathVariable Long code){
        return priceRepository.findAllByCode_Id(code);
    }


    @GetMapping("/pageable/{pages}/{amount}/{sort}")
    public List<Price> getAllPage(@PathVariable Integer pages, @PathVariable Integer amount , @PathVariable String sort){
       var req =  PageRequest.of(pages,amount,Sort.by(sort));
        return  priceRepository.findAllBy(req);
    }


}
