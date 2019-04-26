package excelToBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {

    public static Connection startBdd()
    {
        Bdd bdd = new Bdd("root", "", "RIP");
        Connection conn = null;
        bdd.startConnect();
        conn = bdd.getConn();
        return conn;
    }

    public boolean remuneration(int id,int collab, int trajet, float price) throws SQLException {
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

    public boolean trajet(int id, int client, int chauffeur, String heureDebut, String heureFin, String date, int distance, int prix, String debut, String fin, String duration) throws SQLException {
        Connection conn = startBdd();
        PreparedStatement prepare = conn.prepareStatement("UPDATE trajet SET" +
                "idClient =" + client + "," +
                "idChauffeur = " + chauffeur + "," +
                "heureDebut=" + heureDebut + "," +
                "heureFin =" + heureFin + "," +
                "dateResevation =" + date + "," +
                "distanceTrajet =" + distance + "'," +
                "prixtrajet= " + chauffeur + "'," +
                "debut =" + prix + "'," +
                "debut =" + debut + "'," +
                "fin =" + fin + "'," +
                "duration =" + duration + "" +
                "WHERE idTrajet="+id+");");
        int statut = prepare.executeUpdate();
        if (statut == 1) {
            System.out.println("Vous avez bien inséré la rémunération  dans la base de donnée");
            return true;
        } else {
            System.out.println("Il y a eu un problème lors de l'insertion de la rémunération dans la base de données");
            return false;
        }
    }
}
