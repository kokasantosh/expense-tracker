package com.example.expensetracker.transactionreader.util;

import com.example.expensetracker.transactionreader.domain.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public final class FileToObjectMapper {
    private static final Logger LOG = LoggerFactory.getLogger(FileToObjectMapper.class);

    private FileToObjectMapper(){}
    /**
     * Converts the json file to list of transaction objects
     * @param file
     * @return
     * @throws Exception
     */
    public static Optional<List<Transaction>> getTransactionListFromString(File file) throws IOException{
        try {
            String str = FileUtils.readFileToString(new File(file.getAbsolutePath()), StandardCharsets.UTF_8);
            List<Transaction> transactionList = new ObjectMapper().readValue(str, new TypeReference<List<Transaction>>(){});
            return Optional.of(transactionList);
        } catch (JsonParseException e) {
            LOG.error("Issue while converting the file to Transaction object for the file : {}", file.getName());
            throw e;
        }
    }
}
