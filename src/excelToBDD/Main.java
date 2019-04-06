package excelToBDD;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException,InvalidFormatException,SQLException  {


        // On créer un workbook a partir du fichier excel
        Workbook workbook = WorkbookFactory.create(new File("excel.xlsx"));

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // On part de l'index 0 du sheet. ( la première page)
        Sheet sheet = workbook.getSheetAt(0);

        // On créer un dataFormatter qui va nous permettre d'avoir chaque celulle sous format String
        // Si on a pas de dataFormatter et que les données dans le excel ne sont pas pareils ( si on a des valeurs numériques et des string par exemple, on ne pourra pas récupérer correctement les valeurs sans les formatter )

        DataFormatter dataFormatter = new DataFormatter();
        int rowMax = 0;
        // On recupere le nombres de lignes total
        for (Row row : sheet) {
            if (row.getRowNum() > rowMax) {
                rowMax = row.getRowNum();
            }
        }

        String[] arrayForBdd = new String[11];
        ArrayList arrayInfo = new ArrayList(Arrays.asList(arrayForBdd));
        Bdd bdd = new Bdd("root", "", "RIP");
        RequestInsert rqt = new RequestInsert(bdd);
        Connection conn = RequestInsert.startBddReqInsert(bdd);


        // On créer une double boucle afin de lire les lignes et les colonnes
        for (Row row : sheet) {

            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);

                if (row.getRowNum() >= 1 ) {

                    arrayInfo.set(cell.getColumnIndex(),cellValue);

                    if (cell.getColumnIndex() == 10)
                    {

                        // ArrayList req = rqt.req(arrayInfo,bdd.getConn());
                    }

                }

            }
        }



        // Closing the workbook
        workbook.close();
    }
}

