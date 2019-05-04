package excelToBDD;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Excel {
    private String name;

    public Excel(String name) {
        this.name = name;
    }

    public static boolean checkNull(ArrayList<String> array, int index) throws SQLException {

        String id = array.get(index);
        if (id.equals("0")) {
            return true;
        } else {
            return false;
        }

    }

    public void justDoIt() throws IOException, SQLException {
       if(!this.chauffeur()){
           System.out.println("L'id dans la case chauffeur n'est pas celle d'un chauffeur");
        }
        if (!this.collab()) {
            System.out.println("Il y a un problème avec votre id de service la personne n'est pas un collaborateur ou un chauffeur");
        }
    }

    public boolean collab() throws IOException, SQLException {
        Workbook workbook = WorkbookFactory.create(new File(name));
        Sheet sheet = workbook.getSheet("Collab");
        DataFormatter dataFormatter = new DataFormatter();
        String[] arrayForBdd = new String[9];
        ArrayList<String> array = new ArrayList<>(Arrays.asList(arrayForBdd));


        for (Row row : sheet) {
            String collab = null;
            String service = null;
            String priceString = null;
            String idRem = null;
            String debut = null;
            String fin = null;
            Double heureTotal = null;
            int newService = 0;
            for (Cell cell : row) {
                if (row.getRowNum() != 0) {
                    String cellValue = dataFormatter.formatCellValue(cell);

                    array.set(cell.getColumnIndex(), cellValue);

                    if (row.getRowNum() >= 1) {
                        if (cell.getColumnIndex() == 0) {
                            if (checkNull(array, 0) == true) {
                                newService = 1;
                            }
                        }

                        if (cell.getColumnIndex() == 2) {
                            service = cellValue;
                            if (service.equals("11") || service.equals("12") || service.equals("13")) {

                            } else {
                                return false;
                            }
                        }

                        if (cell.getColumnIndex() == 3) {
                            collab = cellValue;
                            priceString = Select.dynamique("collaborateurs","prixCollaborateur","idCollaborateurs",collab, "string");
                        }

                        if (cell.getColumnIndex() == 6){
                            debut = cellValue;
                        }

                        if (cell.getColumnIndex() == 7){
                            fin = cellValue;
                            heureTotal = checkTime(debut,fin);
                            priceString = Double.toString(Double.parseDouble(priceString)* heureTotal);
                        }

                        if (cell.getColumnIndex() == 8) {
                            if (!checkNull(array, 8)) {
                                idRem = cellValue;
                            }
                            toBDDCollab(array, newService, collab, priceString, row.getRowNum(), workbook, sheet);
                        }
                    }
                }
            }
        }
        workbook.close();
        return true;
    }

    private Double checkTime(String debut, String fin){
        double debutInt, minDeb, finInt, minFin, heureTotal;
        String[] debutTab, finTab;
        debutTab = debut.split(":");
        finTab = fin.split(":");
        debutInt = Integer.parseInt(debutTab[0]);
        finInt = Integer.parseInt(finTab[0]);
        minDeb = Integer.parseInt(debutTab[1]);
        minFin = Integer.parseInt(finTab[1]);
        if (minDeb < 30){
            minDeb = 0;
        }else{
            minDeb = 0.5;
        }

        if (minFin < 30){
            minFin = 0;
        }else{
            minFin = 0.5;
        }
        heureTotal =(finInt+minFin)-(debutInt-minDeb);
        if (heureTotal<0.0){
            debutInt = 24 - debutInt-minDeb;
            heureTotal = debutInt + (finInt+minFin);
        }
        if (heureTotal == 0.0){
            heureTotal = 0.5;
        }
        return heureTotal;
    }

    private void toBDDCollab(ArrayList<String> array, int newService, String collab, String price, int row, Workbook workbook, Sheet sheet) throws SQLException, IOException {
        if (newService == 1) {
            Insert.service(array);

            int id = Select.idLink();
            int rem = Select.idRem();
            Insert.remuneration(collab, Integer.toString(id), price);
            changeId(id, row, workbook, sheet);
            changeRem(rem, row, workbook, sheet, 8);
        } else {
            Update.service(array);
        }
    }

    public boolean chauffeur() throws IOException, SQLException {
        Workbook workbook = WorkbookFactory.create(new File(name));
        Sheet sheet = workbook.getSheet("Chauffeur");
        DataFormatter dataFormatter = new DataFormatter();
        String[] arrayForBdd = new String[14];
        ArrayList<String> array = new ArrayList<>(Arrays.asList(arrayForBdd));


        for (Row row : sheet) {
            String chauffeur = null;
            String priceString = null;
            String distance = null;
            String idRem = null;
            int newTrajet = 0;
            for (Cell cell : row) {
                if (row.getRowNum() != 0) {
                    String cellValue = dataFormatter.formatCellValue(cell);

                    array.set(cell.getColumnIndex(), cellValue);

                    if (row.getRowNum() >= 1) {
                        if (cell.getColumnIndex() == 0) {
                            if (checkNull(array, 0)) {
                                newTrajet = -1;
                            } else {
                                newTrajet = Integer.parseInt(cellValue);
                            }
                        }

                        if (cell.getColumnIndex() == 2) {
                            chauffeur = cellValue;
                            if(!checkChauffeur(chauffeur)){
                                return false;
                            }
                        }
                        if (cell.getColumnIndex() == 6) {
                            distance = cellValue;
                        }


                        if (cell.getColumnIndex() == 13) {
                            if (!checkNull(array, 13)) {
                                idRem = cellValue;
                            }

                            priceString = Float.toString(Select.dynamique("collaborateurs","prixCollaborateur","idCollaborateurs",chauffeur,1f) * Float.parseFloat(distance));

                            toBDDChauffeur(array, newTrajet, chauffeur, priceString, row.getRowNum(), workbook, sheet, idRem);
                        }

                    }
                }
            }
        }
        workbook.close();
        return true;
    }

    private boolean checkChauffeur(String chauffeur) throws SQLException{
        String test = Select.dynamique("collaborateurs","metier","idCollaborateurs",chauffeur,"string");
        if(!test.equals("chauffeur")){
            return false;
        }
        return true;
    }

    private void toBDDChauffeur(ArrayList<String> array, int newTrajet, String chauffeur, String price, int row, Workbook workbook, Sheet sheet, String idRem) throws SQLException, IOException {
        if (newTrajet == -1) {
            Insert.trajet(array);
            int id = Select.idTrajet();
            int rem = Select.idRem();
            Insert.remuneration(chauffeur, Integer.toString(id), price);
            changeId(id, row, workbook, sheet);
            changeRem(rem, row, workbook, sheet, 13);

            System.out.println("L'id nouvelle est " + id);
        } else {
            Update.trajet(array);
            Update.remuneration(chauffeur, Integer.toString(newTrajet), price, idRem);
        }
    }

    private void changeId(int id, int rowId, Workbook workbook, Sheet sheet) throws IOException {
        Row row = sheet.getRow(rowId);
        Cell cell = row.getCell(0);
        if (cell != null) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(id);
        }
        FileOutputStream fileOut = new FileOutputStream(name + ".new");
        workbook.write(fileOut);
        fileOut.close();
        Files.delete(Paths.get(name + ".new"));


    }

    private void changeRem(int id, int rowId, Workbook workbook, Sheet sheet, int rem) throws IOException {
        Row row = sheet.getRow(rowId);
        Cell cell = row.getCell(rem);
        if (cell != null) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(id);
        }
        FileOutputStream fileOut = new FileOutputStream(name + ".new");
        workbook.write(fileOut);
        fileOut.close();
        Files.delete(Paths.get(name + ".new"));


    }

}

