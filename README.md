# LittlePay Coding Challenge

Program Execution
A jar file named “TripsManagement-1.0-SNAPSHOT.jar” is available in the target folder of the code uploaded on github. A sample input file has been created in the resources folder for the purpose of execution and tests. The following command is an example:
      java -jar TripsManagement-1.0-SNAPSHOT.jar <absolute-path-of-taps.csv> <absolute-path-of-trips.csv> 
 
Assumptions Made
  - The input csv (taps.csv) is well-formed and the right path provided 
  - If a tap off is not registered, therefore, the stop station is assigned as well as the duration.
  - All data not captured or that could not be calculated can be represented as empty in the csv output file (trips.csv)

Test Performed
  There is a test folder whereby tests have been made on the csv file as well as in the form of unit tests:
   - TapIntegrationTest
   - TransactionCalculatorUnitTests

Possible enhancements
  - Better test coverage (wider set of data)
  - Use of a GUI for the input of the csv file to avoid input error.

