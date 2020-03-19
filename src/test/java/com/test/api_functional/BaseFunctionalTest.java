package com.test.api_functional;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.testng.Reporter;

import com.github.javafaker.Faker;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.api_functional.helper.CalendarHelper;
import com.test.api_functional.helper.FilePropertyHelper;
import com.test.api_functional.helper.TestEnvironmentReader;
import com.test.api_functional.helper.TestScriptReader;
import com.test.api_functional.helper.TestcaseReader;

import io.restassured.response.Response;

public class BaseFunctionalTest {

	private static ExtentReports report;
	private static ExtentTest test;
	private static URL url;
	private static String URI;
	private static String baseURI;
	private static String basePath;
	private static String body;
	private static Map<String, Object> headerMap = new HashMap<String, Object>();
	private static Map<String, Object> queryParamMap = new HashMap<String, Object>();
	private static Logger log = Logger.getLogger(BaseFunctionalTest.class.getName());
	private static Response response;
	private String userName;
	private String password;
	private static String filePath;
	private static Map<String, String> environmentConfigurationMap;
	private static ArrayList<String> testcaseList;
	private static Map<String, ArrayList<Map<String, Object>>> testScriptMap;
	private static ArrayList<String> extractList;
	private static Map<String, Object> variableMap;

	public static Response getResponse() {
		return response;
	}

	public static Map<String, String> getEnvironmentConfigurationMap() {
		return environmentConfigurationMap;
	}

	public static void setEnvironmentConfigurationMap() {
		environmentConfigurationMap = TestEnvironmentReader.reader();
	}

	public static ArrayList<String> getTestcaseList() {
		return testcaseList;
	}

	public static void setTestcaseList() {
		testcaseList = TestcaseReader.runSelect();
	}

	public static Map<String, ArrayList<Map<String, Object>>> getTestScriptMap() {
		return testScriptMap;
	}

