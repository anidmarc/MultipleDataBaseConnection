package MultipleDBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DBConnection {
	
	public void ConnectDB() throws ClassNotFoundException, SQLException, Exception {

		XSSFSheet spreadsheet = null; 
		CSVPrinter csvPrinter = null; 

		// The below line will read the data from the list mentioned in the .xlsx file
		FileInputStream fis = new FileInputStream("D:\\Aniruddha Workspace\\MDB\\src\\test\\java\\MultipleDBConnection\\orgall.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet Sheet1 = wb.getSheetAt(0);
		int rowcount = Sheet1.getPhysicalNumberOfRows();
		int colcount = Sheet1.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Total Number of Rows is ::" + rowcount);
		System.out.println("Total number of Col is ::" + colcount);
		
		// The below line will write the data in the .csv file
		FileOutputStream outCSV = new FileOutputStream(new File("D:\\Aniruddha Workspace\\MDB\\src\\test\\java\\MultipleDBConnection\\OutputData" + ".csv"));
        csvPrinter = new CSVPrinter(new OutputStreamWriter(outCSV), CSVFormat.DEFAULT);                

		XSSFWorkbook workbook = new XSSFWorkbook();
		spreadsheet = workbook.createSheet("sheet");
		int i = 0;
		for (int k = 0; k < rowcount; k++) {
			for (int j = 0; j < colcount; j++) {
				String testdata1 = Sheet1.getRow(k).getCell(j).getStringCellValue();
				System.out.println("Test data from excel cell  :" + testdata1);
				wb.close();
				Class.forName("org.postgresql.Driver");
				System.out.println("Driver Loaded");
				
				// The below line will connect the db one at a time, and it will read the db name from the .xlsx file
				Connection con = DriverManager.getConnection("jdbc:postgresql://" + "" + testdata1 + "" + ":5432/smdb",
						"successmaker", "Smpwd2112");
				Statement smt = con.createStatement();
				ResultSet rs = smt
						.executeQuery("select distinct rumba_id from school.group where rumba_id is not null");
				while (rs.next()) {
					XSSFRow row = spreadsheet.createRow(i);
					XSSFCell cell = row.createCell(0);
					cell.setCellValue(rs.getString("rumba_id"));
                    csvPrinter.print(cell.getStringCellValue());
                    csvPrinter.println(); // Newline after each row
					i++;
				}
				con.close();
			}
		}
		try {
            if (csvPrinter != null) {
                csvPrinter.flush();
                csvPrinter.close();
            }
        }
        catch (IOException ioe) {
        	System.out.println("Error when closing CSV Printer");
        }  
		System.out.println("All the classes are fetched successfully from the list of organization");

	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
		new DBConnection().ConnectDB();
	}

}
