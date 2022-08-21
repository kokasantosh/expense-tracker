package com.wellsfargo.expensetracker.transactionreader.service;

import com.wellsfargo.expensetracker.transactionreader.repository.IFileStatusRepository;
import com.wellsfargo.expensetracker.transactionreader.domain.TransactionState;
import com.wellsfargo.expensetracker.transactionreader.domain.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<FileStatus> fileStatusOptional = fileStatusRepository.findById(fileStatus.getFileName());
        FileStatus fileStatusToSave = fileStatusOptional.isPresent()?fileStatusOptional.get():fileStatus;
        fileStatusToSave.setState(fileStatusEnum.getValue());
        fileStatusRepository.save(fileStatusToSave);
    }

    /**
     * Gets the error files from DB
     * @return
     */
    public List<FileStatus> getErrorFiles() {
        return fileStatusRepository.findFileStatusByState(TransactionState.ERROR.getValue());
    }
}
