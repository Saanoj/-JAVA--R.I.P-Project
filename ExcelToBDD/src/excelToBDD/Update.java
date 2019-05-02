package excelToBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Update {

    public static Connection startBdd()
    {
        Bdd bdd = new Bdd("root", "", "RIP");
        Connection conn = null;
        bdd.startConnect();
        conn = bdd.getConn();
        return conn;
    }

    public boolean remuneration(String id,String collab, String trajet, String price) throws SQLException {
        Connection conn = startBdd();
        PreparedStatement prepare = conn.prepareStatement("UPDATE remuneration SET " +
                "idCollab =" + collab + "," +
                "idTrajet = " + trajet + "," +
                "Price = " + price + "" +
                "WHERE idRemuneration = "+id+");");
        int statut = prepare.executeUpdate();
        if (statut == 1) {
            System.out.println("Vous avez bien inséré la rémunération  dans la base de donnée");
            return true;
        } else {
            System.out.println("Il y a eu un problème lors de l'insertion de la rémunération dans la base de données");
            return false;
        }
    }

    public static void trajet(ArrayList<String> array) throws SQLException {
        Connection conn = startBdd();
        PreparedStatement prepare = conn.prepareStatement("UPDATE trajet SET " +
                "idClient = " + array.get(1) + ", " +
                "idChauffeur = '" + array.get(2) + "', " +
                "heureDebut='" + array.get(3) + "', " +
                "heureFin = '" + array.get(4) + "', " +
                "dateResevation ='" + array.get(5) + "', " +
                "distanceTrajet ='" + array.get(6) + "', " +
                "prixtrajet =" + array.get(7) + ", " +
                "debut = '" + array.get(8) + "', " +
                "fin = '" + array.get(9) + "', " +
                "duration = '" + array.get(10) + " '," +
                "state = ' Finis'," +
                "stateDriver = '1'" +
                "WHERE idTrajet = '"+array.get(0)+"'");
       // System.out.println(prepare);
        int statut = prepare.executeUpdate();
        if (statut == 1) {
       System.out.println("Vous avez bien update le trajet dans la base de donnée");

        } else {
            System.out.println("Il y a eu un problème lors de l'update du trajet dans la base de donnée ( Trajet inexistant surement en BDD )");

        }
    }


}
