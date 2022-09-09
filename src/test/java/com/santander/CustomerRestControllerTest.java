package com.santander;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.dto.UserPayloadRequest;
import com.santander.repository.TransactionRepository;
import com.santander.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
public class CustomerRestControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private CustomerService service;

    @Autowired
    private TransactionRepository repository;


    @BeforeEach
    public void init() {

        UserPayloadRequest payload = new UserPayloadRequest();
        payload.setAmount(100.00);
        payload.setSender("kaleem@coforge.com");
        payload.setReceiver("abc@santander.com");
        service.saveTransaction(payload);

    }

    @Test
    public void getTransactionBySenderId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/{senderId}", "kaleem@coforge.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    @Order(2)
    public void getAllTransactions() throws Exception {

        UserPayloadRequest payload = new UserPayloadRequest();
        payload.setAmount(200);
        payload.setSender("xee@coforge.com");
        payload.setReceiver("abc@santander.com");
        service.saveTransaction(payload);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    @Order(1)
    public void transferMoney() throws Exception {

        UserPayloadRequest payload = new UserPayloadRequest();
        payload.setAmount(300.00);
        payload.setSender("xyz@coforge.com");
        payload.setReceiver("abc@santander.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transaction")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());


    }

    @Test
    public void transferMoney_invalidPayload() throws Exception {

        UserPayloadRequest payload = new UserPayloadRequest();
        payload.setAmount(0);
        payload.setSender("xyz@");
        payload.setReceiver("abc@");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transaction")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.amount", is("must be greater than 0")))
                .andExpect(jsonPath("$.sender", is("must be a well-formed email address")))
                .andExpect(jsonPath("$.receiver", is("must be a well-formed email address")));


    }

    @AfterEach
    public void cleanUp() {

        repository.deleteAll();

    }

}
