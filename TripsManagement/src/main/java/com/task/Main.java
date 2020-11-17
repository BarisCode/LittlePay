package com.task;
import com.task.calculator.TransactionCalculator;
import com.task.model.Tap;
import com.task.model.Trip;
import com.task.reader.ReadWriteImpl;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

/*
 * Main point of execution of application.
 */

public class Main {
    public static void main(String[] args) {
        ReadWriteImpl csvReaderWriter = new ReadWriteImpl();
        try {
            Queue<Tap> tapList = csvReaderWriter.readCsv(args[0]);
            TransactionCalculator transactionCalculator = TransactionCalculator.builder().allTaps(tapList).build();
            List<Trip> trips = transactionCalculator.generateTripsFromTaps();
            csvReaderWriter.writeToCsv(trips, args[1]);
            System.out.println("Application executed Successfully! ");
        }catch(IllegalArgumentException e){
            System.out.println("Inconsistent argument in csv file: " + e.getMessage());
        }
        catch (IOException io) {
            System.out.println("Incorrect Input: " + io.getMessage());
        }
    }
}
