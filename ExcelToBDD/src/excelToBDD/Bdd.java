package excelToBDD;
import java.sql.*;

public class Bdd {


    //  Database credentials
    private String USER;
    private String PASS;
    private String DB_URL;
    private Connection conn = null;

    public Bdd(String user, String pass, String dbName) {
        this.USER = user;
        this.PASS = pass;
        this.DB_URL = "jdbc:mysql://localhost/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }

    public Connection getConn() {
        return conn;
    }

    public static Connection startBdd()
    {
        Config config = new Config("config.txt");
        Bdd bdd = new Bdd(config.getUser(), config.getPass(), config.getBdname());
        Connection conn = null;
        bdd.startConnect();
        conn = bdd.getConn();
        return conn;
    }
//permer de start la bdd
    public void startConnect() {

        try {
            // System.out.println("Conection à la base de données...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           // System.out.println("Connection réussit à la base de données...");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            System.out.println("Handle errors for JDBC");
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            System.out.println("Handle errors for Class.forName");
        }
    }
}
