package com.horyzonty.api.endpoint.cash;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

public class Controller {

    public ResponseEntity<Void> addIncome(@RequestBody AddIncomeRequest request) {

        Date now = new Date();
//        entityManager.save(request.getValue(), now, request.getKto());

        return ResponseEntity.ok().build();
    }
        
}
