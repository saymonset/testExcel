package ve.org.bcv.reportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class TestStackOver {
	
	 public static void writeXLSXFile(int row, int col) throws IOException {
		  URL url = ExcelEstadistica.class.getResource("ejemplo.xlsx");
	        try {
	            FileInputStream file = new FileInputStream(url.getPath());
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);
	            Cell cell = null;
	          //Retrieve the row and check for null
	            XSSFRow sheetrow = sheet.getRow(row);
	            if(sheetrow == null){
	                sheetrow = sheet.createRow(row);
	            }
	            //Update the value of cell
	            cell = sheetrow.getCell(col);
	            if(cell == null){
	                cell = sheetrow.createCell(col);
	            }
	            cell.setCellValue("Pass");

	            file.close();


	            FileOutputStream outFile =new FileOutputStream(new File("/tmp/simon.xlsx"));
	            workbook.write(outFile);
	            outFile.close();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        // TODO Auto-generated method stub
	        System.out.println("inicio");
	        writeXLSXFile(1, 14);
	        System.out.println("terminado");
	    }
    	
    	 
}
