# SWEETY_TEST
-> Automation Code to test few basic functionalities of Sweety Application.

## Instructions:
a) **Test_Cases.xls:** Contains 5 testcases. Mark Y/y against TC_execution_Flag column to run that particular case. The fields contain test case name, login url, login user, password, workbook and sheet name. (Multiple cases can also be triggered by marking more than one cases for execution at once).

b) **Datasheet/Datasheet_v1.xls:** Contains the data entries to be utilized during runtime for the testcases. There are sheet names specified for the testcases which are registered in Test_Cases.xls to be utilized during execution.

c) **run.bat:** Trigger the file to execute the suite.

d) **Execution_main:** If executing through Eclipse, trigger execution on Execution_main and run as TestNG.

e) **Results:** The HTML reports generated after each execution are stored in this folder.
