package excelToBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public Select(){}
    // on démare la bdd <3


    public static int dynamique(String table, String col, String idName,String id, int valeurretour) throws SQLException {
        Connection conn = Bdd.startBdd();
        int selInt = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT "+col+" FROM "+table+" WHERE "+idName+" ="+ id);
        ResultSet result = prepare.executeQuery();
        if (result.first()) {
            selInt = result.getInt(col);
        }
        return selInt;
    }
    public static Float dynamique(String table, String col, String idName,String id, float valeurretour) throws SQLException {
        Connection conn = Bdd.startBdd();
        float selFloat= 0.0f;
        PreparedStatement prepare = conn.prepareStatement("SELECT "+col+" FROM "+table+" WHERE "+idName+" ="+ id);
        ResultSet result = prepare.executeQuery();
        if (result.first()) {
            selFloat = result.getFloat(col);
        }
        return selFloat;
    }
    public static String dynamique(String table, String col, String idName,String id, String valeurretour) throws SQLException {
        Connection conn = Bdd.startBdd();
        String selString = null;
        PreparedStatement prepare = conn.prepareStatement("SELECT "+col+" FROM "+table+" WHERE "+idName+" ="+ id);
        ResultSet result = prepare.executeQuery();
        if (result.first()) {
            selString = result.getString(col);
        }
        return selString;
    }

    // on récupère le prix du collaborateur
    public static int priceCollab(String id) throws SQLException {
        Connection conn = Bdd.startBdd();
        int price = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT prixService FROM services WHERE idService ="+ id);
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) {
            price = idTrajet.getInt("prixService");
        }
        return price;
    }


    public static float priceChauffeur(String id) throws SQLException{
        Connection conn = Bdd.startBdd();
        float price = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT prixCollaborateur FROM collaborateurs WHERE idCollaborateurs ="+ id);
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) {
            price = idTrajet.getFloat("prixCollaborateur");
        }
        return price;
    }
    //On chope l'id du dernier trajet
    public static int idTrajet() throws SQLException {
        Connection conn = Bdd.startBdd();
        int idTrajetString = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT idTrajet FROM trajet ORDER BY idTrajet DESC"); // dernier truc inséré
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) { // on prend le premier du select
             idTrajetString = idTrajet.getInt("idTrajet");
        }
        return idTrajetString;

    }

    public static int idRem() throws SQLException {
        Connection conn = Bdd.startBdd();
        int idRemuneration = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT idRemuneration FROM remuneration ORDER BY idRemuneration DESC"); // dernier truc inséré
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) { // on prend le premier du select
            idRemuneration= idTrajet.getInt("idRemuneration");
        }
        return idRemuneration;

    }

    // on chope l'id du lien etre les services et les abonnements
    public static int idLink() throws SQLException {
        Connection conn = Bdd.startBdd();
        int idLink = 0;
        PreparedStatement prepare = conn.prepareStatement("SELECT idLink FROM linkservicetrajet ORDER BY idLink DESC"); // dernier truc inséré
        ResultSet idTrajet = prepare.executeQuery();
        if (idTrajet.first()) { // prend le premier de la list
            idLink = idTrajet.getInt("idLink");
        }
        return idLink;

    }


}
