package excelToBDD;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Read {

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
        ArrayList tableauDynamique = new ArrayList(Arrays.asList(arrayForBdd));




        // On créer une double boucle afin de lire les lignes et les colonnes
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
               // System.out.print(cellValue + "\t");
                if (row.getRowNum() >= 1 ) {
                   tableauDynamique.set(cell.getColumnIndex(),cellValue);
                    if (cell.getColumnIndex() == 10)
                    {
                        Bdd bdd = new Bdd("root", "", "RIP");
                        Request rqt = new Request(bdd);
                         ArrayList req = rqt.req("Select * from users", "email");
                        // System.out.println(req);
                        Connection conn = bdd.getConn();
                        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        PreparedStatement prepare = conn.prepareStatement("INSERT INTO `trajet` (`idTrajet`, `idClient`, `idChauffeur`, `heureDebut`, `heureFin`, `dateResevation`, `distanceTrajet`, `prixtrajet`, `debut`, `fin`, `duration`) VALUES (" +
                                "NULL,'" + tableauDynamique.get(1) + "'," +
                                "'" + tableauDynamique.get(2) + "'," +
                                "'" + tableauDynamique.get(3) + "'," +
                                "'" + tableauDynamique.get(4) + "'," +
                                "'" + tableauDynamique.get(5) + "'," +
                                "'" + tableauDynamique.get(6) + "'," +
                                "'" + tableauDynamique.get(7) + "'," +
                                "'" + tableauDynamique.get(8) + "'," +
                                "'" + tableauDynamique.get(9) + "'," +
                                "'" + tableauDynamique.get(10)+ "');");
                        int statut = prepare.executeUpdate();
                        if (statut == 1)
                        {
                            System.out.println("Vous avez bien inséré vos trajets excels dans la base de données");
                        }
                        else
                        {
                            System.out.println("Il y a eu un problème lors de l'insertion des trajets dans la base de données");

                        }

                    }
                    /*

                    */


                }


            }
        }





        // Closing the workbook
        workbook.close();
    }
}

