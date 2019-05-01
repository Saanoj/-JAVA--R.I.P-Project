package excelToBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public Select(){}
    public static Connection startBdd()
    {
        Bdd bdd = new Bdd("root", "", "RIP");
        Connection conn = null;
        bdd.startConnect();
        conn = bdd.getConn();
        return conn;
    }

    public static int idTrajet() throws SQLException {
        Connection conn = startBdd();
        int idTrajetString = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT idTrajet FROM trajet ORDER BY idTrajet DESC");
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) {
             idTrajetString = idTrajet.getInt("idTrajet");
        }
        return idTrajetString;

    }


}
