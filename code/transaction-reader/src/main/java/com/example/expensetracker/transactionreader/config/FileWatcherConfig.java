package com.example.expensetracker.transactionreader.config;

import com.example.expensetracker.transactionreader.listener.FileRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.File;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {
    private static final Logger LOG = LoggerFactory.getLogger(FileWatcherConfig.class);

    @Value( "${folder.input}" )
    private String inputFolderPath;
    @Autowired
    FileRequestListener fileListener;

    /**
     * Initializes FileWatcher on specific folder for property folder.input
     * @return FileSystemWatcher
     */
    @Bean
    public FileSystemWatcher fileSystemWatcher(FileRequestListener fileListener) {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L), Duration.ofMillis(3000L));
        fileSystemWatcher.addSourceDirectory(new File(inputFolderPath));
        fileSystemWatcher.addListener(fileListener);
        fileSystemWatcher.start();
        LOG.info("started fileSystemWatcher");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() {
        fileSystemWatcher(fileListener).stop();
    }
}
