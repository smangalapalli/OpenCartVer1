package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public String[][] getAllData() throws IOException {
		
		
		String path = System.getProperty("user.dir") + "//testData//loginTestData.xlsx"; //taking XL file file from testData folder
		ExcelUtility xl = new ExcelUtility(path); //Creating an  object for ExcelUtility
		
		int rownum = xl.getRowCount("Sheet1");//get total number of rows
		int colcount = xl.getCellCount("Sheet1", 1); // get total number of columns from row 1
		
		String logindata[][] = new String[rownum][colcount]; //creating 2-dimensional array
		for (int i=1; i<=rownum; i++) //read data from xl storing in two dimensional array
		{
			for (int j=0; j<colcount; j++) //i=rows, j=cols
			{
				logindata[i-1][j] = xl.getCellData("Sheet1", i, j); //1,0
			}
		}
		return logindata; //returning 2 dimensional array
	}
	
	//@DataProvider2
	
	//@DataProvider3
	
	//@DataProvider4
	
}
