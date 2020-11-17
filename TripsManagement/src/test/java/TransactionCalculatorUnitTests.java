import com.task.calculator.TransactionCalculator;
import com.task.model.Tap;
import com.task.model.Trip;
import com.task.model.modelEnums.StatusType;
import com.task.model.modelEnums.StopType;
import com.task.model.modelEnums.TapType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the output of an Incomplete Trip.
 */

public class TransactionCalculatorUnitTests {
    private static final String S1_S2_COST = "3.25";
    private static final String S1_S3_COST = "7.30";
    private static final String S2_S3_COST = "5.50";
    private static final String CANCELED_TRIP_COST = "0.00";

    @Test
    void testIncompleteTrip(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime TapOnTime = LocalDateTime.parse("22-01-2018 13:00:01", formatter);
        Tap newTap = Tap.builder().Id("3").tapTime(TapOnTime).tapType(TapType.ON).stop(StopType.Stop1).companyId("Company2").busId("Bus38").pan("5454545454545454").build();
        Queue<Tap> taplist = new LinkedList<>();
        taplist.add(newTap);
        TransactionCalculator transactionCalculator = TransactionCalculator.builder().allTaps(taplist).build();

        List<Trip> expectedTrip = new ArrayList<>();
        expectedTrip.add(Trip.builder().started(TapOnTime).finished(null).duration(null)
                .chargeAmount(7.3)
                .Fromstop(StopType.Stop1)
                .Tostop(null)
                .companyId("Company2")
                .busId("Bus38")
                .pan("5454545454545454")
                .status(StatusType.INCOMPLETE)
                .build());
        assertEquals(expectedTrip, transactionCalculator.generateTripsFromTaps());
        System.out.println("Test Completed Successfully");
    }

}
