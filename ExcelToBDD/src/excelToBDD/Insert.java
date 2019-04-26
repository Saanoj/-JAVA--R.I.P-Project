package excelToBDD;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public Insert(){}
    public static Connection startBdd()
    {
        Bdd bdd = new Bdd("root", "", "RIP");
        Connection conn = null;
        bdd.startConnect();
        conn = bdd.getConn();
        return conn;
    }

    public boolean remuneration(int collab, int trajet, float price) throws SQLException {
        Connection conn = startBdd();
        PreparedStatement prepare = conn.prepareStatement("INSERT INTO remuneration (idCollab, idTrajet, Price) VALUES (" +
                "" + collab + "," +
                "'" + trajet + "'," +
                "'" + price + "');");
        int statut = prepare.executeUpdate();
        if (statut == 1) {
            System.out.println("Vous avez bien inséré la rémunération  dans la base de donnée");
            return true;
        } else {
            System.out.println("Il y a eu un problème lors de l'insertion de la rémunération dans la base de données");
            return false;
        }
    }


    public boolean trajet(int client, int chauffeur, String heureDebut, String heureFin, String date, int distance, int prix, String debut, String fin, String duration) throws SQLException {
        Connection conn = startBdd();
        PreparedStatement prepare = conn.prepareStatement("INSERT INTO trajet (idTrajet, idClient, idChauffeur, heureDebut, heureFin, dateResevation, distanceTrajet, prixtrajet, debut, fin, duration) VALUES (" +
                "" + client + "," +
                "'" + chauffeur + "'," +
                "'" + heureDebut + "'," +
                "'" + heureFin + "'," +
                "'" + date + "'," +
                "'" + distance + "'," +
                "'" + chauffeur + "'," +
                "'" + prix + "'," +
                "'" + debut + "'," +
                "'" + fin + "'," +
                "'" + duration + "');");
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
