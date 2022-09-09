package com.santander.service;

import com.santander.dto.UserPayloadRequest;
import com.santander.entity.Transaction;
import com.santander.helper.IdGenerator;
import com.santander.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    IdGenerator idGenerator;
    @Autowired
    private TransactionRepository repository;

    public Transaction saveTransaction(UserPayloadRequest payload) {

        return repository.save(Transaction.builder().
                amout(payload.getAmount())
                .senderId(payload.getSender())
                .receiverId(payload.getReceiver())
                .Id(IdGenerator.getId()).build());

    }

    public List<Transaction> getTransactionBySender(String senderId) {


        return repository.findBySenderId(senderId);

    }

    public List<Transaction> getAllTransactions() {


        Iterable<Transaction> transactions = repository.findAll();
        List<Transaction> list = new ArrayList<Transaction>();
        transactions.forEach(list::add);
        return list;
    }
}
