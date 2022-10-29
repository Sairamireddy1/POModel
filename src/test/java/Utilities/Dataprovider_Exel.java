package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider_Exel {
	
	@DataProvider(name="testdata")
	public Object[][] data_provider() throws IOException
	{
		String filepath=".\\TestData\\sai.xlsx";
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		wb.close();
	
	
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rowcount][1];
		
		for(int i=0; i<rowcount; i++)
		{
		Map<Object,Object> datamap = new HashMap<Object,Object>();
			for(int j=0; j<colcount; j++)
			{
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			obj[i][0] = datamap;
		}
		return obj;
	}

}
