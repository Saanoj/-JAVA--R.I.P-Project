package excelToBDD;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Excel {
    private String name;
    public Excel(String name){
        this.name = name;
    }
    public void JustDoIt() throws IOException, SQLException{
        Workbook workbook = WorkbookFactory.create(new File(name));
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        String[] arrayForBdd = new String[12];
        ArrayList<String> array = new ArrayList<>(Arrays.asList(arrayForBdd));
        int newTrajet = 0;
        for (Row row : sheet) {

            for (Cell cell : row) {
                if (row.getRowNum()!=0) {
                    String cellValue = dataFormatter.formatCellValue(cell);

                    System.out.print(cell.getColumnIndex()+" : ");
                    System.out.println(cellValue);

                    array.set(cell.getColumnIndex(), cellValue);
                    if (row.getRowNum() == 1) {
                        if (cellValue == null) {
                            newTrajet = 1;
                        }
                    }
                    if (row.getRowNum() >= 2) {

                        if (cell.getColumnIndex() == 10) {
                            toBDD(array, newTrajet);
                        }

                    }
                }

            }
        }
        workbook.close();
    }

    private void toBDD(ArrayList<String> array, int newTrajer) throws SQLException{
        if (newTrajer == 1 ){
            Insert.trajet(array);
        }else{
            Update.trajet(array);
        }
    }
}

