package com.wellsfargo.expensetracker.transactionreader.controller;

import com.wellsfargo.expensetracker.transactionreader.service.FileStatusDaoService;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionDaoService;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReplayController.class)
@TestPropertySource("classpath:application.properties ")
class ReplayControllerTest {

    @Value("${folder.processed}")
    private String processedFolderPath;
    @Value("${folder.error}")
    private String errorFolderPath;

    @MockBean
    private TransactionDaoService transactionDaoService;
    @MockBean
    private FileStatusDaoService fileStatusDaoService;
    @MockBean
    private TransactionProcessor transactionProcessor;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(transactionProcessor, "processedFolderPath", processedFolderPath);
        ReflectionTestUtils.setField(transactionProcessor, "errorFolderPath", errorFolderPath);
    }

    @Test
    void testGetReplay() throws Exception {
        mockMvc.perform(get("/transaction-reader/C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test.json"))
                .andExpect(status().isOk());
        verify(transactionProcessor, times(1)).processFile(any());
    }

    @Test
    void testGetReplayException() throws Exception {
        doThrow(IOException.class)
                .when(transactionProcessor)
                .processFile(any());
        mockMvc.perform(get("/transaction-reader/C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test_not_exits.json"))
                .andExpect(status().isOk());
        verify(transactionProcessor, times(1)).processFile(any());
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