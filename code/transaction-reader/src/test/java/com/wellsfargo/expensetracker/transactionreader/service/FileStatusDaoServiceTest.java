package com.wellsfargo.expensetracker.transactionreader.service;

import com.wellsfargo.expensetracker.transactionreader.domain.FileStatus;
import com.wellsfargo.expensetracker.transactionreader.domain.TransactionState;
import com.wellsfargo.expensetracker.transactionreader.repository.IFileStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class FileStatusDaoServiceTest {

    @Mock
    IFileStatusRepository fileStatusRepository;

    @InjectMocks
    private FileStatusDaoService fileStatusDaoService;

    @Test
    void testSaveFileStatus() {
        fileStatusDaoService.saveFileStatus(new FileStatus(""), TransactionState.PROCESSED);
        verify(fileStatusRepository, times(1)).save(any());
    }

    @Test
    void testGetErrorFiles() {
        fileStatusDaoService.getErrorFiles();
        verify(fileStatusRepository, times(1)).findFileStatusByState(any());
    }
}