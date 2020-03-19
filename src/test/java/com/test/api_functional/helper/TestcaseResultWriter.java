package com.test.api_functional.helper;

import org.testng.ITestResult;

import com.test.api_functional.BaseFunctionalTest;

public class TestcaseResultWriter extends BaseFunctionalTest{
	
	//private static String filePath;
	private ExcelHelper helper;
	
	public void writeResult(ITestResult result, String status){
		String testcaseSheetName = "TESTCASE";
		helper = new ExcelHelper(getFilePath());
		int rowCount = helper.getRowCount(testcaseSheetName);
		int rowNum;
		Object[] param = result.getParameters();
		String testcaseNumber = (((String) param[0]).trim().split(":"))[0].trim();
		for(rowNum = 2 ; rowNum <= rowCount ; rowNum ++){
			String tempTestcaseNumber = helper.getCellData(testcaseSheetName, rowNum, "#").trim();
			if(tempTestcaseNumber.equals(testcaseNumber) && !(tempTestcaseNumber.equals(""))){
				helper.writeCellValue(testcaseSheetName, rowNum, "Status", status);
				break;
			}
		}
		if(status.equalsIgnoreCase("Pass")){
			helper.setFont(testcaseSheetName, rowNum, "Status", "green", true, false);
			helper.setCellColour(testcaseSheetName, rowNum, "Status", "green");
		}else if(status.equalsIgnoreCase("fail")){
			
			helper.setFont(testcaseSheetName, rowNum, "Status", "red", true, false);
			helper.setCellColour(testcaseSheetName, rowNum, "Status", "red");
		}else if(status.equalsIgnoreCase("skip")){
			
			helper.setFont(testcaseSheetName, rowNum, "Status", "yellow", true, false);
			helper.setCellColour(testcaseSheetName, rowNum, "Status", "yellow");
		}
	}

}
