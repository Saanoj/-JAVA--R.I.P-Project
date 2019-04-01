package excelToBDD;

        import org.apache.poi.xssf.usermodel.XSSFCell;
        import org.apache.poi.xssf.usermodel.XSSFRow;
        import org.apache.poi.xssf.usermodel.XSSFSheet;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;

public class Read {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("excel.xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        System.out.println(row.getCell(0).getStringCellValue());

    }
}

