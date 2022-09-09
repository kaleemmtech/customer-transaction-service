package com.santander.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santander.dto.TransactionId;
import com.santander.dto.UserPayloadRequest;
import com.santander.entity.Transaction;
import com.santander.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/api/transactions/{senderId}")
    public List<Transaction> getTransaction(@PathVariable String senderId) {
        log.info("api/transactions/{}", senderId);
        return customerService.getTransactionBySender(senderId);
    }


    @GetMapping("/api/transactions")
    public List<Transaction> getAllTransaction() {

        return customerService.getAllTransactions();

    }

    @PostMapping("/api/transaction")
    public ResponseEntity<TransactionId> transferMoney(@Valid @RequestBody UserPayloadRequest payload) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Transaction transaction = customerService.saveTransaction(payload);
        return new ResponseEntity(new TransactionId(transaction.getId()), HttpStatus.CREATED);

    }

}





