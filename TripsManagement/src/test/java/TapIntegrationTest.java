import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
Comparing with generated Trips
 */

public class TapIntegrationTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/trips.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromClasspath(String Started,String Finished,String DurationSecs,String FromStopId, String ToStopId, String ChargeAmount, String CompanyId, String BusID, String PAN, String Status) {
        assertEquals("2018-01-22T13:00",Started);
        assertEquals("2018-01-22T13:05",Finished);
        assertEquals("300",DurationSecs);
        assertEquals("Stop1",FromStopId);
        assertEquals("Stop2",ToStopId);
        assertEquals("$3.25",ChargeAmount);
        assertEquals("Company1",CompanyId);
        assertEquals("Bus37",BusID);
        assertEquals("5500005555555559",PAN);
        assertEquals("COMPLETED",Status);
        System.out.println("Test Completed Successfully");
    }

}
