# API-functional-testing
## Installation
1. install Java 8(https://java.com/en/download/help/mac_install.xml)
2. Install maven (This can usfull for update all jars for this project) 
3. Add TestNg plugin or install testNG.
4. Rest assured.

## Explanation for this rest assured API testing project
Once we have this framework just need to write the test cases in Excel with below mentioned format.
# Excel:
# In first tab:
we need to mention environment, auth details like (username and password)
# second tab :
1.This will have high level scenarioes ex: Get_all_user, update_user
2.For example if we have 2 scenarioes in this tab it can only run that two scenarioes even though if we have 3 scenarioes in third tab
3.we can run selected scenarioes by using RUN coloumn for EX: In Run coloumn if we mention Y and In fisrt tab we have to mention "Selected" for Run it will run only that test cases which we have 'Y'. and If you wnat to run all the test cases you can mention All in the first tab.
4. In status coloumn it will automatically mention all pass, fail and skip for testcases.
# Third tab:
1. #        -- In this we have to mention scenraio number which we mention in second tab.
2.Test step -- test step name 
3.Request     -- have mention method type like POST,GET,PATCH,..
4.URI         -- have to give end point URL (ex: /api/v1/getusers). Base URI we are mentioning in first tab.
4.Headers     -- Here we have to pass all heades with the sapartion of enter (ALT+enter)
6.JSON        -- If the request type is post,patch or put we have to mention the request body here in the json format.
7.Status      -- This the expected http status code for that API for example (for get all users API we are expecting 200 we have to give 200) 
8.Extract			-- 1.Here we ahve Extract value from the response so that we can pass this value to another API (for exaple: for create user API we did mention "result.id" (this path for ID key from the responce) so that we are passing this value to Update user API (/public-api/users/($result.id)))	
2. If we want to extract multiple values from responce we can mention with the sapration of enter (ALT+enter) example: result.id 
_meta.code