	public static void setTestScriptMap() {
		testScriptMap = TestScriptReader.reader();
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {

		BaseFunctionalTest.filePath = filePath;
	}

	public static ExtentReports getReport() {
		return report;
	}

	public static ExtentTest getTest() {
		return test;
	}

	protected static void setTest(ExtentTest test) {
		BaseFunctionalTest.test = test;
	}

	public void initializeReports() {
		String[] splitPath = getFilePath().split("/");
		String Filename = splitPath[splitPath.length - 1].replace(".xlsx", "");
		String reportPath = FilePropertyHelper.getProperty("application", "Report").get() + Filename + "/"
				+ CalendarHelper.getLocalDate() + "/" + CalendarHelper.getLocalTime() + "Report.html";
		report = new ExtentReports(reportPath);

	}

	public void readAllSheet() {
		setEnvironmentConfigurationMap();
		setTestcaseList();
		setTestScriptMap();
	}

	private void uriCreator() {

		try {

			url = new URL(URI);
			baseURI = url.getProtocol() + "://" + url.getHost();
			basePath = url.getPath().replaceAll("//", "/");
			String[] queries = url.getQuery().split("&");
			queryParamMap.clear();
			for (String query : queries) {
				String[] split = query.split("=");
				queryParamMap.put(split[0], split[1]);
			}

		} catch (NullPointerException e) {

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
	}

	public void get() {
		response = null;
		uriCreator();
		logging("Hitting GET request to " + URI, "info");
		if ((userName != null && password != null) && (!userName.isEmpty()) && (!password.isEmpty())) {

			// Debugging code
			// System.out.println("URL: " + baseURI);

			response = given().baseUri(baseURI).auth().preemptive().basic(userName, password).headers(headerMap)
					.queryParams(queryParamMap).when().get(basePath);

		} else {
			response = given().baseUri(baseURI).headers(headerMap).queryParams(queryParamMap).when().get(basePath);
		}
	}

	public void delete() {
		response = null;
		uriCreator();
		logging("Hitting GET request to " + URI, "info");
		if ((userName != null && password != null) && (!userName.isEmpty()) && (!password.isEmpty())) {

			// Debugging code
			// System.out.println("URL: " + baseURI);

			response = given().baseUri(baseURI).auth().preemptive().basic(userName, password).headers(headerMap)
					.queryParams(queryParamMap).when().delete(basePath);

		} else {
			response = given().baseUri(baseURI).headers(headerMap).queryParams(queryParamMap).when().delete(basePath);
		}
	}

	public void post() {
		response = null;
		uriCreator();
		try {
			logging("Hitting POST request to " + URI, "info");

			if ((userName != null && password != null) && (!userName.isEmpty()) && (!password.isEmpty())) {

				// Debugging code
				/*
				 * System.out.println("URL: " + baseURI); System.out.println("BODY: "+ body);
				 */
				logging("REQUEST BODY " + body, "info");

				response = given().baseUri(baseURI).auth().preemptive().basic(userName, password).headers(headerMap)
						.body(body).post(basePath);

			} else {
				// body = "{'first_name': 'Keenan','last_name': 'Loma Streich','gender':
				// 'female','email': '18-03-2020.14-21@mail.com','status': 'active'}";
				logging("REQUEST BODY " + body, "info");

//				Map<String, Object> headerMap1 = new HashMap<String, Object>();
//				headerMap1.put("Authorization", "Bearer Dr5ULJz1aNz0N3ZwhRY043Q0gRJtl16PC0AW");
//				headerMap1.put("Content-Type", "application/json");

				response = given().baseUri(baseURI).headers(headerMap).body(body).post(basePath);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void patch() {
		response = null;
		uriCreator();
		try {
			logging("Hitting PATCH request to " + URI, "info");

			if ((userName != null && password != null) && (!userName.isEmpty()) && (!password.isEmpty())) {

				// Debugging code
				/*
				 * System.out.println("URL: " + baseURI); System.out.println("BODY: "+ body);
				 */
				logging("REQUEST BODY " + body, "info");
				response = given().baseUri(baseURI).auth().preemptive().basic(userName, password).headers(headerMap)
						.body(body).patch(basePath);

			} else {
				response = given().baseUri(baseURI).headers(headerMap).body(body).patch(basePath);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void logging(String message, String logType) {
		try {
			Reporter.log(message);

			if (logType.equalsIgnoreCase("info")) {
				log.info(message);
				getTest().log(LogStatus.INFO, message);
			} else if (logType.equalsIgnoreCase("fail")) {
				log.info(message);
				getTest().log(LogStatus.FAIL, message);
			} else if (logType.equalsIgnoreCase("pass")) {
				log.info(message);
				getTest().log(LogStatus.PASS, message);
			} else if (logType.equalsIgnoreCase("skip")) {
				log.info(message);
				getTest().log(LogStatus.SKIP, message);
			}
		} catch (Exception e) {

		}
	}

	public Object[][] dataProviderFunction() {
		Object[][] array = new Object[getTestScriptMap().size()][2];
		int count = 0;
		for (Entry<String, ArrayList<Map<String, Object>>> entry : getTestScriptMap().entrySet()) {
			array[count][0] = entry.getKey();
			array[count][1] = entry.getValue();
			count++;
		}
		return array;
	}

	public void initializeVariableMap() {
		variableMap = new HashMap<>();
	}

	public void executeRequest(Map<String, Object> testStep) {
		URI = (String) (environmentConfigurationMap.get("BASEURI") + testStep.get("URI"));
		body = (String) testStep.get("JSON");
		updateRequest(testStep);
		userName = (String) environmentConfigurationMap.get("User name".toUpperCase());
		password = (String) environmentConfigurationMap.get("PASSWORD".toUpperCase());
		setHeaders(testStep);
		setExtractList(testStep);
		hitRequest((String) testStep.get("Request"));
	}

	private void updateRequest(Map<String, Object> testStep) {
		String randomEmail = CalendarHelper.getLocalDate() + "." + CalendarHelper.getLocalTime() + "@mail.com";
		if (!variableMap.isEmpty()) {
			for (Entry<String, Object> entry : variableMap.entrySet()) {
				String variable = "($" + entry.getKey() + ")";
				if (URI.contains(variable)) {
					URI = URI.replace(variable, (String) entry.getValue());
				}
				if (((String) testStep.get("JSON")).contains(variable)) {
					if (entry.getValue().getClass().getName().contains("String"))
						body = (body).replace(variable, "\"" + (String) entry.getValue() + "\"");
					else
						body = (body).replace(variable, (String) entry.getValue());
				}
			}
		}
		if (URI.contains("($random_email)")) {
			URI = URI.replace("($random_email)", randomEmail);
		}
		if (((String) testStep.get("JSON")).contains("($random_email)")) {
			body = (body).replace("($random_email)", "\"" + randomEmail + "\"");
		}
		Faker faker = new Faker();
		String randomName = faker.name().firstName();

		if (URI.contains("($random_name)")) {
			URI = URI.replace("($random_name)", randomName);
		}
		if (((String) testStep.get("JSON")).contains("($random_name)")) {
			body = (body).replace("($random_name)", "\"" + randomName + "\"");
		}
		String randomString = faker.name().fullName();
		if (URI.contains("($random_string)")) {
			URI = URI.replace("($random_string)", randomString);
		}
		if (((String) testStep.get("JSON")).contains("($random_string)")) {
			body = (body).replace("($random_string)", "\"" + randomString + "\"");
		}
	}

	private void setHeaders(Map<String, Object> testStep) {
		String headers = (String) testStep.get("Headers");
		if (headers != "") {
			if (headers.contains("\n")) {
				String[] headerSplit = headers.split("\n");
				for (String header : headerSplit) {
					String[] headerKeyValueSplit = header.split("=");
					headerMap.put(headerKeyValueSplit[0].trim(), headerKeyValueSplit[1].trim());
				}
			} else {
				String[] headerKeyValueSplit = headers.split("=");
				headerMap.put(headerKeyValueSplit[0].trim(), headerKeyValueSplit[1].trim());
			}
		}
	}

	private void setExtractList(Map<String, Object> testStep) {
		String extracts = (String) testStep.get("Extract");
		extractList = new ArrayList<>();
		if (extracts.contains("\n")) {
			String[] extractSplit = extracts.split("\n");
			for (String extract : extractSplit) {
				extractList.add(extract);
			}
		} else if (!extracts.isEmpty()) {
			extractList.add(extracts);
		}
	}

	private void hitRequest(String request) {
		if (request.equalsIgnoreCase("get")) {
			get();
		} else if (request.equalsIgnoreCase("post")) {
			post();
		} else if (request.equalsIgnoreCase("patch")) {
			patch();
		} else if (request.equalsIgnoreCase("delete")) {
			delete();
		}
	}

	public String extractValuesFromResponse() {
		if (!extractList.isEmpty()) {
			for (String extract : extractList) {
				String key = extract;
				if (key != null) {
					try {
						Object value = response.path(key);

						// Debugging code
						// System.out.println(key + " : " + value);
						logging(("Extract Key= " + key + " : " + value), "info");
						if (value != null) {
							variableMap.put(key, value);
						} else {
							return "Fail";
						}
					} catch (Exception e) {
						return e.getLocalizedMessage();
					}
				}
			}
		}

		return "Success";
	}

}
