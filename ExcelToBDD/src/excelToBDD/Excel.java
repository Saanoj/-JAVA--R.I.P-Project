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
        String[] arrayForBdd = new String[13];
        ArrayList<String> array = new ArrayList<>(Arrays.asList(arrayForBdd));
        int newTrajet = 0;
        for (Row row : sheet) {

                            for (Cell cell : row) {
                                if (row.getRowNum()!=0) {
                                    String cellValue = dataFormatter.formatCellValue(cell);

                                    array.set(cell.getColumnIndex(), cellValue);

                                    if (row.getRowNum() >= 1) {
                                        if (cell.getColumnIndex() == 0) {
                                            if (cellValue.equals("0"))
                                            {
                                                newTrajet = 1;
                                            }
                                        }
                                        if (cell.getColumnIndex() == 12) {
                                            toBDD(array, newTrajet);
                                        }

                    }
                }

            }
        }
        workbook.close();
    }

    private void toBDD(ArrayList<String> array, int newTrajet) throws SQLException{
        if (newTrajet == 1 ){
            Insert.trajet(array);
        }else{
            Update.trajet(array);
        }
    }
}

