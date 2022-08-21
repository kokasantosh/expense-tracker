package com.wellsfargo.expensetracker.transactionreader.controller;

import com.wellsfargo.expensetracker.transactionreader.service.FileStatusDaoService;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionDaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReplayController.class)
@TestPropertySource("classpath:application.properties ")
class ReplayControllerTest {

    @MockBean
    private TransactionDaoService transactionDaoService;
    @MockBean
    private FileStatusDaoService fileStatusDaoService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetErrorFiles() throws Exception {
        mockMvc.perform(get("/transaction-reader/getErrorFiles"))
                .andExpect(status().isOk());
        verify(fileStatusDaoService, times(1)).getErrorFiles();
    }

    @Test
    void testGetErrorTransactions() throws Exception {
        mockMvc.perform(get("/transaction-reader/getErrorTransaction"))
                .andExpect(status().isOk());
        verify(transactionDaoService, times(1)).getErrorTransactions();
    }
}