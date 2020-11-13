package com.icemelon.scheduler.service;

import com.icemelon.scheduler.dto.UniqueCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidCodeGenerator implements ICodeGenerator {

    @Override
    public UniqueCode makeUniqueCode() {

       return UniqueCode.fromString(UUID.randomUUID().toString());
    }
}
