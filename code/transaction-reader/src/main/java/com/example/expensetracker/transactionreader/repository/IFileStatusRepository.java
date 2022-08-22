package com.example.expensetracker.transactionreader.repository;

import com.example.expensetracker.transactionreader.domain.FileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFileStatusRepository extends JpaRepository<FileStatus, String> {

    public List<FileStatus> findFileStatusByState(String state);
}
