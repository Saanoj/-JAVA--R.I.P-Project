package excelToBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public Select(){}
    // on démare la bdd <3
    public static Connection startBdd()
    {
        Bdd bdd = new Bdd("root", "", "RIP");
        Connection conn = null;
        bdd.startConnect();
        conn = bdd.getConn();
        return conn;
    }


    //On chope l'id du dernier trajet
    public static int idTrajet() throws SQLException {
        Connection conn = startBdd();
        int idTrajetString = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT idTrajet FROM trajet ORDER BY idTrajet DESC"); // dernier truc inséré
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) { // on prend le premier du select
             idTrajetString = idTrajet.getInt("idTrajet");
        }
        return idTrajetString;

    }
    // on chope l'id du lien etre les services et les abonnements
    public static int idLink() throws SQLException {
        Connection conn = startBdd();
        int idLink = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT idLink FROM linkservicetrajet ORDER BY idLink DESC"); // dernier truc inséré
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) { // prend le premier de la list
            idLink = idTrajet.getInt("idLink");
        }
        return idLink;

    }

    // on récupère le prix du collaborateur
    public static int priceCollab(String id) throws SQLException {
        Connection conn = startBdd();
        int price = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT prixService FROM services WHERE idService ="+ id);
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) {
            price = idTrajet.getInt("prixService");
        }
        return price;

    }

}
