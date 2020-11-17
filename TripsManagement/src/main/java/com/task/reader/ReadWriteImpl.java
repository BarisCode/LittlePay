package com.task.reader;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.task.model.Tap;
import com.task.model.Trip;
import com.task.model.modelEnums.StopType;
import com.task.model.modelEnums.TapType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *  Reading and Writing (I/O) from CSV file.
 */

public class ReadWriteImpl implements ReadWriteCSV{
    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public Queue<Tap> readCsv(String csvFileLocation) throws IOException {
        Queue<Tap> taps = new LinkedList<>();
        File file = new File(csvFileLocation);
        InputStream instream = new FileInputStream(file);
        InputStreamReader inputreader = new InputStreamReader(instream);
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withTrim()
                .parse(inputreader);
        // reading each record according to the header
        for (CSVRecord record : csvParser) {
            taps.add(Tap.builder().Id(record.get("ID")).tapTime(LocalDateTime.parse(record.get("DateTimeUTC"), dateformatter))
                    .tapType(TapType.valueOf(record.get("TapType")))
                    .stop(StopType.valueOf(record.get("StopId")))
                    .companyId(record.get("CompanyId"))
                    .busId(record.get("BusID"))
                    .pan(record.get("PAN")).build());
        }
        return taps;
    }

    @Override
    public void writeToCsv(List<Trip> tripList, String path) throws IOException {
        BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(path));
        // creating CSV header
        CSVPrinter csvPrinter = new CSVPrinter(bufferedwriter, CSVFormat.DEFAULT.withHeader("Started", "Finished", "DurationSecs",
                "FromStopId", "ToStopId", "ChargeAmount", "CompanyId", "BusID", "PAN", "Status"));
        for (Trip trip : tripList) {
            csvPrinter.printRecord(trip.getStarted(),trip.getFinished(),trip.getDuration(),
                    trip.getFromstop(),trip.getTostop(),"$"+ trip.getChargeAmount(),trip.getCompanyId(),trip.getBusId(),
                    trip.getPan(),trip.getStatus());
        }
        csvPrinter.flush();
    }
}
