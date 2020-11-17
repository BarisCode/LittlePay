package com.task.reader;

import com.task.model.Tap;
import com.task.model.Trip;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of this interface in ReadWriteImpl.
 */
public interface ReadWriteCSV {
    // reading the csv file and creating a queue of Tap
    Queue<Tap> readCsv(String csvFileLocation) throws IOException;

   void writeToCsv(List<Trip> tripList, String path) throws IOException;
}
