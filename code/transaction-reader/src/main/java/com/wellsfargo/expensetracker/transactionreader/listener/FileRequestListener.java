package com.wellsfargo.expensetracker.transactionreader.listener;

import com.wellsfargo.expensetracker.transactionreader.service.TransactionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FileRequestListener implements FileChangeListener {
    private static final Logger LOG = LoggerFactory.getLogger(FileRequestListener.class);

    @Autowired
    TransactionProcessor transactionProcessor;

    /**
     * Processes all the files received from the input folder
     * @param changeSet
     */
    @Override
    public void onChange(Set<ChangedFiles> changeSet){
        for(ChangedFiles files : changeSet) {
            for(ChangedFile file: files.getFiles()) {
               try {
                   transactionProcessor.processChangedFile(file);
                   LOG.info("Processed the file successfully: {}", file.getFile().getName());
               } catch(Exception e) {
                   LOG.error("Error while processing the file, Continuing with other file processing", e);
               }
            }
        }
    }
}
