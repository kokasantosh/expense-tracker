package com.example.expensetracker.transactionreader.listener;

import com.example.expensetracker.transactionreader.service.TransactionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FileRequestListenerTest {

    @Mock
    TransactionProcessor transactionProcessor;

    @InjectMocks
    FileRequestListener fileRequestListener;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOnChange() throws IOException {
        ChangedFile file = new ChangedFile(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles"), new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test.json"), ChangedFile.Type.ADD);
        Set<ChangedFiles> changedFilesSet = new HashSet<>();
        Set<ChangedFile> changedFileSet = new HashSet<>();
        changedFileSet.add(file);
        ChangedFiles changedFiles = new ChangedFiles(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles"), changedFileSet);
        changedFilesSet.add(changedFiles);

        fileRequestListener.onChange(changedFilesSet);

        verify(transactionProcessor, times(1)).processChangedFile(any());
    }

    @Test
    void testOnChangeException() throws IOException {
        ChangedFile file = new ChangedFile(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles"), new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test.json"), ChangedFile.Type.ADD);
        Set<ChangedFiles> changedFilesSet = new HashSet<>();
        Set<ChangedFile> changedFileSet = new HashSet<>();
        changedFileSet.add(file);
        ChangedFiles changedFiles = new ChangedFiles(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles"), changedFileSet);
        changedFilesSet.add(changedFiles);

        doThrow(IOException.class)
                .when(transactionProcessor)
                .processChangedFile(any());

        fileRequestListener.onChange(changedFilesSet);

        verify(transactionProcessor, times(1)).processChangedFile(any());
    }
}