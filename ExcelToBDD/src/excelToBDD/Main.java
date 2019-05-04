package excelToBDD;

import java.io.IOException;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws IOException, SQLException {
        Config config = new Config("config.txt");
        Excel excel = new Excel(config.getExcel());
        excel.justDoIt();


    }


}

