package com.example.binance.service;

import com.example.binance.entity.Code;
import com.example.binance.repository.CodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    public Code save(String codeName) {
        Code code = new Code();
        code.setName(codeName);
        log.info(">>> CodeService: save {}", codeName);
        return codeRepository.save(code);
    }

    public Code get(String codeName) {
        log.info(">>> CodeService: get {}", codeName);
        return codeRepository.getByName(codeName).orElseGet(() -> save(codeName));
    }
}
