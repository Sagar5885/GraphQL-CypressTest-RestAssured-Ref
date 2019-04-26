package restassuredtest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class dataDriven {

    public List<String> getData(String testcase, String sheetname) throws IOException {
        List<String> a = new ArrayList<String>();

        FileInputStream fs = new FileInputStream("./src/main/resources/demodata.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fs);

        int sheets = wb.getNumberOfSheets();
        for(int i=0; i<sheets; i++) {
            //Sheet1
            if(wb.getSheetName(i).equalsIgnoreCase(sheetname)) {
                XSSFSheet sheet = wb.getSheetAt(i);
                //Identify test case column by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator(); //Collection of rows
                Row firstRow = rows.next();
                Iterator<Cell> cells = firstRow.cellIterator(); //Collection of cells

                int k=0;
                int column=0;
                while(cells.hasNext()) {
                    Cell value = cells.next();
                    if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        column = k;
                    }
                    k++;
                }
                //System.out.println(column);

                //once column is identify scan entire column to find respective data
                while(rows.hasNext()) {
                    Row r = rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcase)) {
                        //pull all the data of that row
                        Iterator<Cell> cellsres = r.cellIterator();
                        while(cellsres.hasNext()) {
                            //System.out.println(cellsres.next().getStringCellValue());
                            Cell c = cellsres.next();
                            if(c.getCellTypeEnum() == CellType.STRING) {
                                a.add(c.getStringCellValue());
                            }else {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }
                }
            }
        }

        return a;
    }
}
