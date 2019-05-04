package excelToBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Update {




    public static void trajet(ArrayList<String> array) throws SQLException {
        Connection conn = Bdd.startBdd();
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

    public static void service(ArrayList<String> array) throws SQLException {
        Connection conn = Bdd.startBdd();
        PreparedStatement prepare = conn.prepareStatement("UPDATE linkservicetrajet SET " +
                "idTrajet = " + array.get(1) + ", " +
                "idService = " + array.get(2) + ", " +
                "idAnnexe=" + array.get(3) + ", " +
                "quantite = " + array.get(4) + ", " +
                "statut =" + array.get(5) + ", " +
                "dateStart ='" + array.get(6) + "', " +
                "dateEnd = '" + array.get(7) + " '" +
                "WHERE idLink = '"+array.get(0)+"'");
        //System.out.println(prepare);
        int statut = prepare.executeUpdate();
        if (statut == 1) {
            System.out.println("Vous avez bien update le trajet dans la base de donnée");
        } else {
            System.out.println("Il y a eu un problème lors de l'update du trajet dans la base de donnée ( Trajet inexistant surement en BDD )");
        }
    }

    public static void remuneration(String collab, String trajet, String price, String id) throws SQLException {
        Connection conn = Bdd.startBdd();
        PreparedStatement prepare = conn.prepareStatement("UPDATE remuneration SET " +
                "idCollab = " +collab + ", " +
                "idTrajet = " + trajet + ", " +
                "Price = '" + price + " '" +
                "WHERE idRemuneration = '"+id+"'");
        int statut = prepare.executeUpdate();
        if (statut == 1) {
            System.out.println("Vous avez bien update la remuneration dans la base de donnée");
        } else {
            System.out.println("Il y a eu un problème lors de l'update de la rémunération dans la base de donnée");
        }
    }
}
