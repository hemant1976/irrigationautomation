# irrigationautomation
Project for Irrigation Automation

SETTING UP APPLICATION

1.Create a database "irrigationdb" in MySQL database.
2.Download code from github link
3.Extract folders on your drive and open both folders in STS
4.Comment/Disable @EnableScheduling from IrrigationAutomationApplication.java 
if you do not want to test integration with sensor device api and only want to test rest api calls from Postman
5.Use PayLoad.txt to create some records from postman using POST url
6.To test retry logic ,"SensorDeviceInterface" application should be in stopped state and @EnableScheduling should be uncommented to execute business logic.
7.To test integration both applications should be in running state and @EnableScheduling be uncommented.
8.Refer attached word document to know more about testing retry logic and fetching records from database
based on startTime and endTime and triggering sensor device api calls when startTime and endTime is crossed.



