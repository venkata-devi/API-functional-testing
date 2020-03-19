package com.test.api_functional.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.test.api_functional.BaseFunctionalTest;

public class TestScriptReader extends BaseFunctionalTest  {

	public static Map<String, ArrayList<Map<String, Object>>> reader() {

		Map<String, ArrayList<Map<String, Object>>> testScriptBigMap = new HashMap<>();

		for (String testcaseName : getTestcaseList()) {
			String testcase = (testcaseName.trim().split(":"))[0].toLowerCase().trim();
			String testScriptSheetName = "TEST SCRIPT";
			ExcelHelper helper = new ExcelHelper(getFilePath());
			ArrayList<Map<String, Object>> testScriptList = new ArrayList<>();
			int rowCount = helper.getRowCount(testScriptSheetName);
			for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
				Map<String, Object> testSriptMap = new HashMap<>();
				String tempTestcaseNumber = (helper.getCellData(testScriptSheetName, rowNum, "#")).toLowerCase().trim();
				if (tempTestcaseNumber.equalsIgnoreCase(testcase)) {
					int columnCount = helper.getColumnCount(testScriptSheetName);
					for (int colNum = 2; colNum <= columnCount; colNum++) {
						String key = (String) helper.getCellData(testScriptSheetName, 1, colNum);
						Object value = helper.getCellData(testScriptSheetName, rowNum, colNum);
						testSriptMap.put(key, value);
					}
					testScriptList.add(testSriptMap);
				}
			}
			testScriptBigMap.put(testcaseName, testScriptList);
		}
		return testScriptBigMap;
	}

}
