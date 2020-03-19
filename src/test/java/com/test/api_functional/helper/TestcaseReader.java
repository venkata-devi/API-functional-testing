package com.test.api_functional.helper;

import java.util.ArrayList;

import com.test.api_functional.BaseFunctionalTest;

public class TestcaseReader extends BaseFunctionalTest {

	private static ArrayList<String> testcaseList = new ArrayList<>();
	private static ExcelHelper ExcelHelper;
	private static String testcaseSheetName = "TESTCASE";
	
	public static  ArrayList<String> runSelect() {
		
		String run = getEnvironmentConfigurationMap().get("RUN");
		ExcelHelper = new ExcelHelper(getFilePath());
		if (run.equalsIgnoreCase("All")) {
			allRun();
		} else if (run.equalsIgnoreCase("Selected")) {
			selectRun();
		} else if (run.equalsIgnoreCase("Pass")) {
			statusRun(run);
		} else if (run.equalsIgnoreCase("Fail")) {
			statusRun(run);
		} else if (run.equalsIgnoreCase("Skip")) {
			statusRun(run);
		} else {
			System.out.println("Invalid option");
			System.exit(0);
		}
		
		return testcaseList;

	}

	private static boolean includeGroup(int rowNum) {
		int flag = 0;
		String includeAsString = getEnvironmentConfigurationMap().get("INCLUDE");
		if (!includeAsString.isEmpty()) {
			String[] includeAsArray = includeAsString.split(",");
			for (String groupName : includeAsArray) {
				if (ExcelHelper.getCellData(testcaseSheetName, rowNum, "group").toUpperCase()
						.contains(groupName.trim().toUpperCase())) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	private static boolean excludeGroup(int rowNum) {
		int flag = 0;
		String excludeAsString =getEnvironmentConfigurationMap().get("EXCLUDE");
		if (!excludeAsString.isEmpty()) {
			String[] excludeAsArray = excludeAsString.split(",");
			for (String groupName : excludeAsArray) {
				if (ExcelHelper.getCellData(testcaseSheetName, rowNum, "group").toUpperCase()
						.contains(groupName.trim().toUpperCase())) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private static void allRun() {
		
		int rowCount = ExcelHelper.getRowCount(testcaseSheetName);
		testcaseList = new ArrayList<>();
		int counter = 0;
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String testcaseNumber = ExcelHelper.getCellData(testcaseSheetName, rowNum, "#");
			if (includeGroup(rowNum) && !excludeGroup(rowNum)) {
				testcaseList.add(testcaseNumber + " : " + ExcelHelper.getCellData(testcaseSheetName, rowNum, "Testcase Description"));
				counter++;
			}
		}
		if (counter == 0) {
			System.out.println("No test case found");
			System.exit(0);
		}
		System.out.println(
				"Running all test cases [" + getEnvironmentConfigurationMap().get("INCLUDE") + "]");
	}

	private static void selectRun() {
		int rowCount = ExcelHelper.getRowCount(testcaseSheetName);
		testcaseList = new ArrayList<>();
		int counter = 0;
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String testcaseNumber = ExcelHelper.getCellData(testcaseSheetName, rowNum, "#");
			String run = ExcelHelper.getCellData(testcaseSheetName, rowNum, "run");
			if (run.equalsIgnoreCase("Y")) {
				if (includeGroup(rowNum) && !excludeGroup(rowNum)) {
					testcaseList.add(testcaseNumber + " : " + ExcelHelper.getCellData(testcaseSheetName, rowNum, "Testcase Description"));
					counter++;
				}
			}
		}
		if (counter == 0) {
			System.out.println("No test case found");
			System.exit(0);
		}

		
		System.out.println("Running selected test cases ["
				+ getEnvironmentConfigurationMap().get("INCLUDE") + "]");
	}

	private static void statusRun(String status) {
		int rowCount = ExcelHelper.getRowCount(testcaseSheetName);
		testcaseList = new ArrayList<>();
		int counter = 0;
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String testcaseNumber = ExcelHelper.getCellData(testcaseSheetName, rowNum, "#");
			String run = ExcelHelper.getCellData(testcaseSheetName, rowNum, "Status");
			if (run.equalsIgnoreCase(status)) {
				if (includeGroup(rowNum) && !excludeGroup(rowNum)) {
					testcaseList.add(testcaseNumber + " : " + ExcelHelper.getCellData(testcaseSheetName, rowNum, "Testcase Description"));
					counter++;
				}
			}
		}
		if (counter == 0) {
			System.out.println("No test case found");
			System.exit(0);
		}
		
		System.out.println("Running " + status + " test cases ["
				+ getEnvironmentConfigurationMap().get("INCLUDE") + "]");
	}

}
