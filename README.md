# API-functional-testing
## Installation
1. install Java 8(https://java.com/en/download/help/mac_install.xml)
2. Install maven (This can usfull for update all jars for this project) 
3. Add TestNg plugin or install testNG.
4. Rest assured.

## How to run 
1. Once you download the project and import it as Maven project in your IDE. It will automatically download all the maven deps.

2. Run the java file "CoreApiFunctionalTest.java" as TestNg Test.

3. The framework will take care of running the tests mentioned in the Excel file "Testcases.xlsx" located in the resources folder of this project. 



# Format about Excel:

### First Sheet (Environment) :
We need to mention environment, auth details like (username and password)

### Second Sheet (TESTCASE) :
1.This will have high level scenarioes ex: Get_all_user, update_user (No spaces)

2.Whatever scnearios mentioned in this sheet only those will be ran. (Example :- TC01, TC02..etc)

3.We can control what test scenarios to run. 
For example, 
=> If we specify "Selected" in the First sheet under the column "Run" then it will only run the test scnearios which are marked as "y" in the second sheet. 

=> If we specify "All" in the First sheet under the column "Run" then it will run the test scnearios specified in the second sheet.

4.Once the tests were ran, The Status column will be updated with "Pass", "Fail" or "Skip"

### Third Sheet (TEST SCRIPT):
1. #-- In this we have to mention scenraio number which we mention in second sheet.

2. Test step   -- test step name 

3. Request     -- have mention method type like POST,GET,PATCH,..

4. URI         -- have to give end point URL (ex: /api/v1/getusers). Base URI we are mentioning in first tab.

4. Headers     -- Here we have to pass all heades with the sapartion of enter (ALT+enter)

6. JSON        -- If the request type is post,patch or put we have to mention the request body here in the json format.

7. Status      -- This the expected http status code for that API for example (for get all users API we are expecting 200 we have to give 200) 

8. Extract			-- 

=> If we wanted to extract a key from the response and pass to next api, this is the place you specify those things.(For example: for create user API, We did mention "result.id" (this is the path for ID key from the response) so that we are passing this value to Update user API (/public-api/users/($result.id)))	

=> If we want to extract multiple values from response we can mention with the separation of enter (ALT+enter) example: result.id 
_meta.code

## Reports:
In application.properties we have mentioned the path as "Report= ./Reports/". The test results report will be created in that location and reports will be saved with the time stamp as a reports name and based on the environement (Reports-> QA -> current time stamp report file)
