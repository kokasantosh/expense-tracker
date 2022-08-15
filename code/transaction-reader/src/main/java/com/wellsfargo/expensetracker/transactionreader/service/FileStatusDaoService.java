package com.wellsfargo.expensetracker.transactionreader.service;

import com.wellsfargo.expensetracker.transactionreader.repository.IFileStatusRepository;
import com.wellsfargo.expensetracker.transactionreader.domain.TransactionState;
import com.wellsfargo.expensetracker.transactionreader.domain.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileStatusDaoService {

    @Autowired
    private IFileStatusRepository fileStatusRepository;

    /**
     * Save the file status to reposistory after setting the transaction state
     * @param fileStatus
     * @param fileStatusEnum
     */
    public void saveFileStatus(FileStatus fileStatus, TransactionState fileStatusEnum) {
        fileStatus.setState(fileStatusEnum.getValue());
        fileStatusRepository.save(fileStatus);
    }

    /**
     * Gets the error files from DB
     * @return
     */
    public List<FileStatus> getErrorFiles() {
        return fileStatusRepository.findFileStatusByState(TransactionState.ERROR.getValue());
    }
}
