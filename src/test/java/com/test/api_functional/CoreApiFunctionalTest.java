package com.test.api_functional;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.api_functional.helper.TestcaseResultWriter;

public class CoreApiFunctionalTest extends BaseFunctionalTest {

	@BeforeSuite
	private void suite_setup() {

		String path = new File(".//src/test/resources/Testcases.xlsx").getAbsolutePath();

		setFilePath(path);
		readAllSheet();
		initializeReports();

	}

	@BeforeMethod
	public void test_setup() {
		initializeVariableMap();
	}

	@DataProvider(name = "Test Scripts")
	private Object[][] test_steps() {

		Object[][] array = dataProviderFunction();
		return array;

	}

	@Test(dataProvider = "Test Scripts")
	private void test(String testcase, ArrayList<Map<String, Object>> testSteps) {
		setTest(getReport().startTest(testcase));
		logging("Starting test case " + testcase, "info");
		for (Map<String, Object> testStep : testSteps) {
			logging("Executing Step: " + testStep.get("Test step"), "info");
			executeRequest(testStep);
			logging("Responce Body " + getResponse().getBody().asString(), "info");
			Assert.assertEquals((long) getResponse().statusCode(), (long) testStep.get("Status"));
			Assert.assertEquals(extractValuesFromResponse(), "Success");
			logging("Completed execution step: " + testStep.get("Test step"), "info");
			// logging("Responce Body " + getResponse().getBody().asString(),"info");

		}
	}

	@AfterMethod
	private void tearDown(ITestResult result) {
		// TestcaseResultWriter writer = new TestcaseResultWriter();
		if (result.getStatus() == ITestResult.SUCCESS) {
			logging("Test case pass", "pass");
			// writer.writeResult(result, "Pass");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logging("Test case fail. Failed due to : " + result.getThrowable().getLocalizedMessage(), "fail");
			// writer.writeResult(result, "Fail");
		} else if (result.getStatus() == ITestResult.SKIP) {
			logging("Test case skipped", "skip");
			// writer.writeResult(result, "Skip");
		}
		getReport().endTest(getTest());
	}

	@AfterSuite
	private void suitetearDown() {
		getReport().flush();
		getReport().close();
	}
}
