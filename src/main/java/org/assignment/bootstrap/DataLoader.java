package org.assignment.bootstrap;

import org.assignment.exception.FileTypeMismatch;
import org.assignment.util.CSVUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.locks.ReentrantLock;


public class DataLoader implements Runnable {

    private static final String FILES_SOURCE_DIRECTORY = "src/res/";

    /**
     * load all the file in the source directory and pass them to read and store
     * uses CSVUtil.read method for parsing
     */
    void startLoading() {
        File file = new File(FILES_SOURCE_DIRECTORY);
        String[] pathsArray = file.list();
        for (String path : pathsArray) {
            if (!path.contains(".csv")){
                throw new FileTypeMismatch(path + " is not a csv file");
            }
            CSVUtil.read(FILES_SOURCE_DIRECTORY + path);
        }
    }

    /**
     * listen the same directory for addition of any new file for loading the content into the
     * local flight data records
     * @throws IOException
     * @throws InterruptedException
     */
    void loadNewEntry() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(FILES_SOURCE_DIRECTORY);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind:" + event.kind()
                        + ". File affected: " + event.context() + ".");
                if (!event.context().toString().contains(".csv")){
                    throw new FileTypeMismatch(path + " is not a csv file");
                }
                ReentrantLock lock = new ReentrantLock();
                if (lock.tryLock()) {
                    CSVUtil.read(FILES_SOURCE_DIRECTORY + event.context().toString());
                    lock.unlock();
                }
            }
            key.reset();
        }
    }

    /**
     * thread first load existing files
     * then listen for the any new files addition into the directory
     */
    @Override
    public void run() {
        try {
            startLoading();
            loadNewEntry();
        } catch (InterruptedException | IOException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
