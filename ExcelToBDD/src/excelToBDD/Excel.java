package excelToBDD;

import com.mysql.cj.xdevapi.Column;
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

        String chauffeur = null;
        String priceString=null;
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

                                        if (cell.getColumnIndex() == 2) {
                                            chauffeur = cellValue;
                                        }

                                        if (cell.getColumnIndex() == 7) {

                                            priceString = cellValue;
                                            int priceInt = Integer.parseInt(priceString)/2;
                                            priceString = Integer.toString(priceInt);
                                        }

                                        if (cell.getColumnIndex() == 12) {
                                            toBDD(array, newTrajet, chauffeur, priceString,row.getRowNum());
                                        }

                    }
                }

            }
        }
        workbook.close();
    }

    private void toBDD(ArrayList<String> array, int newTrajet, String chauffeur, String price,int row) throws SQLException,IOException{
        if (newTrajet == 1 ){
            Insert.trajet(array);

            int id = Select.idTrajet();
            Insert.remuneration(chauffeur,Integer.toString(id),price);
            changeIdTrajet(id,row);
            System.out.println("L'id nouvelle est "+ id);
        }else{
            Update.trajet(array);
        }
    }

    private void changeIdTrajet(int id, int row) throws IOException{
        Workbook workbook = WorkbookFactory.create(new File(name));
        Sheet sheet = workbook.createSheet("Employee");
        Row headerRow = sheet.createRow(row);
        Cell cell = headerRow.createCell(1);
        cell.setCellValue(id);
        workbook.close();

    }


}

