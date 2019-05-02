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
        Excel excel = new Excel("excel.xlsx");
        excel.justDoIt();


    }
}

