package modele;

import java.sql.*;
import java.util.ArrayList;

import controleur.Proprietaire;
import controleur.User;
import controleur.Ville;

public class Modele {
    /************************* Attributs ****************************/
    private static Connexion uneConnexion = new Connexion ("localhost:8889", "NeigeSoleil", "root", "root");

    /********************* Gestion des utilisateurs **************************/

    public static void insertUser (User unUser) {
        String requete = "insert into user values (null, '" + unUser.getNom() + "', '" + unUser.getPrenom() + "', '" + unUser.getAdresse()+ "','"+unUser.getCp()+"''"+ unUser.getEmail() + "', '" + unUser.getMdp() + "')";
        executerRequete(requete);
    }

    

    public static ArrayList<User> selectAllUser (){
        ArrayList<User> lesUsers = new ArrayList<User>();
        String requete = "select * from user";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()){
                User unUser = new User(lesResultats.getInt("id_user"),0, lesResultats.getString("nom"), lesResultats.getString("prenom"), lesResultats.getString("email"), 0, lesResultats.getString("mdp"), requete, requete, requete);
                lesUsers.add(unUser);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesUsers;
    }

    public static ArrayList<User> selectLikeUser(String filtre) {
        ArrayList<User> lesUsers = new ArrayList<User>();
        String requete = "select * from user where nom like '%" + filtre + "%' or prenom like '%" + filtre + "%' or adresse like '%" + filtre + "%' or code_postal like '%"+filtre+ "%' or telephone like '%"+filtre+"%' or email like '%"+filtre+"%'";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                User unUser = new User(lesResultats.getInt("id_user"), 0, lesResultats.getString("nom"), lesResultats.getString("prenom"), lesResultats.getString("adresse"), lesResultats.getInt("cp"), lesResultats.getString("email"), lesResultats.getString("mdp"), requete, requete);
                lesUsers.add(unUser);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesUsers;
    }

    public static User selectWhereUser (String email, String mdp){
        String requete = "select * from User where email ='"+email+"' and mdp ='"+mdp+"';";
        User unUser = null;
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if (unResultat.next()){
                unUser = new User(
                    unResultat.getInt("id_user"),
                    unResultat.getInt("id_ville"),
                    unResultat.getString("nom"),
                    unResultat.getString("prenom"),
                    unResultat.getString("adresse"),
                    unResultat.getInt("code_postal"),
                    unResultat.getString("telephone"),
                    unResultat.getString("email"),
                    unResultat.getString("mdp"),
                    unResultat.getString("role")
                );
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : "+ requete);
        }
        return unUser;
    }
    /********************* Gestion des villes **************************/

    public static ArrayList<Ville> selectAllVilles (){
        ArrayList<Ville> lesVilles = new ArrayList<Ville>();
        String requete = "select * from VILLE;";
        try{
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
            while (lesResultats.next()) {
                Ville uneVille = new Ville(lesResultats.getInt("Id Ville"), lesResultats.getString("Nom de la ville"));
                lesVilles.add(uneVille);
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        }
        catch(SQLException exp){
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesVilles;
    }

    /************************ GESTION DES PROPRIETAIRES **********************/
	
	//Faut creer une procédure d'insertion dans laquelle on appelle un procédure qui genere un mot de passe random pour l'insérer en tant que mdp puis retourner ce mdp pour l'utiliser dans un api de mail pour envoyer le mdp au propriétaire
	public static void insertProprietaire(Proprietaire unProprietaire) {
		String requete = "insert into proprietaire values (null, '"+unProprietaire.getNom()
		+ "','" + unProprietaire.getPrenom() + "','" + unProprietaire.getAdresse() + "','" + unProprietaire.getCode_postal() + "','" + unProprietaire.getTel()
		+ "','" + unProprietaire.getEmail()+"','" + unProprietaire.getMdp()+"','"+unProprietaire.getRole()+");";
		
		executerRequete (requete); 
	}

	public static ArrayList<Proprietaire> selectAllProprietaires (){
		ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>(); 
		String requete ="select * from proprietaire;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un proprietaire 
				Proprietaire unProprietaire = new Proprietaire(
						lesResultats.getInt("ID_PROPRIETAIRE"), lesResultats.getString("NOM"),
						lesResultats.getString("PRENOM"),lesResultats.getString("ADRESSE"),lesResultats.getInt("CODE_POSTAL"),
						lesResultats.getString("EMAIL"),lesResultats.getString("TELEPHONE")
						);
				//on ajoute le proprietaire dans l'ArrayList
				lesProprietaires.add(unProprietaire);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesProprietaires; 
	}

	public static ArrayList<Proprietaire> selectLikeProprietaires (String filtre){
		ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>(); 
		String requete ="select * from proprietaire where nom like '%"+filtre
				+"%' or prenom like '%" + filtre + "%' or adresse like '%"
				+ filtre + "%' or email like '%" + filtre + "%' or tel like '%"
				+ filtre + "%' ; ";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete);
			while(lesResultats.next()) {
				//instanciation d'un proprietaire 
				Proprietaire unProprietaire = new Proprietaire(
					lesResultats.getInt("ID_PROPRIETAIRE"), lesResultats.getString("NOM"),
					lesResultats.getString("PRENOM"),lesResultats.getString("ADRESSE"),lesResultats.getInt("CODE_POSTAL"),
					lesResultats.getString("EMAIL"),lesResultats.getString("TELEPHONE")
					);
				//on ajoute le proprietaire dans l'ArrayList
				lesProprietaires.add(unProprietaire);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesProprietaires; 
	}

	public static void updateProprietaire(Proprietaire unProprietaire) {
		String requete ="update proprietaire set nom = '" + unProprietaire.getNom() 
		+ "', prenom ='"+unProprietaire.getPrenom() + "', adresse='" + unProprietaire.getAdresse()
		+ "', code_postal='" + unProprietaire.getCode_postal()
		+ "', email ='"+unProprietaire.getEmail() + "', tel='" + unProprietaire.getTel()
		+ "'  where  id_proprietaire = "+unProprietaire.getIdProprietaire()+";";
		
		executerRequete(requete);
	}

	public static void deleteProprietaire (int idProprietaire) {
		String requete = "delete from proprietaire where id_proprietaire = "+idProprietaire+";";
		executerRequete(requete);
	}



    /********************* Autres méthodes **************************/

    public static void executerRequete (String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}

    public static int count (String table) {
		int nb = 0;
		String requete = "select count(*) as nb from "+table+";";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				nb = unResultat.getInt("nb");
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}

		return nb;
	}




    
}
