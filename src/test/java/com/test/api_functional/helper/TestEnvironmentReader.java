package com.test.api_functional.helper;

import java.util.HashMap;
import java.util.Map;

import com.test.api_functional.BaseFunctionalTest;

public class TestEnvironmentReader extends BaseFunctionalTest {

	public static Map<String, String> reader(){
		
		Map<String, String> environmentConfigurationMap = new HashMap<String, String>();
		
		String sheetName = "Environment";
		
		ExcelHelper ExcelHelper = new ExcelHelper(getFilePath());
		int rowCount = ExcelHelper.getRowCount(sheetName);
				
		int rowNum;
		
		for(rowNum = 2 ; rowNum <= rowCount ; rowNum ++){
			environmentConfigurationMap.put(((String) ExcelHelper.getCellData(sheetName, rowNum, 1)).toUpperCase(),(String)ExcelHelper.getCellData(sheetName, rowNum, 2));
		}
		
		return environmentConfigurationMap;
		
	}

}
